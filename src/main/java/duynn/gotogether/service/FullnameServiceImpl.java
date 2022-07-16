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
public class FullnameServiceImpl implements GeneralService<Fullname> {

    @Autowired
    FullnameRepository fullnameRepository;

    @Override
    public List<Fullname> findAll() throws Exception {
        List<Fullname> fullnames = fullnameRepository.findAll();
        if(fullnames.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return fullnames;
    }

    @Override
    public Fullname findById(Long id) throws Exception {
        Optional<Fullname> fullname = fullnameRepository.findById(id);
        if(!fullname.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return fullname.get();
    }

    @Override
    public Fullname create(Fullname fullname) throws Exception {
        return fullnameRepository.save(fullname);
    }

    @Override
    public Fullname update(Fullname fullname) throws Exception {
        return fullnameRepository.save(fullname);
    }

    @Override
    public int delete(Long id) {
        return fullnameRepository.deleteFullnameById(id);
    }
}
