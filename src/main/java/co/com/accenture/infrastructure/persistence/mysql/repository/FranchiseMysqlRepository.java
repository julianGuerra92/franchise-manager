package co.com.accenture.infrastructure.persistence.mysql.repository;

import co.com.accenture.infrastructure.persistence.mysql.entity.FranchiseEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FranchiseMysqlRepository extends R2dbcRepository<FranchiseEntity, Long> {
}
