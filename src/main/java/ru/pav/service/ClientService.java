package ru.pav.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.pav.model.ClientModel;
import ru.pav.model.entity.ClientEntity;
import ru.pav.service.dao.intfs.IClientDao;

import java.util.List;
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
            Mono<ClientEntity> clientEntityWithId = clientDao.save(clientEntity);
            return clientEntityWithId!=null;
        }
        if (clientTemp.equals(client)){
            // ничего
            return true;
        }
        else
        {
            concurrentHashMap.put(client.getClientCode(),clientEntity); // обновляем КЕШ
            Mono<ClientEntity> clientEntityWithId = clientDao.save(clientEntity);
            return clientEntityWithId!=null;
        }


    }

    public Mono<Long> getCount()
    {
        return clientDao.getCount();
    }

    public static ClientEntity mapperFromJsonToEntity(ClientModel clientModel) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName(clientModel.getName());
        clientEntity.setMargin(clientModel.getMargin());
        clientEntity.setClientCode(clientModel.getClientCode());
        return clientEntity;
    }

    public void save(List<ClientEntity> listOfCLients) {
        // Mono 0 1
        // Flux 0 N
        //Flux<ClientEntity> flux =
        System.out.println("begin");
        Flux.fromIterable(listOfCLients).parallel().concatMap(
                clientEntity -> {
                  return clientDao.save(clientEntity);
                }

        ).subscribe();

        System.out.println("finish");
        //clientDao.saveList(listOfCLients);
    }
}
