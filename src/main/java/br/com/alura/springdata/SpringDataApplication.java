package br.com.alura.springdata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springdata.orm.Cargo;
import br.com.alura.springdata.orm.Funcionario;
import br.com.alura.springdata.orm.UnidadeTrabalho;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.repository.UnidadeTrabalhoRepository;
import br.com.alura.springdata.service.CrudCargoService;
import br.com.alura.springdata.service.CrudFuncionarioService;
import br.com.alura.springdata.service.CrudUnidadeTrabalhoService;
import br.com.alura.springdata.service.RelatorioFuncionarioDinamicoService;
import br.com.alura.springdata.service.RelatoriosServices;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

//	private final CargoRepository repository;
	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosServices relatoriosServices;
	private final RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	CargoRepository cargoRepository;
	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	UnidadeTrabalhoRepository unidadeTrabalhoRepository;

//	public SpringDataApplication(CargoRepository repository) {
	public SpringDataApplication(CrudCargoService serviceCargo, CrudFuncionarioService serviceFuncionario,
			CrudUnidadeTrabalhoService serviceUnidadeTrabalho, RelatoriosServices relatoriosServices, RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService) {
//		this.repository = repository;
		this.cargoService = serviceCargo;
		this.funcionarioService = serviceFuncionario;
		this.unidadeTrabalhoService = serviceUnidadeTrabalho;
		this.relatoriosServices = relatoriosServices;
		this.relatorioFuncionarioDinamicoService = relatorioFuncionarioDinamicoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	private void cargaInicial() {
		Cargo cargo = new Cargo();
		cargo = cargoRepository.findById(7).get();
		List<UnidadeTrabalho> list = new ArrayList<>();
		UnidadeTrabalho unidade = unidadeTrabalhoRepository.findById(2).get();
		list.add(unidade);
		for (int i = 0; i < 10; i++) {

			Funcionario func = new Funcionario();
			func.setCargo(cargo);
			func.setUnidadeTrabalho(list);
			func.setNome("Nome" + i);
			func.setCpf((i * 10000 + "").trim());
			func.setSalario(50.0 * i);
			func.setDataContratacao(LocalDate.now().minusDays(i));
			funcionarioRepository.save(func);
		}
	}

	@Override
	public void run(String... args) throws Exception {
//		cargaInicial();
		boolean system = true;
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionario");
			System.out.println("3 - UnidadeTrabalho");
			System.out.println("4 - Relatórios");
			System.out.println("5 - Relatórios Specifications");

			int action = scanner.nextInt();

			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				funcionarioService.inicial(scanner);
				break;
			case 3:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 4:
				relatoriosServices.inicial(scanner);
				break;
			case 5:
				relatorioFuncionarioDinamicoService.inicial(scanner);
				break;

			default:
				system = false;
				break;
			}

		}

//		Cargo cargo = new Cargo("Desenvolvedor de software backend");
//		repository.save(cargo);
//		
//		cargo = new Cargo("Desenvolvedor de software frontend");
//		repository.save(cargo);

	}

}
