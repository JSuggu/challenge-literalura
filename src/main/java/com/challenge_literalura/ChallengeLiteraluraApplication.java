package com.challenge_literalura;

import com.challenge_literalura.service.GetDataFromApiServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		GetDataFromApiServiceImpl getData = new GetDataFromApiServiceImpl();
		Scanner sc = new Scanner(System.in);
		boolean active = true;

		while (active){
			System.out.println("""
				Elija la opcion a traves de su numero:
				1 - Busca libro por nombre
				0 - Salir
				""");
			Integer optionSelected = sc.nextInt();
			sc.nextLine();

			if(optionSelected == 1){
				System.out.println("Escriba el nombre del libro");
				String bookName = sc.nextLine();
				String response = getData.getBooksByName(bookName);
				System.out.println(response);
			} else if(optionSelected == 0){
				sc.close();
				active = false;
			}
		}

	}
}
