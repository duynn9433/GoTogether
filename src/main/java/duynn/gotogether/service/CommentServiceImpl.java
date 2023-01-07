package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import duynn.gotogether.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl{
    @Autowired
    CommentRepository commentRepository;
    public Comment create(Comment comment){
        return commentRepository.save(comment);
    }
    public List<Comment> getCommentsByDriverId(Long driverId) throws Exception {
        List<Comment> comments = commentRepository.findCommentByReceiverId(driverId);
        if(comments.isEmpty()){
            throw new Exception("Không tìm thấy comment");
        }
        return comments;
    }

    public List<Comment> getAllByReceiverId(Long id) {
        return commentRepository.findAllByReceiverId(id);
    }

    public List<Comment> getAllReceivedComment(Long id) {
        return commentRepository.findAllByReceiverId(id);
    }
    public List<Comment> getAllSendComment(Long id) {
        return commentRepository.findAllBySenderId(id);
    }
}
