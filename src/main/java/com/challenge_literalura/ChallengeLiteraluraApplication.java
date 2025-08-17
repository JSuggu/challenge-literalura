package com.challenge_literalura;

import com.challenge_literalura.dto.BookDtoResponse;
import com.challenge_literalura.service.GetDataFromApiService;
import com.challenge_literalura.service.GetDataFromApiServiceImpl;
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
	public CommandLineRunner app(GetDataFromApiService getDataFromApiService) throws Exception {
		Scanner sc = new Scanner(System.in);
		boolean active = true;

		while (active){
			System.out.println("""
				Elija la opcion a traves de su numero:
				1 - Busca libro por nombre
				0 - Salir
				""");
			int optionSelected = sc.nextInt();
			sc.nextLine();

			if(optionSelected == 1){
				System.out.println("Escriba el nombre del libro");
				String bookName = sc.nextLine();

				try {
					List<BookDtoResponse> response = getDataFromApiService.getBooksByName(bookName);

					for (BookDtoResponse book : response) {
						System.out.println("Título: " + book.getTitle());
						System.out.println("Autores: " + book.getAuthorsAsString());
						System.out.println("Idioma: " + book.getFirstLanguage());
						System.out.println("Descargas: " + book.getDownloadCount());
						System.out.println("----------------------");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(optionSelected == 0){
				sc.close();
				active = false;
			}
		}

        return null;
    }
}
