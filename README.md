# JDBC (Java database connectivity) 
- api padrão para acesso a dados
- Java application
  - JDBC Driver manager
    - Driver sql, driver oracle, drive sybase

## Algebra relacional e SQL
- formalismo define modelos dentro modelo relacional
  - restrição
  - projeção
  - produto cartesiano (traz os dois)
```sql
SELECT * FROM product, category
```
  - junção (produto cartesiano + restrição de chaves correspondentes)
```sql
SELECT * FROM product, category WHERE product.category_id = category.id
-- ou
SELECT * FROM product WHERE inner join category cat on product.category_id = cat.id
```
- Baixar connector java
  - é extraido para /usr/share/java
# Buscar dados
## Statement
- montar comando sql a ser executado
## ResultSet
- objeto contendo resultado da consulta em forma de tabela
- first() 
  - move pra posição 1 se houver
- beforeFirst()
  - move para posição 0
- next
  - move para proximo
- absolute 
  - move para posiçã informada, lembrando que os dados reais começam em 1

```java
Connection conn = null;
Statement st = null;
ResultSet rs = null;

try {
  conn = DB.getConnection();
  st = conn.createStatement();
  rs = st.executeQuery("select * from department");

  while (rs.next()) {
    System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
  }
}
```
# Inserir dados
## PreparedStatement
- montar consulta sql 

## executeUpdate
## Statement.RETURN_GENERATED_KEYS
- recuperar id do obj inserido
## getGeneratedKeys
```java
Connection conn = null;
PreparedStatement st = null;

try {
  conn = DB.getConnection();
  st = conn.prepareStatement(
    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
    Statement.RETURN_GENERATED_KEYS
  );

  st.setString(1, "Carl Purple");
  st.setString(2, "carl@gmail.com");
  st.setDate(3, Date.valueOf(LocalDate.parse("1985-04-22")));
  st.setDouble(4, 3000);
  st.setInt(5, 4);

  st = conn.prepareStatement(
      "insert into department (Name) values ('D1'), ('D2')",
      Statement.RETURN_GENERATED_KEYS
  );

  int rowsAffected = st.executeUpdate(); 
  if (rowsAffected > 0)  {
    ResultSet rs = st.getGeneratedKeys();
    while (rs.next()) {
        int id = rs.getInt(1);
        System.out.println("Done! ID = " + id);
    }
  }
}
```

# Transação
- op que tem que manter consistencia do db
- tem que ser
  1. Atomica
  2. consistente
  3. isolada
  4. Durável
- ou acontece tudo ou não acontece nada

- setAutoCommit(false)
  - cada operação isolada deve ser confirmada automáticamente
  - colocar como false, cada transação n ta confirmada, somente quando confirmar que fecha a transação
- commit()
  - commitar transação
- rollback()
  - desfazer transação

# DAO (Data access object)
- pra cada entidade tem um obj responsável por fazer acesso a dados relacionado a essa entidade
  - ClienteDao
  - ProdutoDao
- Cada DAO será definido por uma interface
- A injeção de depencia pode ser feita por meio do padrão Factory
  - responsavel por instanciar implementações do DAO