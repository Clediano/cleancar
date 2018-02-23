package model;

import java.sql.Date;
import java.time.LocalDate;

public class Cliente {

	private Integer codigo;
	private String nome;
	private String sobrenome;
	private String cnpj;
	private String email;
	private String telefone;
	private String pessoa;
	private Date dataCadastro;

	public Cliente(String nome, String sobrenome, String cnpj, String email, String telefone, Date dtaCadastro) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.dataCadastro = dtaCadastro;
	}

	public Cliente() {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getPessoa() {
		return pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

}