package com.example.carfix.carfixspringboot;

import com.example.carfix.carfixspringboot.entities.User;
import com.example.carfix.carfixspringboot.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarfixSpringBootApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class CarfixSpringBootApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Mock
    UserRepository mockUserRepository;

    @Test
    void findByUsernameTest() {

        mockUserRepository.findByUsername("donliskas");

        verify(mockUserRepository, atLeastOnce()).findByUsername(anyString());

    }

    @Test
    void existsByUsernameTest() {

        Boolean exists = userRepository.existsByUsername("donliskas");

        Optional<User> users = userRepository.findByUsername("donliskas");

        assertTrue(exists);

        assertEquals("donliskas", users.get().getUsername());

    }

    @Test
    void existsByEmailTest() {

        Boolean exists = userRepository.existsByEmail("donliskas@gmail.com");

        Optional<User> users = userRepository.findByEmail("donliskas@gmail.com");

        assertTrue(exists);

        assertEquals("donliskas@gmail.com", users.get().getEmail());

    }

    @Test
    void existsByPhoneNumberTest() {

        Boolean exists = userRepository.existsByPhoneNumber("+370-63855525");

        Optional<User> users = userRepository.findByPhoneNumber("+370-63855525");

        assertTrue(exists);

        assertEquals("+370-63855525", users.get().getPhoneNumber());


    }

    @Test
    @Tag("gets")
    @DisplayName("GET the models by price MVC API test")
    void authorizationSigningTest() throws Exception {
            mvc.perform(get("/api/authorization/signin").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void publicContentTest() throws Exception {
        mvc.perform(get("/api/carfix/admin").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());

    }
}



