package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Person;
import co.com.accenture.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class CreatePersonUseCase {
    private final PersonRepository personRepository;

    public Mono<Person> createPerson(Person person) {
        System.out.println("Creating person: " + person);
        return personRepository.save(person);
    }
}
