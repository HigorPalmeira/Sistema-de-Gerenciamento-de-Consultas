# Sistema de Gerenciamento de Consultas
Sistema para gerenciar consultas.

## Menu

* [Sobre](#sobre)

* [Entidades](#entidades)

* [Tabelas do Banco de Dados](#tabelas-do-banco-de-dados)

## Sobre

O projeto foi desenvolvido como composição de nota para a matéria de `Programação Orientada a Objetos - POO`. Ele possui os seguintes requisitos avaliativos: 

* O projeto deve possuir pelo menos duas entidades/tabelas, no banco de dados, com um relacionamento entre elas.

* Deverá ser entregue primeiramente o back-end, como parte inicial do projeto.

* Por último, deverá ser entregue o front-end, como projeto finalizado.

O `Sistema de Gerenciamento de Consultas - SGC` é pensado como um sistema para gerenciar consultas. Consequentemente ele vai gerir dados de `pacientes` e `médicos`. Ele está sendo desenvolvido como um sistema de gerenciamento básico.

O `SGC` foi desenvolvido usando `Java` e o `MySQL` no `Docker`. O Back-end foi feito com o `Spring Boot` e o Front-end com o `Java Swing`.

## Entidades

* Foram criadas 5 entidades para o projeto, sendo elas:
  * Consulta
  * Médico
  * Paciente
  * Especialidade
  * Endereço

## Tabelas do Banco de Dados

* Tabela `sgc_endereco`

```sql
id : binary(16)
bairro : varchar(255)
cep : varchar(8)
complemento : varchar(255)
localidade : varchar(255)
rua : varchar(255)
uf : varchar(2)
creation_timestamp : datetime(6)
update_timestamp : datetime(6)
```

* Tabela `sgc_especialidade`

```sql
id : binary(16)
creation_timestamp : datetime(6)
descrição : varchar(255)
update_timestamp : datetime(6)
```

* Tabela `sgc_medico`

```sql
id : binary(16)
creation_timestamp : datetime(6)
crm : varchar(11)
email : varchar(255)
nome : varchar(128)
sobrenome : varchar(255)
status : enum('ATIVO', 'INATIVO')
telefone : varchar(11)
update_timestamp : datetime(6)
especialidade_id : binary(16)
```

* Tabela `sgc_paciente`

```sql
id : binary(16)
cpf : varchar(11)
data nascimento : date
email : varchar(255)
gênero : enum('FEMININO', 'MASCULINO', 'NAO_DEFINIDO')
nome : varchar(128)
sobrenome : varchar(255)
status : enum('ATIVO', 'INATIVO')
telefone : varchar(11)
endereco_id : binary(16)
creation_timestamp : datetime(6)
update_timestamp : datetime(6)
```

* Tabela `sgc_consulta`

```sql
id : binary(16)
creation_timestamp : datetime(6)
data hora : datetime(6)
observações : varchar(255)
status : enum('AGENDADA', 'CANCELADA', 'FALTA', 'INATIVA', 'REALIZADA', 'REAGENDADA')
update_timestamp : datetime(6)
valor : decimal(10, 2)
medico_id : binary(16)
paciente_id : binary(16)
```