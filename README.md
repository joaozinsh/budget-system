# Sistema de Orçamentos (Budget System)

# Sobre o projeto

Sistema de orçamentos genérico para visualizar, criar, editar e apagar orçamentos, utilizando dois perfis de acesso (Vendedor e Gerente).

## Imagens
<table align=center>
  <tr>
    <td>
      <img src="https://i.imgur.com/Et7xVoX.png" title="source: imgur.com" />
    </td>
    <td>
      <img src="https://i.imgur.com/0Wl7C5u.png" title="source: imgur.com" />
    </td>
  </tr>
  <tr>
    <td>
      <img src="https://i.imgur.com/EmrXZ8s.png" title="source: imgur.com" />
    </td>
    <td>
      <img src="https://i.imgur.com/R2yIrxv.png" title="source: imgur.com" />
    </td>
  </tr>
</table>

>Projeto desenvolvido como um teste para a empresa VarejOnline.

# Tecnologias utilizadas

## Back-End

* Java
* Spring Boot
    * Spring Web
    * Spring Boot DevTools
    * Spring Data JPA
    * H2 Database
    * Validation
* Maven

## Front-End

* HTML / CSS / TypeScript
* Angular
* Bootstrap

# Como executar o projeto

## Back-End

### Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Ter instalado o Java JDK 11

- Para verificar, rode o seguinte comando em um terminal: `java --version`

- Caso não tenha instalado, siga estas etapas:

  Linux:

  ```bash
  sudo apt install openjdk-11-jdk
  ```

  Windows:

  ```
  https://www.oracle.com/java/technologies/downloads/#java11-windows
  ```

### Usando

Para usar o projeto, siga estas etapas:

Faça um clone do repositório

```bash
git clone https://github.com/joaozinsh/budget-system.git
```

Abra um terminal na pasta back-end do projeto e digite:

​	Linux ou Windows (Git Bash):

```bash
./mvnw spring-boot:run
```

​	Windows (CMD):

```bash
./mvnw.cmd spring-boot:run
```

Para acessar o banco de dados H2, acesse `http://localhost:8080/h2-console` 

## Front-End

### Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Ter instalado o `Angular CLI`

- Para verificar, rode o seguinte comando em um terminal: `ng --version`

- Caso não tenha instalado, siga estas etapas:

  Primeiro é necessário ter o `npm` package manage instalado, faça o download da versão `LTS` do `Node.js`

  ```
  https://nodejs.org/en/download/
  ```

  Feito isso, instale o `Angular CLI`

  ```bash
  npm install -g @angular/cli
  ```
  
### Usando

Para usar o projeto, siga estas etapas:

Faça um clone do repositório

```bash
git clone https://github.com/joaozinsh/budget-system.git
```

Abra um terminal na pasta front-end do projeto e instale as dependências:

```bash
npm install
```

Feito isso, rode o projeto com:
```bash
ng serve
```

Por último, acesse `http://localhost:4200`

Usuários de acesso:
  * Vendedor
    * Senha: vendedor
  * Gerente
    * Senha: gerente
