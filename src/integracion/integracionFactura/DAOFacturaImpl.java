package integracion.integracionFactura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import negocio.negocioCliente.TCliente;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.TProducto;

public class DAOFacturaImpl implements DAOFactura {
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;
	
	
	public TProducto anadirProducto(TProducto p) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from productos where id = " + p.getId());
			rs = pStatement.executeQuery();
			if(rs.next()){
				TProducto pr = null;
				int cantidad = rs.getInt(2);
				if(cantidad >= p.getCantidad()) {
					Integer idProducto = rs.getInt(1);
					String nombre = rs.getString(7);
					Double precio = rs.getDouble(3);
					Integer calorias = rs.getInt(6);
					Integer cantidadP = p.getCantidad();
					Boolean activo = rs.getBoolean(8);
					pr = new TProducto(idProducto, nombre, precio, calorias, cantidadP, activo);
					cantidad -= p.getCantidad();
					pStatement = connection.prepareStatement("update productos set cantidad = " + cantidad + " where id = " + p.getId());
					pStatement.executeUpdate();
					return pr;
				}
				return pr;
			}
			else {
				return null;
			}
		
		} catch (Exception e) {
			return null;
		}finally {
			closeAll();
		}
	}

	public double aplicarDescuento(TFactura factura) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update factura set descuento = ? where id = ?");
			pStatement.setDouble(1, factura.getDescuento());
			pStatement.setInt(2, factura.getId());
			pStatement.executeUpdate();
			return factura.getDescuento();
		} catch (Exception e) {
			return -1;
		}finally {
			closeAll();
		}
	}

	public TProducto borrarProducto(TProducto p) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select cantidad from productos where id = ?");
			pStatement.setInt(1, p.getId()); 
			rs = pStatement.executeQuery();
			if(rs.next()) {
				int cantidad = rs.getInt(1);
				cantidad += p.getCantidad();
				pStatement = connection.prepareStatement("update productos set cantidad = ? where id = ?");
				pStatement.setInt(1, cantidad);
				pStatement.setInt(2, p.getId());
				pStatement.executeUpdate();
				return p;
			}
			else{
				return null;
			}
			/*int cantidad = rs.getInt(1);
			cantidad += p.getCantidad();
			pStatement = connection.prepareStatement("update cantidad = ? from productos where id = ?");
			pStatement.setInt(1, cantidad);
			pStatement.setInt(2, p.getId());
			pStatement.executeQuery();
			return 0;*/
			
		} catch (Exception e) {
			return null;
		}finally {
			closeAll();
		}
	}


	public TFactura mostrarFactura(Integer id) throws Exception {
		try {
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from factura WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TFactura factura = new TFactura(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDate(4), rs.getInt(6), rs.getInt(7));
				return factura;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally {
			closeAll();
		}
	}

	public Integer abrirFactura(TFactura factura) throws Exception {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into factura values (null,0,0,?,true,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setDate(1,sqlDate);
			pStatement.setInt(2, factura.getIdCliente());
			pStatement.setInt(3, factura.getIdEmpleado());
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				factura.setId(rs.getInt(1));
				factura.setFecha(sqlDate);
				return rs.getInt(1);
			}else {
				return -1;
			}		
		} catch (Exception e) {
			return -1;
		}finally {
			closeAll();
		}
		
	}
	public Integer cerrarFactura(TFactura factura) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("update productos set precio = ? where id = ?"); // "update precio = ? from factura where id = ?"
			pStatement.setDouble(1, factura.getPrecio());
			pStatement.setInt(2,  factura.getId());
			pStatement.executeUpdate();
			
			return 0;
		} catch (Exception e) {
			return null;
		}finally {
			closeAll();
		}
	}
	
	public TCliente readByIdCliente(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from cliente WHERE ID = ?");
			pStatement.setInt(1, id);
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
	
	public TEmpleado readByIdEmpleado(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from empleado WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TEmpleado empleado = new TEmpleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
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
	
	public TProducto readByIdProducto(Integer id) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from productos WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				Integer idProducto = rs.getInt(1);
				String nombre = rs.getString(7);
				Double precio = rs.getDouble(3);
				Integer calorias = rs.getInt(6);
				Integer cantidad = rs.getInt(2);
				Boolean activo = rs.getBoolean(8);
				TProducto producto = new TProducto(idProducto, nombre, precio, calorias, cantidad, activo);
				return producto;
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
	
	public LinkedList<TFactura> listarFacturasPorCliente(Integer idCliente) throws Exception {
		LinkedList<TFactura> listaClientes = new LinkedList<>();
		try {
			int i = 0;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from factura where Cliente_ID = " + idCliente.toString());
			rs = pStatement.executeQuery();
			while (rs.next()) {
				TFactura cliente = new TFactura(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getDate(4), rs.getInt(5), rs.getInt(6));
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
}