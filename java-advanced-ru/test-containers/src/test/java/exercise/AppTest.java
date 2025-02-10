package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("hexlet")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        // Устанавливаем URL базы данных
        registry.add("spring.datasource.url", database::getJdbcUrl);
        // Имя пользователя и пароль для подключения
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
        // Эти значения приложение будет использовать при подключении к базе данных
    }

    @BeforeEach
    public void setUp() throws Exception {
        var responsePost = mockMvc
                .perform(
                        post("/people/saveAll")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("[{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}," +
                                        "{\"firstName\": \"Peter\", \"lastName\": \"Strollers\"}," +
                                        "{\"firstName\": \"Sergey\", \"lastName\": \"Vazquez\"}]")
                );
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
                .perform(
                        post("/people")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"firstName\": \"Annie\", \"lastName\": \"Valtteri\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Annie", "Valtteri");
    }

    @Test
    void testGetPeople() throws Exception {
        var results = mockMvc.perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON)

                ).andReturn()
                .getResponse();

        assertThat(results.getStatus()).isEqualTo(200);
        assertThat(results.getContentAsString()).contains("Jackson", "Strollers", "Sergey", "Vazquez");
    }

    @Test
    void testGetPerson() throws Exception {
        var result = mockMvc.perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(result).isNotNull();
        assertThat(result.getContentAsString()).contains("Jackson", "Strollers", "Sergey", "Vazquez");
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = new Person();
        person.setId(2);
        person.setFirstName("Ivan");
        person.setLastName("Vespucci");

        mockMvc.perform(patch("/people/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andReturn();

        var result = mockMvc.perform(get("/people/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString()).contains("Vespucci");
    }

    @Test
    void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/people/1"))
                .andReturn();

        var result = mockMvc.perform(get("/people/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString()).isEmpty();

    }
}
