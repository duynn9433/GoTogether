package duynn.gotogether.repository;

import duynn.gotogether.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    int deleteCommentById(Long id);
}