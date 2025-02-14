package model;

public class Pessoa {
	public Long id;
	public String name;

	public Pessoa(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Pessoa(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", name=" + name + "]";
	}
	
	
}
