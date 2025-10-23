- Implementar método GET para listar todos os produtos cadastrados
  - Utilizar os RequestParam como campo opcional para filtrar pelo nome do produto


- Implementar o método GET para exibir o produto pelo ID


- Implementar o método PATCH para atualizar o preço do produto
  - Caso o preço seja menor ou igual a zero deve retornar o erro de BAD REQUEST
  - Caso o id do produto não exista no banco de dados deve retornar um NOT FOUND


- Implementar o método PUT para atualizar o preço e data de vencimento
  - Caso o preço seja menor ou igual a zero deve retornar o erro de BAD REQUEST
  - Caso a data de vencimento seja menor ou igual a 20 dias deve retornar um BAD REQUEST
  - Caso o id do produto não exista no banco de dados deve retornar um NOT FOUND


- Implementar o método DELETE para deletar um produto por ID
  - Caso o id do produto não exista no banco de dados deve retornar um NOT FOUND
