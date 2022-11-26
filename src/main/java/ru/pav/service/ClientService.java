package ru.pav.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pav.model.ClientModel;
import ru.pav.model.entity.ClientEntity;
import ru.pav.service.dao.intfs.IClientDao;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class ClientService {


    private final ConcurrentHashMap<String, ClientEntity> concurrentHashMap = new ConcurrentHashMap<>();

    private final IClientDao clientDao;


    public boolean postClient(ClientModel client) {
        ClientEntity clientEntity = new ClientEntity(client);
        ClientEntity clientTemp = concurrentHashMap.get(clientEntity.getClientCode());
        if (clientTemp == null) {
            concurrentHashMap.put(client.getClientCode(),clientEntity); // добавляем в КЕШ
            ClientEntity clientEntityWithId = clientDao.save(clientEntity);
            return clientEntityWithId!=null;
        }
        if (clientTemp.equals(client)){
            // ничего
            return true;
        }
        else
        {
            concurrentHashMap.put(client.getClientCode(),clientEntity); // обновляем КЕШ
            ClientEntity clientEntityWithId = clientDao.save(clientEntity);
            return clientEntityWithId!=null;
        }


    }
}
