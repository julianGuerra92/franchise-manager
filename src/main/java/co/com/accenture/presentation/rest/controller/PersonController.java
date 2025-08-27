package co.com.accenture.presentation.rest.controller;

import co.com.accenture.domain.usecase.CreatePersonUseCase;
import co.com.accenture.domain.usecase.FetchPersonUseCase;
import co.com.accenture.presentation.rest.dto.PersonDTO;
import co.com.accenture.presentation.rest.mapper.PersonDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final CreatePersonUseCase createPersonUseCase;
    private final FetchPersonUseCase fetchPersonUseCase;
    private final PersonDTOMapper personDTOMapper;

    @PostMapping
    Mono<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) {
        System.out.println("Received PersonDTO: " + personDTO);
        return createPersonUseCase.createPerson(personDTOMapper.toDomain(personDTO)).map(personDTOMapper::toDTO);
    }


    @GetMapping(path = "{id}")
    Mono<PersonDTO> fetchPerson(@PathVariable Long id) {
        return fetchPersonUseCase.fetchById(id).map(personDTOMapper::toDTO);
    }
}
