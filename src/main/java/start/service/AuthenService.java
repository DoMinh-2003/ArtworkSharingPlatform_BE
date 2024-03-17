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
import start.dto.request.VerifyRequestDTO;
import start.dto.response.UserResponseDTO;
import start.entity.User;
import start.entity.Wallet;
import start.enums.RoleEnum;
import start.exception.exceptions.AccountNotVerify;
import start.exception.exceptions.InValidEmail;
import start.repository.UserRepository;
import start.repository.WalletRepository;
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
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    EmailService emailService;

    public UserResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername().trim(),
                            loginRequestDTO.getPassword().trim()
                    )
            );
        } catch (Exception e) {
            throw new NullPointerException("Wrong Id Or Password") ;
        }
        User user = (User) authentication.getPrincipal();
        if(user.isDeActive()) {
            throw new AccountNotVerify("Account has been blocked");
        }
        if(!user.isActive()){
         throw new AccountNotVerify("Account has not been verified");
        }else{
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRole(user.getRole());
            userResponseDTO.setToken(tokenHandler.generateToken(user));
            userResponseDTO.setId(user.getId());
            userResponseDTO.setName(user.getName());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setAvt(user.getAvt());
            userResponseDTO.setPostQuantity(user.getPostQuantity());
            userResponseDTO.setArtworks(user.getArtworks());
            userResponseDTO.setWallet(user.getWallet());
            return userResponseDTO;
        }
    }

    public User signUp(SignUpRequestDTO signUpRequestDTO){
        User user = new User();
        user.setEmail(signUpRequestDTO.getEmail());
        user.setRole(signUpRequestDTO.getRole().toLowerCase().trim().equals("creator")?RoleEnum.CREATOR:RoleEnum.AUDIENCE);
        user.setUsername(signUpRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setName(signUpRequestDTO.getName());
        user.setAvt("https://png.pngtree.com/png-clipart/20200701/original/pngtree-character-default-avatar-png-image_5407167.jpg");
        user.setActive(false);

    try{
        return userRepository.save(user);
    }catch (DataIntegrityViolationException e) {
        if(e.getMessage().contains("user.username_UNIQUE") || e.getMessage().contains("user.UK_sb8bbouer5wak8vyiiy4pf2bx")) throw new DataIntegrityViolationException("Duplicate UserName");
        else  throw new DataIntegrityViolationException("Duplicate Email");
    }
    }

    public User signUpMod(SignUpRequestDTO signUpRequestDTO){
        User user = new User();
        user.setRole(RoleEnum.MOD);
        user.setUsername(signUpRequestDTO.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setActive(true);
        try{
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("user.username_UNIQUE") || e.getMessage().contains("user.UK_sb8bbouer5wak8vyiiy4pf2bx")) throw new DataIntegrityViolationException("Duplicate UserName");
            else  throw new DataIntegrityViolationException("Duplicate Email");
        }
    }

    public User verifyAccount(VerifyRequestDTO verifyRequestDTO) {
         User user = userRepository.findUserById(verifyRequestDTO.getId());
         if(verifyRequestDTO.getEmail().equals(user.getEmail())){
             user.setActive(true);
             Wallet wallet = new Wallet();
             wallet.setUser(user);
             wallet.setBalance(0);
             wallet.setCocMoney(0);
             walletRepository.save(wallet);
         }else{
             throw new AccountNotVerify("Verify failed");
         }
         return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    public  String generateRandomString() {
        return UUID.randomUUID().toString().substring(0,6);
    }

    public User forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
       if(user != null){
           String randomString = generateRandomString();
           String subject = "UserName: " + user.getUsername() +", Password: " + randomString;
           Runnable r = new Runnable() {
               @Override
               public void run() {
                   emailService.sendMail(user,"Your New Infomation",subject);
               }

           };
           new Thread(r).start();
           user.setPassword(passwordEncoder.encode(randomString));
       }else{
      throw new InValidEmail("Email does not exist!!");
       }
       return userRepository.save(user);
    }
}
