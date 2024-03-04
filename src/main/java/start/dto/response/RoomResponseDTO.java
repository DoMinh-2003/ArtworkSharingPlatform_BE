package start.dto.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import start.entity.Message;
import start.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {
    private int roomID;
    Date lastUpdated = new Date();
    String lastMessage;

    private Set<User> users;
    private List<Message> messages;
}
