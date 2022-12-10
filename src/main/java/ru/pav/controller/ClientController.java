package ru.pav.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.pav.model.ClientModel;
import ru.pav.model.ClientModelList;
import ru.pav.model.entity.ClientEntity;
import ru.pav.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Value("${propperty.version:not_found}")
    String version;

    @PostMapping
    public ResponseEntity<Boolean> getClient(@RequestBody ClientModel client) {
        try {
            System.out.println(version);
            return ResponseEntity.ok().body(clientService.postClient(client));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(false);
        }
    }

    @PostMapping("/string")
    public ResponseEntity<Boolean> getClient(@RequestBody String client) {
        try {
            System.out.println(client);
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(false);
        }
    }

    @PostMapping("/list")
    public void getList(@RequestBody ClientModelList clientModelList)
    {
       // System.out.println(clientModelList);
        List<ClientEntity> listOfCLients = clientModelList.getClientModelList().stream().map(ClientService::mapperFromJsonToEntity).collect(Collectors.toList());
        clientService.save(listOfCLients);
    }

    @GetMapping("/count")
    public Mono<Long> getCount()
    {
        return clientService.getCount();
    }

}
