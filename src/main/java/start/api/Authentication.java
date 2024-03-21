package start.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import start.dto.EmailDetail;
import start.dto.request.LoginGoogleRequest;
import start.dto.request.LoginRequestDTO;
import start.dto.request.SignUpRequestDTO;
import start.dto.request.VerifyRequestDTO;
import start.dto.response.LoginResponse;
import start.dto.response.UserResponseDTO;
import start.entity.User;
import start.repository.UserRepository;
import start.service.AuthenService;
import start.service.EmailService;
import start.service.UserService;
import start.utils.ResponseHandler;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name ="api")
public class Authentication {

    @Autowired
    AuthenService authenService;

    @Autowired
    ResponseHandler responseHandler;
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        UserResponseDTO user = authenService.login(loginRequestDTO);
        return responseHandler.response(200, "Login success!", user);
    }


    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = authenService.signUp(signUpRequestDTO);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMailTemplate(user);
            }

        };
        new Thread(r).start();
        return responseHandler.response(200, "Sign Up success!", user);
    }

    @PostMapping("/registerMod")
    public ResponseEntity signupMod(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = authenService.signUpMod(signUpRequestDTO);
        return responseHandler.response(200, "Sign Up Mod success!", user);
    }

    @PostMapping("/login-gg")
        private ResponseEntity checkLoginGoogle(@RequestBody LoginGoogleRequest loginGGRequest){
            return ResponseEntity.ok().body(userService.loginGoogle(loginGGRequest.getToken()));
        }

    @PutMapping("/verify-account")
    private ResponseEntity verifyAccount(@RequestBody VerifyRequestDTO verifyRequestDTO){
        User user = authenService.verifyAccount(verifyRequestDTO);
        return responseHandler.response(200, "verify success!",user);
    }

    @PutMapping("/forgotPassword/{email}")
    private ResponseEntity forgotPassword(@PathVariable String email){
        System.out.println(email);
        User user = authenService.forgotPassword(email);
        return responseHandler.response(200, "Your new information has been sent to email",user);
    }

}
