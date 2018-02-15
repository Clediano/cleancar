package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Clientes;
import model.Usuarios;

public class FrmCadastroClienteDAO {

	/**
	 * Retornar verdadeiro se o cliente existe no banco de dados. Caso não existir
	 * este cliente no banco de dados, é retornado falso.
	 * 
	 * @param String
	 *            usuario
	 * @return boolean
	 */
	public boolean verificarCliente(String cnpj) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("select cli_cnpj from clientes where cli_cnpj = ?;");
			ps.setString(1, cnpj);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retorna verdadeiro caso o usuário foi realmente cadastrado. Caso ocorrer
	 * algum incidente, retorna falso.
	 * 
	 * @param usuario
	 * @param senha
	 * @return boolean
	 */
	public boolean cadastrarCliente(Clientes cliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(
					"insert into clientes (cli_nome, cli_sobrenome, cli_cnpj, cli_email, cli_telefone, cli_data_cadastro) values (?,?,?,?,?,?);");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getSobrenome());
			ps.setString(3, cliente.getCnpj());
			ps.setString(4, cliente.getEmail());
			ps.setInt(5, cliente.getTelefone());
			ps.setDate(6, (Date)cliente.getDataCadastro());

			if (ps.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Retorna um ObservableList contendo todos os clientes do banco de dados.
	 * 
	 * @return ObservableList
	 */
	public ObservableList<Clientes> capturarTodosClientes() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Clientes cliente;
		ObservableList<Clientes> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes;");

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Clientes();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getInt(6));
				cliente.setDataCadastro(rs.getDate(7));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Retorna um ObservableList contendo todos os clientes do banco de dados.
	 * 
	 * @param String
	 *            contendo o possivel usuário(login);
	 * @return ObservableList
	 */
	public ObservableList<Clientes> capturarClienteNome(String nomeCliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Clientes cliente;
		ObservableList<Clientes> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_nome like ?;");
			ps.setString(1, '%' + nomeCliente + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Clientes();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setDataCadastro(rs.getDate(6));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Retorna um ObservableList contendo todos os usuários do banco de dados.
	 * 
	 * @param Integer
	 *            contendo o possivel código do cliente;
	 * @return ObservableList
	 */
	public ObservableList<Usuarios> capturarUsuariosCodigo(Integer codigo) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Usuarios usuario;
		ObservableList<Usuarios> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from usuarios where idusuarios = ?;");
			ps.setInt(1, codigo);

			rs = ps.executeQuery();

			while (rs.next()) {
				usuario = new Usuarios();

				usuario.setCodigo(rs.getInt(1));
				usuario.setUsuario(rs.getString(2));
				usuario.setSenha(rs.getString(3));

				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Retorna verdadeiro se removeu e falso se não encontrou nada
	 * 
	 * @param ID
	 *            do cliente a ser deletado
	 * @return Boolean
	 */
	public boolean removerCliente(Integer idCliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("delete from clientes where id_cliente = ?;");
			ps.setInt(1, idCliente);

			if (ps.executeUpdate() != 0) {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Retorna verdadeiro se conseguiu editar ou falso se não conseguiu.
	 * 
	 * @param (Usuarios)
	 *            - Usuário a ser editado
	 * @return Boolean
	 */
	public boolean editarUsuario(Usuarios usuarios) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE usuarios SET usu_senha = ? WHERE  idusuarios = ?;");
			ps.setString(1, usuarios.getSenha());
			ps.setInt(2, usuarios.getCodigo());

			if (ps.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
