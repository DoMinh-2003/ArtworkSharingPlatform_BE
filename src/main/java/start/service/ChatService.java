package start.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import start.dto.request.GetRoomRequest;
import start.dto.request.MessageRequest;
import start.dto.request.RoomRequest;
import start.dto.response.RoomResponseDTO;
import start.entity.Message;
import start.entity.Room;
import start.entity.User;
import start.repository.MessageRepository;
import start.repository.RoomRepository;
import start.repository.UserRepository;
import start.utils.AccountUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    AccountUtils accountUtils;

    public Room createNewRoom(RoomRequest roomRequest) {
        Set<User> users = new HashSet<>();
        User user1 = userRepository.findUserById(roomRequest.getMembers().get(0));
        User user2 = userRepository.findUserById(roomRequest.getMembers().get(1));
        Room roomCheck = roomRepository.findRoomByUsersIsContainingAndUsersIsContaining(user1,user2);
        if(roomCheck!=null) return roomCheck;

        Room room = new Room();
        room.setUsers(users);
        for (UUID accountId : roomRequest.getMembers()) {
            try {
                User user = userRepository.findUserById(accountId);
                user.getRooms().add(room);
                users.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomRepository.save(room);
    }

    public List<Room> getRoomsByAccountID() {
        User user = accountUtils.getCurrentUser();
        List<Room> rooms = roomRepository.findRoomsByUsersIsContaining(user);
//        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
//
//        roomResponseDTO.setRoomID();

        if (rooms != null) {
            return rooms.stream().sorted(Comparator.comparing(Room::getLastUpdated).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    public Room getRoomDetail(int roomID) {
        Room roomDTO = roomRepository.findRoomByRoomID(roomID);
        if (roomDTO != null)
            roomDTO.setMessages(roomDTO.getMessages().stream().sorted(Comparator.comparing(Message::getCreateAt)).collect(Collectors.toList()));
        return roomDTO;
    }

    public Message sendMessage(MessageRequest messageRequest, int roomId) {
        User user = accountUtils.getCurrentUser();
        Room roomDTO = roomRepository.findRoomByRoomID(roomId);
        Message messageDTO = new Message();
        messageDTO.setUser(user);
        messageDTO.setRoom(roomDTO);
        messageDTO.setMessage(messageRequest.getMessage());
        roomDTO.setLastUpdated(new Date());
        roomDTO.setLastMessage(messageRequest.getMessage());
        roomRepository.save(roomDTO);
        for (User user1 : roomDTO.getUsers()) {
            if (user1.getId() != user.getId()) {
                messagingTemplate.convertAndSend("/topic/chat/" + user1.getId(), "New message");
//                for (FCM fcm : account.getFcms()) {
//                    FcmNotification fcmNotification = new FcmNotification();
//                    fcmNotification.setBody(messageRequest.getMessage());
//                    fcmNotification.setTitle(accountDTO.getEmail());
//                    fcmNotification.setToken(fcm.getToken());
//                    try {
//                        fcmService.sendPushNotification(fcmNotification);
//                    } catch (FirebaseMessagingException | FirebaseAuthException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
        return messageRepository.save(messageDTO);
    }

    public void setTyping(int roomID, String name) {
        Room roomDTO = roomRepository.findRoomByRoomID(roomID);
        for (User account : roomDTO.getUsers()) {
            messagingTemplate.convertAndSend("/topic/chat/" + account.getId(), "Typing: " + name);
        }
    }

    public Room getRoom(GetRoomRequest getRoomRequest) {
        User user1 = userRepository.findUserById(getRoomRequest.getUser1());
        User user2 = userRepository.findUserById(getRoomRequest.getUser2());

        Set<User> accountDTOS = new HashSet<>();
        accountDTOS.add(user1);
        accountDTOS.add(user2);

        Room room = roomRepository.findRoomByUsersIsContainingAndUsersIsContaining(user1, user2);
        if (room == null) {
            room = new Room();
            room.setUsers(accountDTOS);
            room.setName("[" + user1.getName() + " and "+ user2.getName() + "]");
            room = roomRepository.save(room);
        }

        return room;
    }


}
