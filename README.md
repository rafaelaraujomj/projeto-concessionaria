# Sistema de Gerenciamento de Concessionária

Projeto desenvolvido em Java para simular o gerenciamento completo de uma concessionária de veículos. O sistema permite o controle de estoque, cadastro de clientes, registro de vendas financeiras e recomendação de produtos. 

Este projeto foi construído com foco na aplicação de boas práticas de Programação Orientada a Objetos (POO).

## Funcionalidades

* **Gestão de Estoque:** Entrada e saída de diferentes tipos de veículos (Carros, Motos e Caminhões).
* **Gestão de Clientes:** Cadastro de clientes contendo dados de contato e características físicas.
* **Sistema de Vendas:** Registro de transações vinculando um cliente a um veículo do estoque.
* **Análise Financeira:** Geração de relatórios de vendas com cálculo automático de lucro, perda ou venda a preço de custo.
* **Recomendação Inteligente:** Algoritmo que sugere o tipo de veículo mais adequado com base no biotipo (altura e peso) do cliente selecionado.

## Conceitos de POO Aplicados

O código foi estruturado para demonstrar domínio sobre os pilares da Orientação a Objetos:

* **Herança e Classes Abstratas:** A classe mãe `Veiculo` concentra os atributos comuns, sendo herdada por `Carro`, `Moto` e `Caminhao`.
* **Polimorfismo:** Unificação da exibição de informações e listagem de estoque através de uma lista genérica `List<Veiculo>`, permitindo que o sistema trate diferentes objetos de forma dinâmica.
* **Interfaces:** Implementação de contratos de comportamento (como ligar, desligar, acelerar) aplicáveis a todos os tipos de veículos.
* **Encapsulamento:** Proteção de dados sensíveis através de modificadores de acesso e métodos Getters/Setters.
* **Tratamento de Exceções:** Implementação de exceções personalizadas (`VendaInvalidaException` e `EstoqueVazioException`) para garantir a integridade das regras de negócio (ex: impedir vendas com valor negativo).


## Estrutura do Projeto

O projeto é composto pelas seguintes classes principais:

* `Main.java`: Ponto de entrada da aplicação, contendo os menus interativos e a inicialização das listas.
* `Veiculo.java` (Classe Abstrata) e `InterfaceVeiculo.java` (Interface).
* `Carro.java`, `Moto.java`, `Caminhao.java`: Especializações da classe Veiculo.
* `Pessoa.java`: Representação dos clientes da concessionária.
* `Venda.java`: Classe responsável por processar a lógica financeira e o relacionamento entre Cliente e Veículo.
