## DESAFIO: CRUD de clientes

<p>Você deverá entregar um projeto Spring Boot contendo um CRUD completo de web services REST para
acessar um recurso de clientes, contendo as cinco operações básicas aprendidas no capítulo:</p>
<ul>
<li> Busca paginada de recursos</li>
<li> Busca de recurso por id</li>
<li> Inserir novo recurso</li>
<li> Atualizar recurso</li>
<li> Deletar recurso</li>
</ul>
<p>O projeto deverá estar com um ambiente de testes configurado acessando o banco de dados H2, deverá usar
Maven como gerenciador de dependência, e Java como linguagem.
Um cliente possui nome, CPF, renda, data de nascimento, e quantidade de filhos. A especificação da
entidade Client é mostrada a seguir (você deve seguir à risca os nomes de classe e atributos mostrados no
diagrama):</p>

![Client](https://github.com/user-attachments/assets/866f2cc3-3e35-4b30-80fd-32a146388da3)


<p><b>Seu projeto deverá fazer um seed de pelo menos 10 clientes</b> com dados SIGNIFICATIVOS (não é para
usar dados sem significado como “Nome 1”, “Nome 2”, etc.).</p>

<p><b>Seu projeto deverá tratar as seguintes exceções:</b></p>
<ul>
  <li> Id não encontrado (para GET por id, PUT e DELETE), retornando código 404.</li>
  <li> Erro de validação, retornando código 422 e mensagens customizada para cada campo inválido. As
  regras de validação são:
      <ul>  
      <li> Nome: não pode ser vazio</li>
      <li>o Data de nascimento: não pode ser data futura (dica: use @PastOrPresent)</li>
      </ul>  
  </li>
</ul>  
<p><span style="color:red"><b>Atenção:</b></span> crie um novo projeto para este trabalho. Não é para simplesmente acrescentar a classe
Client no projeto feitos nas aulas.</p>

<p><span style="color:red"><b>Atenção:</b></span> lembre-se de que por padrão a JPA transforma nomes de atributos em camelCase para
snake_case, como foi o caso do campo imgUrl das aulas, que no banco de dados tinha o nome
img_url. Assim,<b> o campo birthDate acima será criado no banco de dados como birth_date, então
seu script SQL deverá seguir este padrão.</b></p>

<p><span style="color:red"><b>Atenção:</b></span> cuidado para não salvar no seu projeto arquivos e pastas que não devem ser salvas no Git,
tais como a pasta .metadata do Eclipse ou .idea do Intellij.
Como o trabalho será corrigido?</p>

<p><b>1) Importação do projeto</b></p>
<p>O professor deverá ser capaz de fazer um simples clone do projeto Github, e importar e executar o mesmo na
IDE sem necessidade de qualquer configuração especial diferente daquelas das aulas.</p>
<p><b>2) Testes manuais no Postman</b></p>
<p>O professor já terá preparado em seu computador as requisições Postman abaixo. Todas elas deverão
funcionar corretamente:</p>

### Busca de cliente por id
GET /clients/1
### Busca paginada de clientes
GET /clients?page=0&size=6&sort=name
### Inserção de novo cliente
POST /clients
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
### Atualização de cliente
PUT /clients/1
{
  "name": "Maria Silvaaa",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
### Deleção de cliente
DELETE /clients/1


## CHECKLIST:
1. Busca por id retorna cliente existente
2. Busca por id retorna 404 para cliente inexistente
3. Busca paginada retorna listagem paginada corretamente
4. Inserção de cliente insere cliente com dados válidos
5. Inserção de cliente retorna 422 e mensagens customizadas com dados inválidos
6. Atualização de cliente atualiza cliente com dados válidos
7. Atualização de cliente retorna 404 para cliente inexistente
8. Atualização de cliente retorna 422 e mensagens customizadas com dados inválidos
9. Deleção de cliente deleta cliente existente
10. Deleção de cliente retorna 404 para cliente inexistente
