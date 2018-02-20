package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Produtos;
import util.Util;

public class FrmCadastroProdutosDAO {
	
	public ObservableList<Produtos> capturarTodosProdutos() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produtos produto;
		ObservableList<Produtos> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from produtos;");

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = new Produtos();

				produto.setCodigo(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setPrecoVenda(rs.getFloat(3));
				produto.setConversao(rs.getFloat(4));
				produto.setDataCadastro(rs.getDate(5));

				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Produtos> capturarProdutosId(Integer codigo) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produtos produto;
		ObservableList<Produtos> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from produtos where id_produto = ?;");
			ps.setInt(1, codigo);

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = new Produtos();

				produto.setCodigo(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setPrecoVenda(rs.getFloat(3));
				produto.setConversao(rs.getFloat(4));
				produto.setDataCadastro(rs.getDate(5));

				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Produtos> capturarProdutosNome(String nome) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produtos produto;
		ObservableList<Produtos> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from produtos where pro_nome like ?;");
			ps.setString(1, "%" + nome + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = new Produtos();

				produto.setCodigo(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setPrecoVenda(rs.getFloat(3));
				produto.setConversao(rs.getFloat(4));
				produto.setDataCadastro(rs.getDate(5));

				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Produtos> capturarProdutosPreco(Float valor) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produtos produto;
		ObservableList<Produtos> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from produtos where pro_preco_venda = ?;");
			ps.setFloat(1, valor);

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = new Produtos();

				produto.setCodigo(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setPrecoVenda(rs.getFloat(3));
				produto.setConversao(rs.getFloat(4));
				produto.setDataCadastro(rs.getDate(5));

				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Produtos> capturarProdutosData(String data) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Produtos produto;
		ObservableList<Produtos> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from produtos where pro_data_cadastro = ?;");
			ps.setString(1, "%" + data + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				produto = new Produtos();

				produto.setCodigo(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setPrecoVenda(rs.getFloat(3));
				produto.setConversao(rs.getFloat(4));
				produto.setDataCadastro(rs.getDate(5));

				lista.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Integer capturarProximoCodigo() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("select max(id_produto) from produtos;");
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return (rs.getInt(1) + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean excluirProduto(Produtos produto) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("delete from produtos where id_produto = ?");	
			ps.setInt(1, produto.getCodigo());
					
			if(ps.executeUpdate() != 0){
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editarProduto(Produtos produto) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("UPDATE produtos SET "
					+ "pro_nome = ?,"
					+ "pro_preco_venda = ?,"
					+ "pro_conversao = ?,"
					+ "pro_data_cadastro = ? WHERE id_produto = ?;");

			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPrecoVenda());
			ps.setFloat(3, produto.getConversao());
			ps.setDate(4, produto.getDataCadastro());
			ps.setInt(5, produto.getCodigo());
			
			if(ps.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean consultarExistenciaProduto(Integer codigo) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("select * from produtos where id_produto = ?");
			
			ps.setInt(1, codigo);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean adicionarProduto(Produtos produto) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("insert into produtos (pro_nome, pro_preco_venda, pro_conversao, pro_data_cadastro) values (?,?,?,?);");
			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPrecoVenda());
			ps.setFloat(3, produto.getConversao());
			ps.setDate(4, produto.getDataCadastro());
			
			if(ps.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}