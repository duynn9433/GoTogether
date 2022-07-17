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
public class PositionServiceImpl implements GeneralService<Position> {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public List<Position> findAll() throws Exception {
        List<Position> positions = positionRepository.findAll();
        if(positions.isEmpty()) {
            throw new Exception("Không tìm thấy dữ liệu vị trí");
        }
        return positions;
    }

    @Override
    public Position findById(Long id) throws Exception {
        Optional<Position> position = positionRepository.findById(id);
        if(!position.isPresent()) {
            throw new Exception("Không tìm thấy dữ liệu vị trí");
        }
        return position.get();
    }

    @Override
    public Position create(Position position) throws Exception {
        return positionRepository.save(position);
    }

    @Override
    public Position update(Position position) throws Exception {
        return positionRepository.save(position);
    }

    @Override
    public int delete(Long id) {
        return positionRepository.deletePositionById(id);
    }
}
