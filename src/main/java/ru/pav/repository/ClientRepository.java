package ru.pav.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.pav.model.entity.ClientEntity;

public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, String> {
}
