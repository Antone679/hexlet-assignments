package exercise.controller;

import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    public Task createTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getDescription),
                        () -> faker.lorem().sentence(4))
                .supply(Select.field(Task::getCreatedAt), () -> LocalDate.now())
                .create();
    }

    @Test
    public void testShow() throws Exception {
        Task task = createTask();
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains(task.getDescription());
    }

    @Test
    public void testCreate() throws Exception {
        Task task = createTask();

        var result = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn();

        Task afterRequest = om.readValue(result.getResponse().getContentAsString(), Task.class);

        Task returnedFromRepoTask = taskRepository.findById(afterRequest.getId()).get();
        assertThat(returnedFromRepoTask.getDescription()).isEqualTo(task.getDescription());
        assertThat(returnedFromRepoTask.getTitle()).isEqualTo(task.getTitle());
        assertThat(returnedFromRepoTask.getCreatedAt()).isEqualTo(task.getCreatedAt());
    }

    @Test
    public void testUpdate() throws Exception {
        Task newTask = createTask();
        taskRepository.save(newTask);

        var data = new HashMap<>();
        data.put("title", "bellissimo grande");
        data.put("description", "grazzie");

        var result = mockMvc.perform(put("/tasks/" + newTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andReturn();

        Task updatedTask = om.readValue(result.getResponse().getContentAsString(), Task.class);

        assertThat(updatedTask.getTitle()).isEqualTo("bellissimo grande");
        assertThat(updatedTask.getDescription()).isEqualTo("grazzie");
    }
    @Test
    public void testDelete() throws Exception {
        Task newTask = createTask();
        taskRepository.save(newTask);

        mockMvc.perform(delete("/tasks/" + newTask.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    // END
}
