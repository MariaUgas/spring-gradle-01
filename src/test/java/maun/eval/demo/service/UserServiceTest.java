package maun.eval.demo.service;

import maun.eval.demo.dto.UserDto;
import maun.eval.demo.entity.User;
import maun.eval.demo.repository.PhoneRepository;
import maun.eval.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    PhoneRepository phoneRepository;

    @Autowired
    UserService userService;

    @Test
    void registerUserTest() {
        UUID idUser = UUID.randomUUID();
        User user = new User(
                null, "Maria U", "test@example.com",
                "P1234ext", LocalDateTime.now(), LocalDateTime.now(),
                true, "userToken", new ArrayList<>());
        when(userRepository.saveAndFlush(any())).then(invocation -> {
            User u= invocation.getArgument(0);
            u.setId(idUser);
            return u;
        });

        UserDto userDto = userService.registerUser(user);

        assertEquals(idUser, userDto.getId());



    }
}