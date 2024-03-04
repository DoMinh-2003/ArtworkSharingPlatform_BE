package start.api;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.request.GetRoomRequest;
import start.dto.request.MessageRequest;
import start.dto.request.RoomRequest;
import start.entity.Room;
import start.service.ChatService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping()
    public ResponseEntity createNewChat(@RequestBody RoomRequest roomRequest) {
        Room room = chatService.createNewRoom(roomRequest);
        return ResponseEntity.ok(room);
    }

    @GetMapping()
    public ResponseEntity getChatByAccountID() {
        List<Room> rooms = chatService.getRoomsByAccountID();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/detail/{roomID}")
    public ResponseEntity getChatDetail(@PathVariable int roomID) {
        return ResponseEntity.ok(chatService.getRoomDetail(roomID));
    }

    @PostMapping("/send/{roomID}")
    public ResponseEntity sendMessage(@PathVariable int roomID, @RequestBody MessageRequest messageRequest) {
        return ResponseEntity.ok(chatService.sendMessage(messageRequest,roomID));
    }

    @PostMapping("/typing/{roomID}/{name}")
    public void typingMessage(@PathVariable int roomID, @PathVariable String name) {
        chatService.setTyping(roomID, name);
    }

    @PostMapping("/room")
    public ResponseEntity<Room> getRoom(@RequestBody GetRoomRequest getRoomRequest) {
        return ResponseEntity.ok(chatService.getRoom(getRoomRequest));
    }
}
