package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ContactInfoServiceImpl{

    @Autowired
    ContactInformationRepository contactInformationRepository;

    public ContactInfomation findById(Long id) throws Exception {
        Optional<ContactInfomation> contactInfomation = contactInformationRepository.findById(id);
        if (contactInfomation.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return contactInfomation.get();
    }

    public ContactInfomation create(ContactInfomation contactInfomation){
        return contactInformationRepository.save(contactInfomation);
    }
}
