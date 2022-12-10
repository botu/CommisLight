package ru.pav.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.pav.model.entity.ClientEntity;

public interface ClientRepository extends ReactiveCrudRepository<ClientEntity, String> {
}
