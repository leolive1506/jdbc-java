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