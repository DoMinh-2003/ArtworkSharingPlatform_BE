package start.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FcmNotification {
    private String title;
    private String body;
    private String token;
}
