




Funcionalidade: Validar API de Usuários
  Como um QA
  Quero consultar a API
  Para validar os dados retornados

  Cenario: Buscar um usuário específico com sucesso
    Dado que o endpoint é "https://jsonplaceholder.typicode.com/users/1"
    Quando eu fizer uma requisição GET
    Então o status code deve ser 200
    E o corpo da resposta deve conter o nome "Leanne Graham"