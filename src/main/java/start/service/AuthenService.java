package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import start.dto.request.LoginRequestDTO;
import start.dto.request.SignUpRequestDTO;
import start.dto.response.LoginResponse;
import start.entity.User;
import start.enums.RoleEnum;
import start.exception.exceptions.AccountNotVerify;
import start.repository.UserRepository;
import start.utils.TokenHandler;

import java.util.UUID;

@Service
public class AuthenService implements UserDetailsService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenHandler tokenHandler;

    public LoginResponse login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

        } catch (Exception e) {
            throw new NullPointerException("Wrong Id Or Password") ;
        }
        User user = (User) authentication.getPrincipal();
        System.out.println(user);

        if(!user.isActive()){
         throw new AccountNotVerify("Account has not been verified");
        }else{
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(user.getUsername());
            loginResponse.setRole(user.getRole());
            loginResponse.setToken(tokenHandler.generateToken(user));
            loginResponse.setId(user.getId());
            loginResponse.setName(user.getName());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setAvt(user.getAvt());
            loginResponse.setPhoneNumber(user.getPhoneNumber());
            loginResponse.setPostQuantity(user.getPostQuantity());
            return loginResponse;
        }
    }

    public User signUp(SignUpRequestDTO signUpRequestDTO){
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setRole(signUpRequestDTO.getRole().toLowerCase().trim().equals("creator")?RoleEnum.CREATOR:RoleEnum.AUDIENCE);
        user.setUsername(signUpRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setName(signUpRequestDTO.getName());
        user.setPhoneNumber(signUpRequestDTO.getPhone());
        user.setAvt("https://png.pngtree.com/png-clipart/20200701/original/pngtree-character-default-avatar-png-image_5407167.jpg");
        user.setActive(false);
    try{
        return userRepository.save(user);
    }catch (DataIntegrityViolationException e) {
        if(e.getMessage().contains("user.username_UNIQUE") || e.getMessage().contains("user.UK_sb8bbouer5wak8vyiiy4pf2bx")) throw new DataIntegrityViolationException("Duplicate UserName");
        else  throw new DataIntegrityViolationException("Duplicate Email");
    }
    }

    public User verifyAccount(UUID id) {
         User user = userRepository.findUserById(id);
         user.setActive(true);
         return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
