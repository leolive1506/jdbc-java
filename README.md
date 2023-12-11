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