package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return null;
    }

    @Override
    public User findById(Long id) throws Exception {
        return null;
    }

    @Override
    public User create(User user) throws Exception {
        return null;
    }

    @Override
    public User update(User user) throws Exception {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }
}
