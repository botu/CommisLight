package ru.pav.service.dao.intfs;

import ru.pav.model.entity.ClientEntity;

public interface IClientDao {
    ClientEntity save(ClientEntity clientEntity);
    ClientEntity getById(String id);
}
