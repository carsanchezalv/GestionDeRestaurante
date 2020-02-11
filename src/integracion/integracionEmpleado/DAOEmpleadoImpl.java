package integracion.integracionEmpleado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.TTurno;

public class DAOEmpleadoImpl implements DAOEmpleado {
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;

	public Integer altaEmpleado(TEmpleado empleado) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into empleado values (null,?,?,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setString(1, empleado.getDni());
			pStatement.setString(2, empleado.getNombre());
			pStatement.setBoolean(3, empleado.getActivo());
			pStatement.setInt(4, empleado.getIdTurno()); 
					
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				empleado.setId(rs.getInt(1));
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
	

	public Integer editarEmpleado(TEmpleado empleado) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select ID from empleado WHERE ID = ?");
			pStatement.setInt(1, empleado.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				
				pStatement = connection.prepareStatement("update empleado set activo = true, nombre = ?, dni = ?, id_turno = ? where id = " + empleado.getId());		
				pStatement.setString(1, empleado.getNombre());	//3
				pStatement.setString(2, empleado.getDni());	//2
				pStatement.setInt(3, empleado.getIdTurno());	//1
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

	public Integer bajaEmpleado(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update empleado set activo = false where id = " + id.toString());
			pStatement.executeUpdate();
			return 0;
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}

	public TEmpleado devolverEmpleado(Integer id) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				return empleado;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
		

	public LinkedList<TEmpleado> listarEmpleados() throws Exception {
		LinkedList<TEmpleado> listaEmpleados = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true ");
			rs = pStatement.executeQuery();
			
		
			while (rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				listaEmpleados.add(empleado);
				i++;
			}
			if(i == 0) {
				return null;
			}else {
				return listaEmpleados;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public LinkedList<TEmpleado> listarEmpleadosPorTurno(Integer idTurno) throws Exception{
		LinkedList<TEmpleado> listaEmpleados = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true and id_turno = " + idTurno.toString());
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				listaEmpleados.add(empleado);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaEmpleados;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public TEmpleado readByDNI(String dni) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE DNI = ?");
			pStatement.setString(1, dni);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getBoolean(4), rs.getInt(5));
				return empleado;
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

	public TTurno readByIdTurno(Integer id) throws Exception {
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
}