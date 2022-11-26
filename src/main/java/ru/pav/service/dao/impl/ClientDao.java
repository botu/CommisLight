package ru.pav.service.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.pav.model.entity.ClientEntity;
import ru.pav.repository.ClientRepository;
import ru.pav.service.dao.intfs.IClientDao;

@Component
@RequiredArgsConstructor
public class ClientDao implements IClientDao {

    private final ClientRepository clientRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.saveAndFlush(clientEntity);

    }

    @Override
    @Cacheable(key = "id")
    public ClientEntity getById(String id) {
        System.out.println("getById");
        return clientRepository.getById(id);
    }
}
