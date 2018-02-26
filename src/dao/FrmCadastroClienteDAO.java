package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Cliente;
import model.Usuario;

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
	public boolean cadastrarCliente(Cliente cliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement(
					"insert into clientes (cli_nome, cli_sobrenome, cli_cnpj, cli_email, cli_telefone, cli_data_cadastro, cli_pessoa) values (?,?,?,?,?,?,?);");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getSobrenome());
			ps.setString(3, cliente.getCnpj());
			ps.setString(4, cliente.getEmail());
			ps.setString(5, cliente.getTelefone());
			ps.setDate(6, cliente.getDataCadastro());
			ps.setString(7, cliente.getPessoa());

			if (ps.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Retorna o código do proximo usuário a ser adicioando
	 * 
	 * @return integer
	 */
	public Integer proximoCodigoCliente() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(
					"select max(id_cliente) from clientes;");

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna um ObservableList contendo todos os clientes do banco de dados.
	 * 
	 * @return ObservableList
	 */
	public ObservableList<Cliente> capturarTodosClientes() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes;");

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

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
	 *            contendo o possivel cliente(nome);
	 * @return ObservableList
	 */
	public ObservableList<Cliente> capturarClienteNome(String nomeCliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_nome like ?;");
			ps.setString(1, '%' + nomeCliente + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Cliente> capturarClienteSobrenome(String sobrenomeCliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_sobrenome like ?;");
			ps.setString(1, '%' + sobrenomeCliente + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Cliente> capturarClienteEmail(String email) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_email like ?;");
			ps.setString(1, '%' + email + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Cliente> capturarClienteTelefone(String telefone) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_email like ?;");
			ps.setString(1, '%' + telefone + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Cliente> capturarClienteData(String data) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_data_cadastro = ?;");
			ps.setString(1, data);

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Cliente> capturarClienteCnpjCpf(String cnpjCpf) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;
		ObservableList<Cliente> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from clientes where cli_cnpj like ?;");
			ps.setString(1, '%' + cnpjCpf + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				cliente = new Cliente();

				cliente.setCodigo(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setSobrenome(rs.getString(3));
				cliente.setCnpj(rs.getString(4));
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone(rs.getString(6));
				cliente.setPessoa(rs.getString(7));
				cliente.setDataCadastro(rs.getDate(8));

				lista.add(cliente);
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
	public boolean editarCliente(Cliente cliente) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE clientes SET "
					+ "cli_nome = ?, "
					+ "cli_sobrenome = ?, "
					+ "cli_cnpj = ?, "
					+ "cli_email = ?, "
					+ "cli_telefone = ?, "
					+ "cli_data_cadastro = ?, "
					+ "cli_pessoa = ? WHERE  id_cliente = ?;");
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getSobrenome());
			ps.setString(3, cliente.getCnpj());
			ps.setString(4, cliente.getEmail());
			ps.setString(5, cliente.getTelefone());
			ps.setDate(6, cliente.getDataCadastro());
			ps.setString(7, cliente.getPessoa());
			ps.setInt(8, cliente.getCodigo());
			
			if (ps.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Cliente capturarClientesId(Integer id) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Cliente cliente;

		try {
			ps = conn.prepareStatement("select * from clientes where id_cliente = ?;");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
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
}
