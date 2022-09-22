//package duynn.gotogether.service.security;
//
//import duynn.gotogether.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Optional<duynn.gotogether.entity.User> user = userRepository.findUserByAccount_Username(s);
//        if (user.isPresent()) {
//            return new User(s,user.get().getAccount().getPassword(),new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + s);
//        }
//    }
//}
