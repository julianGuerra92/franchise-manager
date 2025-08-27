package co.com.accenture.presentation.rest.mapper;

import co.com.accenture.domain.model.Person;
import co.com.accenture.presentation.rest.dto.PersonDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonDTOMapper {
    Person toDomain(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}