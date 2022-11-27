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
public class FullnameServiceImpl{

    @Autowired
    FullnameRepository fullnameRepository;

    public Fullname findById(Long id) throws Exception {
        Optional<Fullname> fullname = fullnameRepository.findById(id);
        if(fullname.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return fullname.get();
    }

    public Fullname create(Fullname fullname) {
        return fullnameRepository.save(fullname);
    }
}
