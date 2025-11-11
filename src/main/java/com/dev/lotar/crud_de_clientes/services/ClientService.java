package com.dev.lotar.crud_de_clientes.services;

import com.dev.lotar.crud_de_clientes.dto.ClientDTO;
import com.dev.lotar.crud_de_clientes.entities.Client;
import com.dev.lotar.crud_de_clientes.repositories.ClientRepository;
import com.dev.lotar.crud_de_clientes.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  public ClientService (ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  private void copyDtoToEntity (ClientDTO clientDTO, Client entity) {
    entity.setName(clientDTO.getName());
    entity.setCpf(clientDTO.getCpf());
    entity.setIncome(clientDTO.getIncome());
    entity.setBirthDate(clientDTO.getBirthDate());
    entity.setChildren(clientDTO.getChildren());
  }

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {
    Client client = clientRepository.findById(id).orElseThrow(
      () -> new ResourceNotFoundException("Client not found")
    );
    return new ClientDTO(client);
  }

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAll(Pageable pageable) {
    Page<Client> clients = clientRepository.findAll(pageable);
    return clients.map(ClientDTO::new);
  }

  @Transactional
  public ClientDTO insert(ClientDTO clientDTO) {
    Client client = new Client();
    copyDtoToEntity(clientDTO, client);
    client = clientRepository.save(client);
    return new ClientDTO(client);
  }

  @Transactional
  public ClientDTO update(Long id, ClientDTO clientDTO) {

    try {
      Client entity = clientRepository.getReferenceById(id);
      copyDtoToEntity(clientDTO, entity);
      entity = clientRepository.save(entity);
      return new ClientDTO(entity);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Client not found");
    }
  }

  public void delete(Long id){
    if (!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException("Client not found");
    }

    clientRepository.deleteById(id);
  }
}
