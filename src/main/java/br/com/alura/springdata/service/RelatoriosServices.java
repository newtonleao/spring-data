package br.com.alura.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.FuncionarioProjecao;
import br.com.alura.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosServices {
	
	private final FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosServices(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	

	public void inicial(Scanner scanner) {
		boolean system = true;
		while (system) {
			System.out.println("Qual ação de carga");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionário por nome");
			System.out.println("2 - Busca Funcionário por nome, salário e data");
			System.out.println("3 - Busca Funcionário por data maior");
			System.out.println("4 - Busca Funcionário salário");

			int action = scanner.nextInt();
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataMaior(scanner);
				break;
			case 4:
				pesquisaFuncionarioSalario();
				break;
			default:
				system = false;
				break;
			}
		}

	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Infome nome do funcionario");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		
		funcionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Infome nome do funcionario");
		String nome = scanner.next();
		
		System.out.println("Infome data contratação do funcionario");
		String data = scanner.next();
		LocalDate localdate = LocalDate.parse(data, formatter);
		
		System.out.println("Infome salário do funcionario");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localdate);
		list.forEach(System.out::println);
	}

	private void buscaFuncionarioDataMaior(Scanner scanner) {
		
		System.out.println("Infome data contratação do funcionario");
		String data = scanner.next();
		LocalDate localdate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localdate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
}
