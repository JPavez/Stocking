package org.stocking.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.stocking.beans.Insumo;

public class InsumoDao {

	public void insert(Insumo insumito) throws SQLException{
		String sql = "insert into insumos (id, nombre, cantidad) values (null, ?, ?)";
		Connection con = SQLiteConnectionFactory.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql, 1);
		stmt.setString(1, insumito.getNombre());
		stmt.setInt(2, insumito.getCantidad().intValue());
		stmt.executeUpdate();
	}
}
