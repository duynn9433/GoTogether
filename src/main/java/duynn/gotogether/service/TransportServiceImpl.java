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
public class TransportServiceImpl implements GeneralService<Transport> {

    @Autowired
    TransportRepository transportRepository;

    @Override
    public List<Transport> findAll() throws Exception {
        List<Transport> transports = transportRepository.findAll();
        if (transports.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu phương tiện");
        }
        return transports;
    }

    @Override
    public Transport findById(Long id) throws Exception {
        Optional<Transport> transport = transportRepository.findById(id);
        if (!transport.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu phương tiện");
        }
        return transport.get();
    }

    @Override
    public Transport create(Transport transport) throws Exception {
        transportRepository.save(transport);
        return transport;
    }

    @Override
    public Transport update(Transport transport) throws Exception {
        transportRepository.save(transport);
        return transport;
    }

    @Override
    public int delete(Long id) {
        return transportRepository.deleteTransportById(id);
    }

    public List<Transport> getTransportByUserId(Long id) {
        return transportRepository.getTransportByOwnerId(id);
    }
}
