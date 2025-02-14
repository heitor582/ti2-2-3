package app;

import java.util.List;

import dao.PessoaDAO;
import model.Pessoa;

public class Application {
	
	public static void main(String[] args) throws Exception {
		
		PessoaDAO PessoaDAO = new PessoaDAO();
		
		System.out.println("\n\n==== Inserir pessoas === ");
		Pessoa Pessoa = new Pessoa("pablo");
		if(PessoaDAO.insert(Pessoa) == true) {
			System.out.println("Inserção com sucesso -> " + Pessoa.toString());
		}		
			
		System.out.println("\n\n==== Mostrar pessoas === ");
		List<Pessoa> Pessoas = PessoaDAO.get();
		for (Pessoa u: Pessoas) {
			System.out.println(u.toString());
		}
		Pessoa=Pessoas.get(0);
		
		System.out.println("\n\n==== Atualizar pessoa (código " + Pessoa.id + ") === ");
		Pessoa.name = "teste";
		PessoaDAO.update(Pessoa);
		
		System.out.println("\n\n==== Mostrar pessoa === ");
		Pessoa = PessoaDAO.get(Pessoa.id);
		System.out.println(Pessoa.toString());
		
		System.out.println("\n\n==== Excluir pessoa (código " + Pessoa.id + ") === ");
		PessoaDAO.delete(Pessoa.id);
		
		PessoaDAO.close();
		
	}
}