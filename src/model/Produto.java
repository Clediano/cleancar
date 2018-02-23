package model;

import java.sql.Date;

public class Produto {

	private Integer codigo;
	private String nome;
	private Float precoVenda;
	private Float conversao;
	private Date dataCadastro;

	public Produto(Integer codigo, String proNome, Float precoVenda, Float conversao, Date dataCadastro) {
		super();
		this.codigo = codigo;
		this.nome = proNome;
		this.precoVenda = precoVenda;
		this.conversao = conversao;
		this.dataCadastro = dataCadastro;
	}

	public Produto() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Float getConversao() {
		return conversao;
	}

	public void setConversao(Float conversao) {
		this.conversao = conversao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


}