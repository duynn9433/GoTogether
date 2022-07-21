package duynn.gotogether.service;

import duynn.gotogether.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentServiceImpl commentService;
    @Autowired
    ClientServiceImpl clientService;

    Comment comment;
    Client clientData;

    @BeforeEach
    void setUp() throws Exception {
        comment = Comment.builder()
                .content("content")
                .Author(Client.builder().id(1L).build())
                .Receiver(Client.builder().id(1L).build())
                .rating(1)
                .trip(Trip.builder().id(1L).build())
                .build();
        clientData = Client.builder()
                .avatar("avatar")
                .description("description")
                .role("role")
                .isActive(true)
                .account(Account.builder()
                        .username("duynn")
                        .password("password")
                        .build())
                .contactInfomation(ContactInfomation.builder()
                        .email("duynn@mail.com")
                        .phoneNumber("0966215413")
                        .build())
                .fullname(Fullname.builder()
                        .firstName("duy")
                        .lastName("nn")
                        .build())
                .address(Address.builder()
                        .city("HCM")
                        .district("Q1")
                        .province("province")
                        .detail("detail")
                        .build())
                .position(Position.builder()
                        .latitude(10.0)
                        .longitude(10.0)
                        .build())
                .rate(10.0)
                .isInTrip(false)
                .build();
        clientService.create(clientData);
        clientData.getAccount().setUsername("duynn2");
        clientService.create(clientData);
    }

    @Test
    @Transactional
    @Rollback
    void findAll() throws Exception {
        commentService.create(comment);
        List<Comment> comments = commentService.findAll();
        assertNotNull(comments);
    }

    @Test
    @Transactional
    @Rollback
    void findById() throws Exception {
        Comment expected =commentService.create(comment);
        assertNotNull(expected);
        Comment actual = commentService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Comment expected =commentService.create(comment);
        Comment actual = commentService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
        Comment expected =commentService.create(comment);
        Comment actual = commentService.findById(expected.getId());
        assertEquals(expected, actual);
        expected.setContent("new content");
        commentService.update(expected);
        actual = commentService.findById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        Comment expected =commentService.create(comment);
        Comment actual = commentService.findById(expected.getId());
        assertEquals(expected, actual);
        commentService.delete(expected.getId());
        actual = commentService.findById(expected.getId());
        assertNull(actual);
    }
}