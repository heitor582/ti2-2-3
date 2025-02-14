package app;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.PessoaDAO;
import model.Pessoa;

public class Application {
	private static PessoaDAO pessoaDAO = new PessoaDAO();
	public static void main(String[] args) throws Exception {
		
		  port(2555);
	        
	      staticFiles.location("/public");
	        	      
	      
	      post("/pessoa", (request, response) -> {
	    	  	String body = request.body();
	    	    JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
	    	    String name = jsonObject.get("name").getAsString();
	            Pessoa pessoa = new Pessoa(name);
	            boolean success = pessoaDAO.insert(pessoa);
	            response.status(success ? 201 : 400);
	            return success ? "Pessoa criada com sucesso!" : "Falha na criação da pessoa!";
	        });

	        get("/pessoa", (request, response) -> {
	            List<Pessoa> pessoas = pessoaDAO.get();
	            return new Gson().toJson(pessoas);
	        });

	        get("/pessoa/:id", (request, response) -> {
	            long id = Long.parseLong(request.params(":id"));
	            Pessoa pessoa = pessoaDAO.get(id);
	            if (pessoa != null) {
	                return new Gson().toJson(pessoa);
	            } else {
	                response.status(404);
	                return "Pessoa não encontrada!";
	            }
	        });
	        
	        put("/pessoa/:id", (request, response) -> {
	            long id = Long.parseLong(request.params(":id"));
	            String body = request.body();
	    	    JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
	    	    String name = jsonObject.get("name").getAsString();
	            Pessoa pessoa = new Pessoa(id, name);
	            boolean success = pessoaDAO.update(pessoa);
	            response.status(success ? 200 : 400);
	            return success ? "Pessoa atualizada com sucesso!" : "Falha na atualização da pessoa!";
	        });

	        delete("/pessoa/:id", (request, response) -> {
	            long id = Long.parseLong(request.params(":id"));
	            boolean success = pessoaDAO.delete(id);
	            response.status(success ? 200 : 400);
	            return success ? "Pessoa excluída com sucesso!" : "Falha na exclusão da pessoa!";
	        });

	}
}