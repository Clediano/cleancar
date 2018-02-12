package model;

public class Usuarios {
	private String usuario;
	private String senha;
	private Integer codigo;
	
	public Usuarios(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public Usuarios() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}