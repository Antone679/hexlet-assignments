package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    // Автоматически заполняем значение поля
    private final PersonRepository personRepository;

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    @PostMapping(path = "")
    public void createPerson(@RequestBody Person person) {
        this.personRepository.save(person);
    }

    @PostMapping(path = "/saveAll")
    public void createPerson(@RequestBody Iterable<Person> persons) {
        this.personRepository.saveAll(persons);
    }

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {

        return this.personRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        this.personRepository.deleteById(id);
    }

    @PatchMapping(path = "/{id}")
    public void updatePerson(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        this.personRepository.save(person);
    }
}
