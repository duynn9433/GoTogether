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
public class ContactInfoServiceImpl implements GeneralService<ContactInfomation> {

    @Autowired
    ContactInformationRepository contactInfomationRepository;

    @Override
    public List<ContactInfomation> findAll() throws Exception {
        List<ContactInfomation> contactInfomations = contactInfomationRepository.findAll();
        if (contactInfomations.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return contactInfomations;
    }

    @Override
    public ContactInfomation findById(Long id) throws Exception {
        Optional<ContactInfomation> contactInfomation = contactInfomationRepository.findById(id);
        if (!contactInfomation.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu");
        }
        return contactInfomation.get();
    }

    @Override
    public ContactInfomation create(ContactInfomation contactInfomation) throws Exception {
        contactInfomationRepository.save(contactInfomation);
        return contactInfomation;
    }

    @Override
    public ContactInfomation update(ContactInfomation contactInfomation) throws Exception {
        contactInfomationRepository.save(contactInfomation);
        return contactInfomation;
    }

    @Override
    public int delete(Long id) {
        return contactInfomationRepository.deleteContactInformationById(id);
    }
}
