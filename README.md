# Atividade RPG
Este projeto é um sistema de gerenciamento para um jogo de RPG, desenvolvido com Spring Boot, JPA, e banco de dados H2. Ele permite gerenciar Personagens e Itens Mágicos, com operações CRUD via APIs RESTful. 
Cada personagem pode possuir múltiplos itens mágicos, com restrições específicas, como limite de um amuleto por personagem e validações de força e defesa.

# Tecnologias Utilizadas: Java 21 (ou compatível), Spring Boot, Spring Data JPA, H2 Database, Maven, Postman (para testar as APIs.

# Exemplos no Postman:
- POST : /personagens -- cria um personagem
- POST : /itens -- cria um item mágico
- GET : /personagens/{personagemId}/amuleto -- busca o amuleto do personagem
- DELETE : /personagens/{personagemId}/itens/{itemId} -- deleta um item do personagem
