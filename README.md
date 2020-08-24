# desafioERP
CRUD sistema ERP, com Java 8+, JPA, JSON, REST, Postgres, Spring, maven, Bean Validation, Spring Jpa

Trata-se de um repositorio com o projeto desafio. Este projeto utiliza banco de dados PostgreSQL, Java 8, Maven , Spring JPA, Spring MVC, REST com JSON, Swagger para documentar os enpoints.

O projeto esta versionado no github no endereço https://github.com/fabiobatista32/desafioERP.git

Segue abaixo as especificações do projeto:

Criar banco de dados com o nome "desafio" para criação das tabelas necessarias para execução do projeto;

Apos executar o projeto maven, o spring boot criará as tabelas automaticamento, caso isso não aconteça poderá executar o script "data.sql" de forma manual incluido no projeto no diretorio "src/main/resources".

Segue o endereço para acessar toda a ducumentação dos serviços disponibilizados, apos executar o projeto

http://localhost:8080/swagger-ui.html

Foram criados os end points para execução dos cruds (item, pedido e itemPedido).

A sequencia de passo será: cadastro de um item; cadastro de pedido com uma lista de itens de pedidos.

O id das tabelas serão gerados automaticamente

Exemplo de cadastro de um item:

http://localhost:8080/api/item      metodo POST

{
  "ativo": true,
  "codigo": "0009",
  "descricao": "Castanha de Barreirinhas",
  "tipo": "PRODUTO",
  "titulo": "CASTANHA",
  "valorUnitario": 7
}

retorno após execução

{
  "data": {
    "uuid": "4152a910-f339-4f19-b732-57fa09cec8b4",
    "codigo": "0009",
    "titulo": "CASTANHA",
    "descricao": "Castanha de Barreirinhas",
    "valorUnitario": 7,
    "tipo": "PRODUTO",
    "ativo": true
  },
  "errors": []
}

Para a criação do pedido não precisamos informar a data e hora

Segue o exemplo de criação de um pedido informado apenas o uuid dos itens em uma lista com susa respectivas quantidades

http://localhost:8080/api/pedido metodo POST

{
  "itens": [
    {
      "item": {
         "uuid": "4152a910-f339-4f19-b732-57fa09cec8b4"
      },
      "quantidade": 10
    }
  ],
  "observacao": "Pedido de Castanha",
  "situacao": "ABERTO",
  "valorDesconto": 0.2
}

Retorno apos execução

{
  "data": {
    "uuid": "ca6b9998-1255-4e92-a7e7-e8afec3feaf5",
    "data": "2020-08-24",
    "hora": "08:25:36.692",
    "observacao": "Pedido de Castanha",
    "valorDesconto": 0.2,
    "valorTotal": 56,
    "itens": [
      {
        "uuid": "100aca05-d182-49e0-a935-b3142dcaa666",
        "item": {
          "uuid": "4152a910-f339-4f19-b732-57fa09cec8b4",
          "codigo": "0009",
          "titulo": "CASTANHA",
          "descricao": "Castanha de Barreirinhas",
          "valorUnitario": 7,
          "tipo": "PRODUTO",
          "ativo": true,
          "hibernateLazyInitializer": {}
        },
        "valorUnitario": 7,
        "quantidade": 10,
        "valorTotal": 70
      }
    ],
    "situacao": "ABERTO"
  },
  "errors": []
}

Todas as regras de negocio estão implementadas no service do projeto, observe que o valor total do produto ja está calculado com o respectivo desconto seguindo as regras de imlementação.


