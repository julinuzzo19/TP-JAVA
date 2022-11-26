package auth.springframework.service;

import auth.springframework.dtos.RegisterDTO;
import auth.springframework.exception.UserNotFoundException;
import auth.springframework.model.User;
import auth.springframework.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository);
        user = User.builder()
                .id(1)
                .name("Gustavo")
                .lastName("Ponce")
                .email("test@test.com")
                .password("123456")
                .build();

        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
    }

    @Test
    public void testSaveUser() {
        // Setup
        final String email = "test@test.com";
        RegisterDTO register = new RegisterDTO ("test@test.com","123456","Gustavo", "Ponce");
        // Run the test
        userServiceUnderTest.saveUser(register);

    }
}
