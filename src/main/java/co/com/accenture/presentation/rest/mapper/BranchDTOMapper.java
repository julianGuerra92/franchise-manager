package co.com.accenture.presentation.rest.mapper;

import co.com.accenture.domain.model.Branch;
import co.com.accenture.presentation.rest.dto.BranchDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BranchDTOMapper {
    Branch toDomain(BranchDTO branchDTO);
    BranchDTO toDTO(Branch branch);
}
