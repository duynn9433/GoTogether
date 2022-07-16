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
public class CommentServiceImpl implements GeneralService<Comment> {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TripRepository tripRepository;

    @Override
    public List<Comment> findAll() throws Exception {
        List<Comment> comments = commentRepository.findAll();
//        for (Comment comment : comments) {
//            comment.setClient(clientRepository.findById(comment.getClientId()));
//            comment.setTrip(tripRepository.findById(comment.getTripId()));
//        }
        return comments;
    }

    @Override
    public Comment findById(Long id) throws Exception {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
//            comment.get().setClient(clientRepository.findById(comment.get().getClientId()));
//            comment.get().setTrip(tripRepository.findById(comment.get().getTripId()));
            return comment.get();
        }else{
            throw new Exception("Không tìm thấy comment");
        }
    }

    @Override
    public Comment create(Comment comment) throws Exception {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) throws Exception {
        return commentRepository.save(comment);
    }

    @Override
    public int delete(Long id) {
        return commentRepository.deleteCommentById(id);
    }
}
