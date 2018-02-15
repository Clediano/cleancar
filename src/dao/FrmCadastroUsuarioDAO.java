package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Usuarios;

public class FrmCadastroUsuarioDAO {

	/**
	 * Retornar verdadeiro se o usuário existe no banco de dados. Caso não existir
	 * este usuário no banco de dados, é retornado falso.
	 * 
	 * @param String
	 *            usuario
	 * @return boolean
	 */
	public boolean verificarUsuario(String usuario) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("select usu_nome from usuarios where usu_nome = ?;");
			ps.setString(1, usuario);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public boolean cadastrarUsario(String usuario, String senha) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("insert into usuarios (usu_nome, usu_senha) values (?,?);");
			ps.setString(1, usuario);
			ps.setString(2, senha);

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
	 * Retorna um ObservableList contendo todos os usuários do banco de dados.
	 * 
	 * @return ObservableList
	 */
	public ObservableList<Usuarios> capturarTodosUsuarios() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Usuarios usuario;
		ObservableList<Usuarios> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from usuarios;");

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
	 * Retorna um ObservableList contendo todos os usuários do banco de dados.
	 * 
	 * @param String
	 *            contendo o possivel usuário(login);
	 * @return ObservableList
	 */
	public ObservableList<Usuarios> capturarUsuariosLogin(String login) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Usuarios usuario;
		ObservableList<Usuarios> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from usuarios where usu_nome like ?;");
			ps.setString(1, '%' + login + '%');

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
	 * Retorna um ObservableList contendo todos os usuários do banco de dados.
	 * 
	 * @param String
	 *            contendo a possivel senha;
	 * @return ObservableList
	 */
	public ObservableList<Usuarios> capturarUsuariosSenha(String senha) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Usuarios usuario;
		ObservableList<Usuarios> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from usuarios where usu_senha like ?;");
			ps.setString(1, '%' + senha + '%');

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
	 *            do usuário a ser deletado
	 * @return Boolean
	 */
	public boolean removerUsuario(Integer idUsuario) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("delete from usuarios where idusuarios = ?;");
			ps.setInt(1, idUsuario);

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
