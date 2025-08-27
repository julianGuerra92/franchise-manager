package co.com.accenture.infrastructure.persistence.mysql.adapter;

import co.com.accenture.domain.model.Person;
import co.com.accenture.domain.repository.PersonRepository;
import co.com.accenture.infrastructure.persistence.mysql.entity.PersonEntity;
import co.com.accenture.infrastructure.persistence.mysql.mapper.PersonMysqlMapper;
import co.com.accenture.infrastructure.persistence.mysql.repository.ReactivePersonMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepository {

    private final ReactivePersonMongoRepository reactivePersonMongoRepository;
    private final PersonMysqlMapper personMysqlMapper;

    @Override
    public Mono<Person> save(Person person) {
        System.out.println("Saving person: " + person);
//        return reactivePersonMongoRepository.save(personMongoMapper.toEntity(person)).map(personMongoMapper::toDomain);
        PersonEntity entity = personMysqlMapper.toEntity(person);
        System.out.println("Converted to entity: " + entity);
        return reactivePersonMongoRepository
                .save(entity)
                .map(personMysqlMapper::toDomain);
    }


    @Override
    public Mono<Person> findById(Long id) {
        return reactivePersonMongoRepository.findById(id).map(personMysqlMapper::toDomain);
    }
}
