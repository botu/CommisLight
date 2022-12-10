package ru.pav.service.dao.intfs;

import reactor.core.publisher.Mono;
import ru.pav.model.entity.ClientEntity;

import java.util.List;

public interface IClientDao {
    Mono<ClientEntity> save(ClientEntity clientEntity);
    Mono<ClientEntity> getById(String id);

    void saveList(List<ClientEntity> listOfCLients);

    Mono<Long> getCount();
}
