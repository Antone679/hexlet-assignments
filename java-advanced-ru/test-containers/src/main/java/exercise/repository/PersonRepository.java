package exercise.repository;

import exercise.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findById(long id);
    <S extends Person> Iterable<S> saveAll(Iterable<S> persons);
}
