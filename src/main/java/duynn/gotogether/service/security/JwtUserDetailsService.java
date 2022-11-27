package duynn.gotogether.service.security;

import duynn.gotogether.dto.entity_dto.UserDTO;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.place.Location;
import duynn.gotogether.repository.ClientRepository;
import duynn.gotogether.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Optional;

/**JWTUserDetailsService implements the Spring Security UserDetailsService interface.
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 * The Spring Security Authentication Manager calls this method for getting the user details from
 * the database when authenticating the user details provided by the user.
 * Here we are getting the user details from a hardcoded User List.
 * In the next tutorial we will be adding the DAO implementation for fetching User Details
 * from the Database. Also, the password for a user is stored in encrypted format using BCrypt.
 * Previously we have seen Spring Boot Security - Password Encoding Using Bcrypt.
 * Here using the Online Bcrypt Generator you can generate the Bcrypt for a password.
 * */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;
//    @Autowired
//    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<duynn.gotogether.entity.User> user = userRepository.findUserByAccount_Username(s);
        if (user.isPresent()) {
            return new User(s,user.get().getAccount().getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
    }

    public duynn.gotogether.entity.User findUserByUsername(String s) throws UsernameNotFoundException {
        Optional<duynn.gotogether.entity.User> user = userRepository.findUserByAccount_Username(s);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
    }
    public Client findClientByUsername(String username) {
        Optional<duynn.gotogether.entity.Client> client = clientRepository.findClientByAccount_Username(username);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    public boolean save(UserDTO user) {
        System.out.println(user.toString());
        Client client = new Client();
        ReflectionUtils.shallowCopyFieldState(modelMapper.map(user, duynn.gotogether.entity.User.class), client);
        client.setRate(0.0);
        client.setInTrip(false);
        client.setLocation(new Location());
//        duynn.gotogether.entity.User userDAO = modelMapper.map(user, duynn.gotogether.entity.User.class);
//        duynn.gotogether.entity.User user1 = userRepository.save(
//                modelMapper.map(user, duynn.gotogether.entity.User.class));
        Client res = clientRepository.save(client);
        return res != null;
    }

    public boolean isExist(String username){
        Optional<duynn.gotogether.entity.User> user = userRepository.findUserByAccount_Username(username);
        return user.isPresent();
    }


}
