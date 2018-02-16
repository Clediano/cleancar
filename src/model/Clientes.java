package model;

import java.sql.Date;

public class Clientes {

	private Integer codigo;
	private String nome;
	private String sobrenome;
	private Integer cnpj;
	private String email;
	private Integer telefone;
	private Date dataCadastro;

	public Clientes(String nome, String sobrenome, Integer cnpj, String email, Integer telefone, Date dtaCadastro) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.dataCadastro = dtaCadastro;
	}

	public Clientes() {
		super();
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}