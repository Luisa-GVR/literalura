package com.literaluraSpring.literalura;

import com.literaluraSpring.literalura.principal.Principal;
import com.literaluraSpring.literalura.repository.AutorRepository;
import com.literaluraSpring.literalura.repository.LibreriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.PrinterIOException;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibreriaRepository repository;

	@Autowired
	private AutorRepository repositoryAutor;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args)throws Exception{
		Principal principal = new Principal(repository, repositoryAutor);
		principal.menu();
	}
}



