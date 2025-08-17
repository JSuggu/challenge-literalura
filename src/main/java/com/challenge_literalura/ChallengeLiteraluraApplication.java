package com.challenge_literalura;

import com.challenge_literalura.dto.AuthorDto;
import com.challenge_literalura.dto.BookDto;
import com.challenge_literalura.service.AuthorService;
import com.challenge_literalura.service.BookService;
import com.challenge_literalura.service.GetDataFromApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Bean
	public CommandLineRunner app(GetDataFromApiService getDataFromApiService, AuthorService authorService, BookService bookService) throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean active = true;

		while (active){
			System.out.println("""
				Elija la opcion a traves de su numero:
				1 - Busca libro por nombre
				2 - Listar libros registrados
				3 - Listar autores registrados
				4 - Listar autores vivos en un año determinado
				5 - Listar libros por idioma
				0 - Salir
			""");
			int optionSelected = -1;
			boolean inputOptionValid = false;

			while (!inputOptionValid) {
				try {
					String input = sc.nextLine();
					optionSelected = Integer.parseInt(input);

					if (optionSelected >= 0 && optionSelected <= 5) {
						inputOptionValid = true;
					} else {
						System.out.println("Opción no válida. Ingrese un número entre 0 y 5");
					}
				} catch (NumberFormatException e) {
					System.out.println("Debe ingresar un número. Intente nuevamente");
				}
			}

			try {
				if(optionSelected == 1){
					System.out.println("Escriba el nombre del libro");
					String bookName = sc.nextLine();

					List<BookDto> response = getDataFromApiService.getBooksByName(bookName);

					response.stream().forEach(book -> {
						System.out.println("Título: " + book.getTitle());
						System.out.println("Autores: " + book.getAuthorsAsString());
						System.out.println("Idioma: " + book.getFirstLanguage());
						System.out.println("Descargas: " + book.getDownloadCount());
						System.out.println("----------------------");
					});
				} else if (optionSelected == 2) {
					System.out.println("Lista de libros registrados");

					List<BookDto> response = bookService.getAll();

					response.forEach(book -> {
						System.out.println("Título: " + book.getTitle());
						System.out.println("Autores: " + book.getAuthorsAsString());
						System.out.println("Idioma: " + book.getFirstLanguage());
						System.out.println("Descargas: " + book.getDownloadCount());
						System.out.println("----------------------");
					});
				} else if (optionSelected == 3) {
					System.out.println("Lista de autores registrados");

					List<AuthorDto> response = authorService.getAll();

					response.forEach(author -> {
						System.out.println("Nombre: " + author.getName());
						System.out.println("Año de Nacimiento: " + author.getBirthYear());
						System.out.println("Año de Fallecimiento: " + author.getDeathYear());
						System.out.println("----------------------");
					});
				} else if (optionSelected == 4) {
					System.out.println("Escriba el año");
					Integer year = null;
					boolean inputValid = false;

					while (!inputValid) {
						try {
							String input = sc.nextLine();
							year = Integer.parseInt(input);
							inputValid = true;
						} catch (NumberFormatException e) {
							System.out.println("Debe ingresar un número válido. Intente nuevamente:");
						}
					}

					List<AuthorDto> response = authorService.getAliveAuthorsByYear(year);

					System.out.println("Lista de autores vivos por año");
					response.forEach(author -> {
						System.out.println("Nombre: " + author.getName());
						System.out.println("Año de Nacimiento: " + author.getBirthYear());
						System.out.println("Año de Fallecimiento: " + author.getDeathYear());
						System.out.println("----------------------");
					});
				} else if (optionSelected == 5) {
					System.out.println("Escriba el idioma");
					String language = sc.nextLine();

					List<BookDto> response = bookService.getBooksByLanguage(language);

					System.out.println("Lista de libros por idioma");
					response.forEach(book -> {
						System.out.println("Título: " + book.getTitle());
						System.out.println("Autores: " + book.getAuthorsAsString());
						System.out.println("Idioma: " + book.getFirstLanguage());
						System.out.println("Descargas: " + book.getDownloadCount());
						System.out.println("----------------------");
					});
				} else if(optionSelected == 0){
					sc.close();
					active = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

        return null;
    }
}
