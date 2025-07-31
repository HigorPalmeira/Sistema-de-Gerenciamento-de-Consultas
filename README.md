# Sistema de Gerenciamento de Consultas
Sistema para gerenciar consultas.

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