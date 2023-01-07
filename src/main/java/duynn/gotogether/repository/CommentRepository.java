package duynn.gotogether.repository;

import duynn.gotogether.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Modifying
    int deleteCommentById(Long id);

    List<Comment> findCommentByReceiverId(Long receiverId);

    List<Comment> findAllByReceiverId(Long id);
    List<Comment> findAllBySenderId(Long id);

    @Query(value = "call go_together.update_average_rate_with_plus_rating(?1, ?2);", nativeQuery = true)
    void updateClientRate(Long id, Integer plusRating);
}