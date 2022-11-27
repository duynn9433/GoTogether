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
public class TransportServiceImpl {
    @Autowired
    TransportRepository transportRepository;
    public Transport findById(Long id) throws Exception {
        Optional<Transport> transport = transportRepository.findById(id);
        if (transport.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu phương tiện");
        }
        return transport.get();
    }
    public Transport create(Transport transport) {
        transportRepository.save(transport);
        return transport;
    }
    public List<Transport> getTransportByUserId(Long id) throws Exception {
        List<Transport> transports = transportRepository.getTransportByOwnerId(id);
        if (transports.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu phương tiện");
        }
        return transports;
    }
}
