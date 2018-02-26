package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import model.Placa;

public class FrmCadastroPlacaDAO {
	
	public boolean adicionarPlaca(Placa placa) {
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("insert into placas values plc_numero = ?;");
			ps.setString(1, placa.getNumero());

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
}
