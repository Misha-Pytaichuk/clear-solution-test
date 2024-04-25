package clearsolution.pytaichuk.test;

import clearsolution.pytaichuk.test.controller.UserController;
import clearsolution.pytaichuk.test.dto.AddressDTO;
import clearsolution.pytaichuk.test.dto.UserDTO;
import clearsolution.pytaichuk.test.dto.request.UserBirthRange;
import clearsolution.pytaichuk.test.dto.request.UserEmail;
import clearsolution.pytaichuk.test.model.User;
import clearsolution.pytaichuk.test.repository.UserRepository;
import clearsolution.pytaichuk.test.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    private final ObjectMapper mapper = config();

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    void createUser_ValidUser_ReturnsCreated() throws Exception {
        UserDTO userDTO = new UserDTO("test1@example.com", "John", "Doe", LocalDate.of(2002, 2, 2), new AddressDTO("Country", "City", "Street", "123"), "+123456789013");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mapper, userDTO)))
                .andExpect(status().isCreated());

    }

    @Order(2)
    @Test
    void updateUser_ValidUser_ReturnsOk() throws Exception {
        User user = userRepository.findUserByEmail("test1@example.com").get();

        Long userId = user.getId();

        UserDTO userDTO = new UserDTO("test2@example.com", "John", "Doe", LocalDate.of(2002, 2, 2), new AddressDTO("Country", "City", "Street", "123"), "+123456789013");

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/user/update/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mapper, userDTO)))
                .andExpect(status().isOk());
    }

    @Order(3)
    @Test
    void getUsersByBirthRange_ValidRange_ReturnsOk() throws Exception {
        LocalDate from = LocalDate.now().minusYears(30);
        LocalDate to = LocalDate.now();

        UserBirthRange userBirthRange = new UserBirthRange(from, to);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mapper, userBirthRange)))
                .andExpect(status().isOk());
    }

    @Order(4)
    @Test
    void deleteUser_ValidEmail_ReturnsOk() throws Exception {
        UserEmail userEmail = new UserEmail("test2@example.com");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mapper, userEmail)))
                .andExpect(status().isOk());
    }


    private String asJsonString(ObjectMapper mapper,final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper config(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
