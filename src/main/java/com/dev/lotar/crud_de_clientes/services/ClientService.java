package com.dev.lotar.crud_de_clientes.services;

import com.dev.lotar.crud_de_clientes.dto.ClientDTO;
import com.dev.lotar.crud_de_clientes.entities.Client;
import com.dev.lotar.crud_de_clientes.repositories.ClientRepository;
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
      // Todo -  tratar erro de not found
      () -> new RuntimeException("Client not found")
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
      //Todo tratar Exception mais especifica
      throw new RuntimeException("Client not found");
    }
  }

  public void delete(Long id){
    if (clientRepository.existsById(id)) {
      //tratar exception de not found
//      throw new RuntimeException("Client not found");
    }

    try {
      clientRepository.deleteById(id);
    }catch (Exception e){
      //tratar exception de integridade referencial
    }
  }


}
