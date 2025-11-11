package com.dev.lotar.crud_de_clientes.dto;

import com.dev.lotar.crud_de_clientes.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class ClientDTO {

  private Long id;

  @NotBlank(message = "Field name is required")
  private String name;

  private String cpf;

  private Double income;

  @PastOrPresent(message = "Field birthDate cannot be in the future or past")
  private LocalDate birthDate;

  private Integer children;

  public ClientDTO () {}

  public ClientDTO(Client entity) {
    id = entity.getId();
    name = entity.getName();
    cpf = entity.getCpf();
    income = entity.getIncome();
    birthDate = entity.getBirthDate();
    children = entity.getChildren();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public Double getIncome() {
    return income;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public Integer getChildren() {
    return children;
  }
}
