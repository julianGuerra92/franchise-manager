package co.com.accenture.infrastructure.persistence.mysql.repository;

import co.com.accenture.infrastructure.persistence.mysql.entity.BranchEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface BranchMysqlRepository extends R2dbcRepository<BranchEntity, Long> {
}
