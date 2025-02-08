package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> findById(long id){
        return userRepository.findById(id);
    }

    public Mono<User> save(User user){
        return userRepository.save(user);
    }
    public Mono<User> update(User user, long id){
       return userRepository.findById(id).flatMap(userToUpdate -> {
           userToUpdate.setEmail(user.getEmail());
           userToUpdate.setFirstName(user.getFirstName());
           userToUpdate.setLastName(user.getLastName());
           return userRepository.save(userToUpdate);
       });
    }

    public Mono<Void> deleteById(long id){
       return userRepository.deleteById(id);

    }
    // END
}
