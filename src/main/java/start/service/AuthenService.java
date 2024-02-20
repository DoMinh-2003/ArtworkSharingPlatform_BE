package start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import start.exception.exceptions.EntityNotFound;
import start.repository.UserRepository;
import start.utils.TokenHandler;

import java.sql.SQLIntegrityConstraintViolationException;

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
            throw new EntityNotFound("Username or password invalid");
        }
        User user = (User) authentication.getPrincipal();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(user.getUsername());
        loginResponse.setPassword(user.getPassword());
        loginResponse.setRole(user.getRole());
        loginResponse.setToken(tokenHandler.generateToken(user));
        return loginResponse;
    }

    public User signUp(SignUpRequestDTO signUpRequestDTO){
        User user = new User();
        System.out.println(signUpRequestDTO.getUserName());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setRole(signUpRequestDTO.getRole().toLowerCase().trim().equals("creator")?RoleEnum.CREATOR:RoleEnum.AUDIENCE);
        user.setUsername(signUpRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setName(signUpRequestDTO.getName());
        user.setPhoneNumber(signUpRequestDTO.getPhone());
    try{
        return userRepository.save(user);
    }catch (DataIntegrityViolationException e) {
        System.out.println(e.getMessage());
        if(e.getMessage().contains("user.username_UNIQUE")) throw new DataIntegrityViolationException("Duplicate UserName");
        else  throw new DataIntegrityViolationException("Duplicate Email");
    }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }
}
