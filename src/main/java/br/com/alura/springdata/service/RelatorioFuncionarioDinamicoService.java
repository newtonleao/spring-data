package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamicoService {
	
	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamicoService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		System.out.println("Didite o nome");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Didite o CPF");
		String cpf = scanner.next();

		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Didite o salario");
		Double salario = scanner.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		System.out.println("Didite o Data Contratação");
		String dataString = scanner.next();
		
		LocalDate dataContratacao;
		if(dataString.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(dataString, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification.where(
				    SpecificationFuncionario.nome(nome))
				.or(SpecificationFuncionario.cpf(cpf))
				.or(SpecificationFuncionario.salario(salario))
				.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);
		funcionarios.forEach(System.out::println);
	}

}
