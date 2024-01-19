package start.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import start.dto.request.LoginRequestDTO;
import start.dto.request.SignUpRequestDTO;
import start.dto.response.LoginResponse;
import start.entity.User;
import start.service.AuthenService;
import start.utils.ResponseHandler;

@RestController
public class Authentication {

    @Autowired
    AuthenService authenService;

    @Autowired
    ResponseHandler responseHandler;

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
}
