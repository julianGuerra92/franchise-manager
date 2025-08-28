package co.com.accenture.infrastructure.persistence.mysql.mapper;

import co.com.accenture.domain.model.Branch;
import co.com.accenture.infrastructure.persistence.mysql.entity.BranchEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BranchMysqlMapper {
    Branch toDomain(BranchEntity branchEntity);
    BranchEntity toEntity(Branch branch);
}
