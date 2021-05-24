package com.study.study.repository;

import com.study.study.StudyApplicationTests;
import com.study.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void create(){

        User user = User.builder().id(1L).account("Test1").email("992mseven@naver.com").phoneNumber("01027626870")
                .createdAt(LocalDateTime.now()).createdBy("admin").build();

        when(userRepository.save(user)).thenReturn(user);

        user = userRepository.save(user);

        System.out.println(user);
        assertThat(user.getCreatedBy()).isEqualTo("admin");
    }
}

//class UserRepositoryTest extends StudyApplicationTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void create(){
//
//        User user = User.builder().account("Test1").email("992mseven@naver.com").phoneNumber("01027626870")
//                .createdAt(LocalDateTime.now()).createdBy("admin").build();
//        user = userRepository.save(user);
//
//        System.out.println(user);
//        assertThat(user.getCreatedBy()).isEqualTo("admin");
//    }
//}