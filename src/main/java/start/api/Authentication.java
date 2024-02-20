package start.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.LoginGoogleRequest;
import start.dto.request.LoginRequestDTO;
import start.dto.request.SignUpRequestDTO;
import start.dto.response.LoginResponse;
import start.entity.User;
import start.repository.UserRepository;
import start.service.AuthenService;
import start.service.UserService;
import start.utils.ResponseHandler;

@RestController
public class Authentication {

    @Autowired
    AuthenService authenService;

    @Autowired
    ResponseHandler responseHandler;
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponse user = authenService.login(loginRequestDTO);
        return responseHandler.response(200, "Login success!", user);
    }


    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = authenService.signUp(signUpRequestDTO);
        return responseHandler.response(200, "Sign Up success!", user);
    }

    @PostMapping("/login-gg")
        private ResponseEntity checkLoginGoogle(@RequestBody LoginGoogleRequest loginGGRequest){
            return ResponseEntity.ok().body(userService.loginGoogle(loginGGRequest.getToken()));
        }

}
