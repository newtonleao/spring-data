package br.com.alura.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository repository;
    
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository repository) {
		this.repository = repository;
	}

	public void inicial(Scanner scanner) {

		boolean system = true;
		while (system) {
			System.out.println("Qual ação de carga");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int action = scanner.nextInt();
			switch (action) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição da Unidade de trabalho");
		String descricao = scanner.next();

		System.out.println("Endereço da Unidade de trabalho");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		repository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		
		System.out.println("Descrição da Unidade de trabalho");
		String descricao = scanner.next();

		System.out.println("Endereço da Unidade de trabalho");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalho.setEndereco(endereco);
		
		repository.save(unidadeTrabalho);
		System.out.println("Atualizar");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadesTrabalho = repository.findAll();
		unidadesTrabalho.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		repository.deleteById(id);
		System.out.println("Registro deletado");
	}
}
