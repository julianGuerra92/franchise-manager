package co.com.accenture.infrastructure.persistence.mysql.repository;

import co.com.accenture.infrastructure.persistence.mysql.entity.PersonEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReactivePersonMongoRepository extends R2dbcRepository<PersonEntity, Long> {
}
