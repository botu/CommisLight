package ru.pav.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pav.model.ClientModel;
import ru.pav.service.ClientService;

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

}
