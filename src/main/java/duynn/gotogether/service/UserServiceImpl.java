package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl{
    @Autowired
    UserRepository userRepository;
    public User findById(Long id) throws Exception {
        Optional<User> user = userRepository.findByIdAndIsActiveIsTrue(id);
        if (user.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return user.get();
    }
    public User create(User user){
        user.setActive(true);
        return userRepository.save(user);
    }
}
