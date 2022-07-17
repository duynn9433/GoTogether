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
public class UserServiceImpl implements GeneralService<User> {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    FullnameRepository fullnameRepository;
    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<User> findAll() throws Exception {
        List<User> users = userRepository.findByIsActiveIsTrue();
        return users;
    }

    @Override
    public User findById(Long id) throws Exception {
        Optional<User> user = userRepository.findByIdAndIsActiveIsTrue(id);
        if (!user.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return user.get();
    }

    @Override
    public User create(User user) throws Exception {
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public int delete(Long id) {
        return userRepository.deleteUserById(id);
    }
}
