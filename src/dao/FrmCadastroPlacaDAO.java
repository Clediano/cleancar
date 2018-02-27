package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdbc.ConnectionFactory;
import model.Placa;

public class FrmCadastroPlacaDAO {
	
	public boolean adicionarPlaca(Placa placa) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("insert into placas (plc_numero, plc_data_cadastro) values (?,?);");
			ps.setString(1, placa.getNumero());
			ps.setDate(2, placa.getDataCadastro());
			
			if(ps.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean removerPlaca(Integer idPlaca) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM placas where id_placa = ?;");
			ps.setInt(1, idPlaca);

			if(ps.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Boolean editarPlaca(Placa placa) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE placas SET "
											+ "plc_data_cadastro = ?, "
											+ "plc_numero = ? where id_placa = ?;");
			
			ps.setDate(1, placa.getDataCadastro());
			ps.setString(2, placa.getNumero());
			ps.setInt(3, placa.getId());

			if(ps.executeUpdate() == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Placa pegarPlaca(Integer idPlaca) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Placa placa;
		try {
			ps = conn.prepareStatement("select * from placas where id_placa = ?;");
			ps.setInt(1, idPlaca);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				placa = new Placa();
				
				placa.setId(rs.getInt(1));
				placa.setNumero(rs.getString(2));
				
				return placa;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer pegarIdProximaPlaca() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("select max(id_placa) from placas;");
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ObservableList<Placa> pegarPlacaData(String data) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Placa placa;
		ObservableList<Placa> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from placas where plc_data_cadastro = ?;");
			ps.setString(1, "%" + data + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				placa = new Placa();

				placa.setId(rs.getInt(1));
				placa.setDataCadastro(rs.getDate(2));
				placa.setNumero(rs.getString(3));
				
				lista.add(placa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Placa> pegarPlacaNumero(String numero) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Placa placa;
		ObservableList<Placa> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from placas where plc_numero = ?;");
			ps.setString(1, "%" + numero + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				placa = new Placa();

				placa.setId(rs.getInt(1));
				placa.setDataCadastro(rs.getDate(2));
				placa.setNumero(rs.getString(3));
				
				lista.add(placa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public ObservableList<Placa> pegarTodasPlaca() {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;
		ResultSet rs;
		Placa placa;
		ObservableList<Placa> lista = FXCollections.observableArrayList();

		try {
			ps = conn.prepareStatement("select * from placas;");

			rs = ps.executeQuery();

			while (rs.next()) {
				placa = new Placa();

				placa.setId(rs.getInt(1));
				placa.setDataCadastro(rs.getDate(2));
				placa.setNumero(rs.getString(3));
				
				lista.add(placa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
