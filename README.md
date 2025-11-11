# CRUD de Clientes

Projeto desenvolvido durante o curso da **DevSuperior** para aprendizado de desenvolvimento de APIs REST com Spring Boot.

## 📋 Sobre o Projeto

API REST para gerenciamento de clientes (CRUD completo) utilizando Spring Boot, Spring Data JPA e banco de dados H2.

### Funcionalidades

- ✅ Buscar cliente por ID
- ✅ Listar todos os clientes (paginado)
- ✅ Inserir novo cliente
- ✅ Atualizar cliente existente
- ✅ Deletar cliente

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (banco em memória para testes)
- **Maven**

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 17 ou superior instalado
- Maven instalado (ou usar o Maven Wrapper incluído no projeto)

### Passo a Passo

1. **Clone ou acesse o diretório do projeto**
   ```bash
   cd crud-de-clientes
   ```

2. **Execute o projeto com Maven**
   
   No Windows (CMD):
   ```cmd
   mvnw.cmd spring-boot:run
   ```
   
   No Linux/Mac ou WSL:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse a aplicação**
   
   A API estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### Listar todos os clientes (paginado)
```http
GET http://localhost:8080/clients?page=0&size=10
```

### Buscar cliente por ID
```http
GET http://localhost:8080/clients/{id}
```

### Criar novo cliente
```http
POST http://localhost:8080/clients
Content-Type: application/json

{
  "name": "Maria Silva",
  "cpf": "12345678900",
  "income": 5000.0,
  "birthDate": "1990-01-15",
  "children": 2
}
```

### Atualizar cliente
```http
PUT http://localhost:8080/clients/{id}
Content-Type: application/json

{
  "name": "Maria Silva Santos",
  "cpf": "12345678900",
  "income": 5500.0,
  "birthDate": "1990-01-15",
  "children": 2
}
```

### Deletar cliente
```http
DELETE http://localhost:8080/clients/{id}
```

## 🗄️ Banco de Dados H2

O projeto utiliza o H2 Database (banco em memória) para testes.

- **Console H2**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(deixe em branco)*

O banco já vem populado com 10 clientes de exemplo através do arquivo `import.sql`.

## 📝 Formato de Data

As datas devem ser enviadas no formato **ISO 8601**: `YYYY-MM-DD`

Exemplo: `"1994-07-20"`

## 👨‍💻 Autor

Projeto desenvolvido como parte do curso **DevSuperior** por Lucas Lotar.

---

**Nota**: Este é um projeto de estudo. O banco de dados H2 é volátil e os dados são perdidos ao reiniciar a aplicação.

