package co.com.accenture.domain.usecase;

import co.com.accenture.domain.model.Person;
import co.com.accenture.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FetchPersonUseCase {

    private final PersonRepository personRepository;

    public Mono<Person> fetchById(Long id) {
        return personRepository.findById(id);
    }

}
