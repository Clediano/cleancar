package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Cliente;
import model.Lavagem;
import model.Produto;

public class FrmLancamentosLavagensDAO {

	public Lavagem capturarDadosLavagemCabecalho(Integer idLavagem) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Lavagem lavagem = new Lavagem();
		
		try {
			ps = conn.prepareStatement("select id_Lavagem, lav_cod_cliente, lav_data, lav_situacao, lav_obs from lavagens where id_lavagem = ?;");
			ps.setInt(1, idLavagem);

			rs = ps.executeQuery();

			if(rs.next()) {
				lavagem.setId(rs.getInt(1));
				lavagem.setCliente(capturarCliente(rs.getInt(2)));
				lavagem.setDataCadastro(rs.getDate(3));
				lavagem.setSituacao(rs.getInt(4));
				lavagem.setObservacao(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lavagem;
	}	
	
	public Cliente capturarCliente(Integer idCliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		
		try {
			ps = conn.prepareStatement("select * from cliente where id_cliente = ?;");
			ps.setInt(1, idCliente);

			rs = ps.executeQuery();

			if(rs.next()) {
				cliente = new Cliente();
				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));
				
				return cliente;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public ObservableList<Lavagem> capturarLavagensItens(Integer idLavagem) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Lavagem lavagem;
		ObservableList<Lavagem> lavagensItens = FXCollections.observableArrayList();
		
		try {
			ps = conn.prepareStatement("select * from lavagens_itens where lav_id = ?;");
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				lavagem = new Lavagem();
				
				lavagem.setProduto(capturarProduto(rs.getInt(3)));
				lavagem.setValor(rs.getFloat(4));
				
				lavagensItens.add(lavagem);
			}
			return lavagensItens;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	

	public Float somarValorItens(Integer idLavagem) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Float valorTotal = null;
		
		try {
			ps = conn.prepareStatement("select sum(lav_item_valor) from lavagens_itens where lav_id = ?;");
			ps.setInt(1, idLavagem);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				valorTotal = rs.getFloat(1);
				
				return valorTotal;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
	public Integer capturarIdProximaLavagem() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("select max(id_lavagem) from lavagens;");

			rs = ps.executeQuery();
			
			while(rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	
	public Boolean deletarLavagem(Integer idLavagem) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("delete from lavagens where id_lavagem = ?;");
			ps.setInt(1, idLavagem);

			if(ps.executeUpdate() == 0) {
				return false;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}	
	
	
	public Produto capturarProduto(Integer idProduto) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produto produtos;
		
		try {
			ps = conn.prepareStatement("select * from produtos where id_produto = ?;");
			ps.setInt(1, idProduto);

			rs = ps.executeQuery();

			if(rs.next()) {
				produtos = new Produto();
				produtos.setCodigo(rs.getInt(1));
				produtos.setNome(rs.getString(2));
				produtos.setPrecoVenda(rs.getFloat(3));
				produtos.setConversao(rs.getFloat(4));
				produtos.setDataCadastro(rs.getDate(5));
				
				return produtos;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}