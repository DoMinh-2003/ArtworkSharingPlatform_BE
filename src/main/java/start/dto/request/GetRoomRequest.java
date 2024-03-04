package start.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class GetRoomRequest {
    UUID user1;
    UUID user2;
}
