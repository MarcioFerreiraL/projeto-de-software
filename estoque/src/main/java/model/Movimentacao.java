package model;

import java.util.Date;

public class Movimentacao {
	private int id;
	private TIPO_MOVIMENTACAO tipo;
	private int quantidade;
	private Date data;
	private Produto produto;
	
	public Movimentacao(int id, TIPO_MOVIMENTACAO tipo, int quantidade, Date data, Produto produto) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.data = data;
		this.produto = produto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TIPO_MOVIMENTACAO getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_MOVIMENTACAO tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", tipo=" + tipo + ", quantidade=" + quantidade + ", data=" + data
				+ ", produto=" + produto + "]";
	}
	
}
