LiterAlura

*📖 Descrição do Projeto*

O LiterAlura é uma aplicação de linha de comando desenvolvida com Spring Boot que se conecta à API Gutendex para buscar e gerenciar informações sobre livros e seus autores. A aplicação permite que o usuário interaja com um menu para buscar livros, listar registros e consultar dados de autores e livros armazenados em um banco de dados H2.

*📁 Estrutura do Projeto*

      src/main/java
      ├── com/literAlura
      │   ├── model
      │   │   ├── Autor.java
      │   │   └── Livro.java
      │   ├── repository
      │   │   ├── AutorRepository.java
      │   │   └── LivroRepository.java
      │   └── service
      │       ├── ConsumoApi.java
      │       └── ConverteDados.java
      └── org/example
          ├── Dados.java
          ├── DadosAutor.java
          ├── DadosLivro.java
          ├── IConverteDados.java
          └── Main.java
    
*✨ Funcionalidades*

A aplicação oferece as seguintes funcionalidades através de um menu interativo:

- Buscar livro por título: Busca livros na API Gutendex e os salva no banco de dados, caso não existam.

- Listar livros registrados: Exibe todos os livros que foram salvos no banco de dados.

- Listar autores registrados: Mostra todos os autores que foram salvos, juntamente com os livros associados.

- Listar autores vivos em um determinado ano: Filtra e exibe os autores que estavam vivos em um ano específico.

- Listar livros em um determinado idioma: Filtra e exibe os livros com base no idioma (por exemplo: pt, en, es).

*🛠️ Tecnologias Utilizadas*

- Java 21: Linguagem de programação.

- Spring Boot: Framework para simplificar o desenvolvimento de aplicações Java.

- Spring Data JPA: Para persistência de dados e interação com o banco de dados.

- Hibernate: Implementação da JPA.

- Maven: Gerenciador de dependências e build do projeto.

- H2 Database: Banco de dados em memória, ideal para desenvolvimento.

- Jackson Databind: Biblioteca para processar e mapear dados JSON.

- API Gutendex: Fonte de dados para a busca de livros.

 *📥 Como Executar*
 
1. Clone o repositório:
   
         git clone https://github.com/AfroditeSanliveira/LiterAluraChallenge.git
          cd LiterAluraChallenge
2. Compile e execute com Maven:
   
       mvn clean install
        mvn exec:java -Dexec.mainClass="com.afrodite.literalura.MainApp"
