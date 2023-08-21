## Teste de projeto java

### Requisitos
---

- Maven
- Java 11

### Execução
---

``` bash
mvn spring-boot:run
```

Aplicação irá rodar em ```http://localhost:9564```

### Execução  dos testes
---

Executar todos os testes:
``` bash
mvn test
```

Executar uma classe de teste:
``` bash
mvn test -Dtest={nome da classe}
```

Executar um teste específico:

Executar uma classe de teste:
``` bash
mvn test -Dtest={nome da classe}#{nome do teste}
```

- Exemplo:
``` bash
mvn test -Dtest=JavaTestApplicationTests#contextLoads
```

### Referência da API
---

Disponível em ```http://localhost:9564/```

### Acessar console do banco de dados
---

```http://localhost:9564/h2-console``` 

usuário = user

senha = password