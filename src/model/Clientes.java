package model;

import java.sql.Date;

public class Clientes {
	
	private Integer codigo;
	private String nome;
	private String sobrenome;
	private String cnpj;
	private String email;
	private Integer telefone;
	private Date dtaCadastro;

	public Clientes(String nome, String sobrenome, String cnpj, String email, Integer telefone, Date dtaCadastro) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.dtaCadastro = dtaCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtaCadastro() {
		return dtaCadastro;
	}

	public void setDtaCadastro(Date dtaCadastro) {
		this.dtaCadastro = dtaCadastro;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}