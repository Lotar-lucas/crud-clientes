package com.dev.lotar.crud_de_clientes.controllers;

import com.dev.lotar.crud_de_clientes.dto.ClientDTO;
import com.dev.lotar.crud_de_clientes.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> findById (@PathVariable long id) {
    ClientDTO clientDTO = clientService.findById(id);
    return ResponseEntity.ok(clientDTO);
  }

  @GetMapping
  public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
    Page<ClientDTO> clientsDTO = clientService.findAll(pageable);
    return ResponseEntity.ok(clientsDTO);
  }

  @PostMapping
  public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO clientDTO) {
    clientDTO = clientService.insert(clientDTO);

    //Decomposição:
    //ServletUriComponentsBuilder.fromCurrentRequest() - Obtém a URI da requisição atual (ex: http://localhost:8080/clients)
    //.path("/{id}") - Adiciona o path template /{id} à URI (ex: http://localhost:8080/clients/{id})
    //.buildAndExpand(clientDTO.getId()) - Substitui {id} pelo ID real do cliente criado (ex: http://localhost:8080/clients/123)
    //.toUri() - Converte para objeto URI
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(clientDTO.getId()).toUri();

    return ResponseEntity.created(uri).body(clientDTO);
  }




}
