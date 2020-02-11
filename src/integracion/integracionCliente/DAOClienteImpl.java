package integracion.integracionCliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import negocio.negocioCliente.TCliente;

public class DAOClienteImpl implements DAOCliente {
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;
	
	public Integer altaCliente(TCliente cliente) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into cliente values (null,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setString(1, cliente.getNombre());
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				cliente.setId(rs.getInt(1));
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
	
	public Integer editarCliente(TCliente cliente) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			
			pStatement = connection.prepareStatement("select ID from cliente WHERE ID = ?");
			pStatement.setInt(1, cliente.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement = connection.prepareStatement("update cliente set nombre = ? where id = ?");
				pStatement.setString(1, cliente.getNombre());
				pStatement.setInt(2, cliente.getId());				 
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
	
	public TCliente devolverCliente(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente where ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TCliente cliente = new TCliente(id, rs.getString(2));
				return cliente;
			}else {
				return null;
			}			
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public LinkedList<TCliente> listarClientes() throws Exception {
		LinkedList<TCliente> listaClientes = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente");
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TCliente cliente = new TCliente(rs.getInt(1), rs.getString(2));
				listaClientes.add(cliente);
				i++;
			}
			
			if(i == 0) {
				return null;
			}else {
				return listaClientes;
			}
			
		} catch (Exception e) {
			throw new Exception();
		}finally {
			closeAll();
		}
	}
	
	public TCliente readByNombre(String nombre) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente where nombre = ?");
			pStatement.setString(1, nombre);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TCliente cliente = new TCliente(rs.getInt(1), rs.getString(2));
				return cliente;
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