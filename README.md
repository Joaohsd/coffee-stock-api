[![Kotlin CI with Gradle](https://github.com/Joaohsd/coffee-stock-api/actions/workflows/gradle.yml/badge.svg)](https://github.com/Joaohsd/coffee-stock-api/actions/workflows/gradle.yml)

# Coffe Stock API ☕
Desenvolvimento de uma API voltada para a eficiente administração de estoques de café. Essa plataforma tecnológica tem como objetivo simplificar e aprimorar significativamente o controle de inventário e o relacionamento com os clientes dentro do setor cafeeiro.

## Gerenciador de Dependências
Como gerenciador de dependências, foi escolhido o Gradle, que é uma ferramenta para automatização de build e gerenciamento de dependências. Este se baseia nos conceitos de Apache Ant e Apache Maven. Não é necessária a instalação do Gradle, visto que estamos utilizando o Gradle Wrapper, o qual fica responsável por buscá-lo no repositório remoto para que o Gradle original seja utilizado em nossa aplicação. As dependências a seguir foram incluídas no arquivo `build.gradle.kts` para que pudessem ser utilizadas no projeto:

| Nome                         | Versão |
|------------------------------|--------|
| Spring Boot                  | 3.1.5  |
| Spring Boot Starter Test     | 3.1.5  |
| Springdoc Openapi Starter UI | 2.2.0 |
| Kotlin                       | 1.8.22 |
| Mockk                        | 1.13.8 |
| MySQL Connector Java         | 8.0.28 |
| JUnit                        | 5.9.3  |

## Pré-requisitos
Entre os requisitos do projeto, vale ressaltar:
*   MySQL Server (**v8.2.0**);
*   Docker (**v24.0.7**);
*   JDK - Java Development Kit (**v17.0.9**);

## Instalação
Clone do repositório:

```shell
git clone https://github.com/Joaohsd/coffee-stock-api.git
```

## Como utilizar
Para a execução do projeto, temos duas opções, as quais serão apresentadas a seguir:

### Sem Docker
Para execução sem a utilização do Docker, torna-se necessário a instalação da JDK e do MySQL Server. Para a aquisição de ambos, é possível por meio dos links a seguir:

*   [JDK 17](https://www.oracle.com/br/java/technologies/downloads/#java17);
*   [MySQL Server no Ubuntu](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04);
*   [MySQL Workbench no Ubuntu](https://dev.mysql.com/doc/workbench/en/wb-installing-linux.html)

Realizadas as instalações, siga os passos a seguir para executar a nossa aplicação:

1. Execute o script SQL presente em `schema/schema.sql` através do MySQL Workbench ou outra ferramenta semelhante. Este script é responsável pela inicialização do nosso Banco de Dados.
2. Rode os testes unitários da nossa aplicação por meio do gradle wrapper na raiz do projeto:

Para Linux:
```shell
./gradlew clean test
```

Para Windows:
```shell
./gradlew.bat clean test
```

3. Realize o build da nossa aplicação por meio do gradle wrapper na raiz do projeto:

Para Linux:
```shell
./gradlew clean build
```

Para Windows:
```shell
./gradlew.bat clean build
```

4. Dado que nenhum teste tenha falhado e a etapa de build não retornou erros, execute a nossa aplicação por meio do seguinte comando na raiz do projeto:

```shell
java -jar build/libs/coffeestock-0.0.1-SNAPSHOT.jar
```

### Com Docker
A utilização do docker facilita o processo de execução da aplicação, visto que por meio das imagens a seguir e o `docker-compose`, podemos gerenciar os containers da nossa aplicação. Cada imagem contem uma dependência conforme apresentado na seção de execução `Sem Docker`.

*   [MySQL](https://hub.docker.com/_/mysql) - Container para o Banco de Dados da aplicação;
*   [API](https://hub.docker.com/r/fernagata/coffee/tags) - Container para a aplicação;
*   [Adminer](https://hub.docker.com/_/adminer) - Container para um client semelhante ao mysql-workbench;

A execução do projeto é possível por meio do seguinte comando na raiz do projeto:

```shell
docker-compose up
```

Realizado tal comando, as três imagens serão baixadas para a sua máquina e seus respectivos **containers** serão executados.

### Build da aplicação e Geração da nova imagem

Caso você realize alguma modificação, também é possível criar uma nova imagem por meio do `Dockerfile` presente na raiz do projeto. Para isso, realize o seguinte comando a seguir:

```shell
docker build -t <nome-da-imagem> .
```

Feito isso, altere o arquivo `docker-compose.yml` presente na raiz do projeto para a imagem `nome-da-imagem` escolhida durante o processo de build do docker. Após este processo, é possível rodar novamente a aplicação por meio do seguinte comando na raiz do projeto:

```shell
docker-compose up
```

## Acessando o Swagger

Para acessar a interface do Swagger (depois de executar a aplicação), vá para localhost:9000/swagger e você verá uma tela como esta:

![Página do Swagger](images/swagger.png)

Esta página é uma **documentação** da nossa API, a qual apresenta todos os `recursos` e seus respectivos `endpoints`. Aliado a isso, também é possível realizar testes manuais, como: adicionar um cliente, atualizar um cliente, adicionar um estoque, remover um estoque, entre outros.

## Integrantes

* [Fernanda Nagata Ito](https://github.com/FerNagata)
* [João Henrique Silva Delfino](https://github.com/Joaohsd)
* [Paulo Otávio Luczensky](https://github.com/PauloLuczensky)
* [Pedro Pereira Guimarães](https://github.com/PedroPereiraGuimaraes)

