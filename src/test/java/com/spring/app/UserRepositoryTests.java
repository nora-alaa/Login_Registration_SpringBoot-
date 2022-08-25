package com.spring.app;


import com.spring.app.entity.User;
import com.spring.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

//    @Autowired
//    private TestEntityManager entityManager ;

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("nora@nora.com");
        user.setPassword("nora");
        user.setFirstName("nora");
        user.setLastName("alaa");
        user.setUserName("alaa");
        user.setPhone("01012345768");


        User savedUser = repo.save(user);

//        User existUser = entityManager.find(User.class, savedUser.getId());

//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());


    }

    @Test
    public void testFindByEmail() {
        String email = "nora@nora.com";
        User user = repo.findByEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }


}
