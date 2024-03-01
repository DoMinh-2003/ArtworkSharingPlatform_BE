package start.service;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import java.util.HashMap;
import java.util.Map;

//public class NotifycationService {
//    public void sendNotification(){
//        Notification notification = Notification.builder()
//                .setTitle("title")
//                .setBody("Hi")
//                .build();
//
//        Map<String, String> data = new HashMap<>();
//        data.put("click_action", "http://localhost:5173");
//        System.out.println(data);
//        Message message = Message.builder()
//                .setNotification(notification)
//                .setToken(fcmNotification.getToken())
//                .putAllData(data)
//                .build();
//
//        try{
//            System.out.println("Demo 1");
//            firebaseMessaging.send(message);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
