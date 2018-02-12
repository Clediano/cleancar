package model;

public class Produtos {
	private String proNome;
	private Double precoVenda;

	public Produtos(String proNome, Double precoVenda) {
		this.setProNome(proNome);
		this.setPrecoVenda(precoVenda);
	}

	public Produtos() {
	}

	public String getProNome() {
		return proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
}