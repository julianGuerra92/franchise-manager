package co.com.accenture.infrastructure.persistence.mysql.mapper;

import co.com.accenture.domain.model.Person;
import co.com.accenture.infrastructure.persistence.mysql.entity.PersonEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMysqlMapper {
    Person toDomain(PersonEntity personEntity);

    PersonEntity toEntity(Person person);
}
