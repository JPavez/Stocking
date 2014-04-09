package org.stocking.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.stocking.beans.Insumo;

/**
 * esta clase se conecta a una base de datos y guarda los parametros del objeto Insumo,
 * luego crea un objeto lista con los valores de la tabla insumos.
 * @author joincube
 *
 */
public class InsumoDao {

	/**
	 * inserta dentro de la base de datos los campos id, nombre, cantidad, sacados del objeto Insumo.
	 * @param insumito parametro obtengo la informacion del objeto para insertarlo en base de datos.
	 * @throws SQLException
	 */
	public void insert(Insumo insumito) throws SQLException{
		String sql = "insert into insumos (id, nombre, cantidad) values (null, ?, ?)";
		Connection con = SQLiteConnectionFactory.getInstance().getConnection();
		PreparedStatement stmt = con.prepareStatement(sql, 1);
		stmt.setString(1, insumito.getNombre());
		stmt.setInt(2, insumito.getCantidad().intValue());
		stmt.executeUpdate();
	}
	/**
	 * este metodo crea uno o varios objetos insumos con los campos de la tabla insumo base de datos.
	 * @param selectInsumo es un parametro que uso para completar el query.
	 * @return lista de insumos que viene de la base de datos.
	 * @throws SQLException
	 */
	public List<Insumo> select(Insumo selectInsumo) throws SQLException{
		String nom = selectInsumo.getNombre();
		Long id = selectInsumo.getId();
		Connection con = SQLiteConnectionFactory.getInstance().getConnection();
		String sql = null;
		
		if (nom != null && !nom.trim().equals("")){
			sql = "select id, nombre, cantidad from insumos where nombre = ? ";
			if(id != null){
				sql = sql + "and id = ?";
			}
		}else if (id != null){
			sql = "select id, nombre, cantidad from insumos where id = ?";
		}else{
			sql = "select id, nombre, cantidad from insumos";
		}

		PreparedStatement stmt = con.prepareStatement(sql, 1);
		int param = 1;
		if (nom != null && !nom.trim().equals("")){
			stmt.setString(param, selectInsumo.getNombre());
			param ++;
		}
		if (id != null){
			stmt.setLong(param, id.longValue());
		}
		ResultSet resultSet = stmt.executeQuery();
		List<Insumo> insumoLista = new ArrayList<Insumo>();
		while (resultSet.next()){
			Insumo nuevoInsumo = new Insumo();
			nuevoInsumo.setId(resultSet.getLong(1));
			nuevoInsumo.setNombre(resultSet.getString(2));
			nuevoInsumo.setCantidad(resultSet.getInt(3));
			insumoLista.add(nuevoInsumo);
		}
		return insumoLista;
	}
	/**
	 * este metodo me actualiza la base de datos, con la nueva cantidad del objeto Insumo.
	 * @param updateInsumo es un parametro que uso para conseguir la informacion del objeto.
	 * @throws IOException
	 * @throws SQLException
	 */
	public void update(Insumo updateInsumo) throws IOException, SQLException{
		Connection con = SQLiteConnectionFactory.getInstance().getConnection();
		String sql = "update insumos set nombre = ?, cantidad = ? where id = ? ";
		PreparedStatement stmt = con.prepareStatement(sql, 1);
		stmt.setString(1, updateInsumo.getNombre());
		stmt.setInt(2, updateInsumo.getCantidad());
		stmt.setLong(3, updateInsumo.getId());
		stmt.executeUpdate();
	}
}