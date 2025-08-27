package co.com.accenture.domain.repository;

import co.com.accenture.domain.model.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> save(Person person);

    Mono<Person> findById(Long id);

}