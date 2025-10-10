package model;

public class Usuario extends Entidade{
	private int id;
	private String nome;
	private TIPO_USUARIO tipo;
	
	public Usuario(int id, String nome, TIPO_USUARIO tipo) {
		super(id);
		this.nome = nome;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TIPO_USUARIO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_USUARIO tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", tipo=" + tipo + "]";
	}
	
	
	
}
