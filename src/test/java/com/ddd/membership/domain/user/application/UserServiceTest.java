package com.ddd.membership.domain.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.ActiveProfiles;

import com.ddd.membership.domain.user.dao.UserRepository;
import com.ddd.membership.domain.user.domain.User;
import com.ddd.membership.domain.user.dto.UserDto;

@TestInstance(PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("UserService Unit Tests")
public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeAll
    public void init() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @DisplayName("[Service] 통합 바코드 발급 테스트")
    @Test
    void testCreateBarcode() {
        //given
        Long existingUserId = 1L;

        User user = User
                    .builder()
                    .id(existingUserId)
                    .name("이경민")
                    .pwd("abc123!")
                    .build();

        when(userRepository.findById(existingUserId)).thenReturn(Optional.of(user));
        //when
        
        String barcodeNo = userService.createBarcode(existingUserId);
        //then
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(barcodeNo);
        assertNotEquals(barcodeNo, "1234567890");
    }

    @DisplayName("[Service] 유저 생성 테스트")
    @Test
    void testCreateUser() {

        //given
        UserDto userDto = UserDto
                          .builder()
                          .id(1L)
                          .name("헬로")
                          .pwd("12345")
                          .build();

        User user = User
                    .builder()
                    .id(1L)
                    .name("헬로")
                    .pwd("12345")
                    .build();

        //when
        when(userRepository.save(any(User.class))).thenReturn(user);
        Long userId = userService.createUser(userDto);
        
        //then
        assertNotNull(user);
        assertEquals(user.getId(), userId);
    }
}
