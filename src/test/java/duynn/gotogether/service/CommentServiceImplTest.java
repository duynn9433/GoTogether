package duynn.gotogether.service;

import duynn.gotogether.entity.Comment;
import duynn.gotogether.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentServiceImpl commentService;
    @MockBean(name = "commentRepository")
    CommentRepository commentRepository;

    @Test
    @Transactional
    @Rollback
    void testCreate() throws Exception {
        // Setup
        final Comment comment = Comment.builder().build();
        final Comment expectedResult = Comment.builder().build();
        when(commentRepository.save(Comment.builder().build())).thenReturn(Comment.builder().build());

        // Run the test
        final Comment result = commentService.create(comment);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
    @Test
    void testGetCommentsByDriverId() throws Exception {
        // Setup
        final List<Comment> expectedResult = List.of(Comment.builder().build());
        when(commentRepository.findCommentByReceiverId(0L)).thenReturn(List.of(Comment.builder().build()));

        // Run the test
        final List<Comment> result = commentService.getCommentsByDriverId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCommentsByDriverId_ThrowsException() {
        // Setup
        when(commentRepository.findCommentByReceiverId(0L)).thenReturn(new ArrayList<>());

        // Run the test
        assertThatThrownBy(() -> commentService.getCommentsByDriverId(0L)).isInstanceOf(Exception.class);
    }
}