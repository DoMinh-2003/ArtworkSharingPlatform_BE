package start.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import start.entity.User;

@Component
public class AccountUtils {
     public User getCurrentUser() {
         return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     }
}
