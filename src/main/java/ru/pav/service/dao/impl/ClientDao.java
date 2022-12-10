package ru.pav.service.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import ru.pav.model.entity.ClientEntity;
import ru.pav.repository.ClientRepository;
import ru.pav.service.dao.intfs.IClientDao;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientDao implements IClientDao {

    private final ClientRepository clientRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Mono<ClientEntity> save(ClientEntity clientEntity) {
       return clientRepository.save(clientEntity);
    }

    @Override
    @Cacheable(key = "id")
    public Mono<ClientEntity> getById(String id) {
        System.out.println("getById");
        return clientRepository.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveList(List<ClientEntity> listOfCLients) {
        clientRepository.saveAll(listOfCLients);
    }

    @Override
    public Mono<Long> getCount() {
        return clientRepository.count();
    }
}
