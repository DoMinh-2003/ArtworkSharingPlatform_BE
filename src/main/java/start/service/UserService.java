package start.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.entity.User;
import start.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User loginGoogle (String token) {
        try{
            FirebaseToken decodeToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String email = decodeToken.getEmail();
             User user = userRepository.findByEmail(email);
            return user;
        } catch (FirebaseAuthException e)
        {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

}
