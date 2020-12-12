package com.example.carfix.carfixspringboot;

import com.example.carfix.carfixspringboot.controllers.AuthorizationController;
import com.example.carfix.carfixspringboot.entities.User;
import com.example.carfix.carfixspringboot.payload.request.LoginRequest;
import com.example.carfix.carfixspringboot.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    AuthorizationController authorizationController;

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
    @DisplayName("Testing ")
    void authorizationSigningTest() throws Exception {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("donliskas");
        loginRequest.setPassword("Cinkcilatas5521");

        ResponseEntity<?> responseEntity = authorizationController.authenticateUser(loginRequest);

        assertNotNull(responseEntity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        /*LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("donliskas");
        loginRequest.setPassword("Cinkcilatas5521");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(loginRequest );

        mvc.perform(post("/signin")
                .content(requestJson)
                .accept("application/json;charset=UTF-8"))
                *//*.andExpect(status().isOk())*//*
                .andExpect(status().isUnauthorized());
                *//*.andExpect(content().contentType(APPLICATION_JSON));*/

    }

    @Test
    @DisplayName("Testing public content response")
    void publicContentTest() throws Exception {
        mvc.perform(get("/api/carfix/all").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());

    }

    @DisplayName("Testing private content response")
    @ParameterizedTest
    @ValueSource(strings = {"user", "admin", "mod", })
    void privateContentTest(final String api) throws Exception {
        mvc.perform(get("/api/carfix/" + api).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isUnauthorized());

    }
}



