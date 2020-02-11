package integracion.integracionTurno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import negocio.negocioTurno.TTurno;

public class DAOTurnoImpl implements DAOTurno{
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;

	public Integer altaTurno(TTurno turno) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into Turno values (null,?,?,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setString(1, turno.getNombre());
			pStatement.setInt(2, turno.getHoraInicio());
			pStatement.setInt(3, turno.getHoraFin());
			pStatement.setBoolean(4, turno.getActivo());
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				turno.setId(rs.getInt(1));
				return rs.getInt(1);
			}else {
				return -1;
			}
				
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public Integer editarTurno(TTurno turno) throws Exception{
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			
			pStatement = connection.prepareStatement("select ID from turno WHERE ID = ?");
			pStatement.setInt(1, turno.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement = connection.prepareStatement("update turno set activo = true, nombre = ?, horainicio = ?, horafin = ? where id = ?");	
				pStatement.setString(1, turno.getNombre());
				pStatement.setInt(2, turno.getHoraInicio());
				pStatement.setInt(3, turno.getHoraFin());
				pStatement.setInt(4, turno.getId());
				pStatement.executeUpdate();		
				return 0;
			}else {
				return -1;
			}	
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}

	public Integer bajaTurno(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			Statement statement = connection.createStatement();
			String sql = "update turno set activo = false where id =" + id;
			statement.executeUpdate(sql);
			
			return 0;
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public TTurno mostrarTurno(Integer id) throws Exception{		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from turno WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), true);
				return turno;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public LinkedList<TTurno> listarTurnos() throws Exception{
		LinkedList<TTurno> listaTurnos = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from turno WHERE activo = true ");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), true);
				listaTurnos.add(turno);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaTurnos;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public TTurno readByNombre(String Nombre) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from turno where nombre = ?");
			pStatement.setString(1, Nombre);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TTurno turno = new TTurno(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5));
				return turno;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	private void closeAll() {
		if(rs != null) {
			try {
				rs.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(pStatement != null) {
			try {
				pStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}