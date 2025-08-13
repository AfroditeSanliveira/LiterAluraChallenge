package com.literAlura;

import com.literAlura.model.Autor;
import com.literAlura.model.Livro;
import com.literAlura.repository.AutorRepository;
import com.literAlura.repository.LivroRepository;
import com.literAlura.service.ConsumoApi;
import com.literAlura.service.ConverteDados;
import org.example.DadosAutor;
import org.example.DadosLivro;
import org.example.RespostaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private static final String BASE_URL = "https://gutendex.com/books/";

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        exibirMenuPrincipal();
    }

    private void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    \n--- MENU PRINCIPAL ---
                    1. Buscar livro por título
                    2. Listar livros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos em um ano
                    5. Listar livros por idioma
                    0. Sair
                    """);

            try {
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> buscarLivroPorTitulo();
                    case 2 -> listarLivrosRegistrados();
                    case 3 -> listarAutoresRegistrados();
                    case 4 -> listarAutoresVivos();
                    case 5 -> listarLivrosPorIdioma();
                    case 0 -> System.out.println("\nSaindo do sistema... Até logo!");
                    default -> System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nErro: Digite um número válido!");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void buscarLivroPorTitulo() {
        System.out.print("\nDigite o título do livro: ");
        String titulo = scanner.nextLine();

        String url = BASE_URL + "?search=" + titulo.replace(" ", "%20");

        try {
            String json = consumoApi.obterDados(url);
            RespostaApi resposta = conversor.obterDados(json, RespostaApi.class);

            if (resposta.results() == null || resposta.results().isEmpty()) {
                System.out.println("\nNenhum livro encontrado com o título: " + titulo);
                return;
            }

            DadosLivro dadosLivro = resposta.results().get(0);
            System.out.println("\n--- LIVRO ENCONTRADO ---");
            System.out.println("Título: " + dadosLivro.titulo());
            System.out.println("Autor(es): " + dadosLivro.autores().stream()
                    .map(DadosAutor::nome)
                    .collect(Collectors.joining(", ")));
            System.out.println("Idiomas: " + String.join(", ", dadosLivro.idiomas()));
            System.out.println("Downloads: " + dadosLivro.downloads());

        } catch (RuntimeException e) {
            System.out.println("\nErro na busca: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro registrado no sistema.");
        } else {
            System.out.println("\n--- LIVROS REGISTRADOS ---");
            livros.forEach(livro -> {
                System.out.println("\nTítulo: " + livro.getTitulo());
                System.out.println("Autor: " +
                        (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido"));
                System.out.println("Idiomas: " + String.join(", ", livro.getIdiomas()));
            });
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("\nNenhum autor registrado no sistema.");
        } else {
            System.out.println("\n--- AUTORES REGISTRADOS ---");
            autores.forEach(autor -> {
                System.out.println("\nNome: " + autor.getNome());
                System.out.println("Ano de nascimento: " +
                        (autor.getAnoNascimento() != null ? autor.getAnoNascimento() : "N/A"));
                System.out.println("Ano de falecimento: " +
                        (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A"));
            });
        }
    }

    private void listarAutoresVivos() {
        try {
            System.out.print("\nDigite o ano para pesquisa: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            List<Autor> autores = autorRepository.findAutoresVivosNoAno(ano);

            if (autores.isEmpty()) {
                System.out.println("\nNenhum autor vivo encontrado no ano " + ano);
            } else {
                System.out.println("\n--- AUTORES VIVOS EM " + ano + " ---");
                autores.forEach(autor -> {
                    System.out.println("\nNome: " + autor.getNome());
                    System.out.println("Nascimento: " + autor.getAnoNascimento());
                    System.out.println("Falecimento: " +
                            (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "Presente"));
                });
            }
        } catch (InputMismatchException e) {
            System.out.println("\nErro: Digite um ano válido!");
            scanner.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("\nDigite o idioma (ex: en, es, fr, pt): ");
        String idioma = scanner.nextLine().toLowerCase();

        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro encontrado no idioma: " + idioma);
        } else {
            System.out.println("\n--- LIVROS EM " + idioma.toUpperCase() + " ---");
            livros.forEach(livro -> {
                System.out.println("\nTítulo: " + livro.getTitulo());
                System.out.println("Autor: " +
                        (livro.getAutor() != null ? livro.getAutor().getNome() : "Desconhecido"));
            });
        }
    }
}