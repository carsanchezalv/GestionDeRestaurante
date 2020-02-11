package integracion.integracionProducto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import negocio.negocioProducto.TProducto;

public class DAOProductoImpl implements DAOProducto {
	
	Connection connection;
	PreparedStatement pStatement;
	ResultSet rs;
	
	@Override
	public Integer altaProducto(TProducto producto) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("insert into productos values (null,?,?,?,?,?,?,?)",
					   PreparedStatement.RETURN_GENERATED_KEYS);
			
			pStatement.setDouble(1, producto.getCantidad());
			pStatement.setDouble(2, producto.getPrecio());
			pStatement.setDouble(3, producto.getCantidadComida());
			pStatement.setDouble(4, producto.getVolumen());
			pStatement.setInt(5, producto.getCalorias());
			pStatement.setString(6, producto.getNombre());//quizas hay que ponerle comillas
			pStatement.setBoolean(7, producto.getActivo());//o true directamente
			
			pStatement.executeUpdate();
			rs = pStatement.getGeneratedKeys();
			if(rs.next()) {
				producto.setId(rs.getInt(1));
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

	

	@Override
	public Integer editarProducto(TProducto producto) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			
			pStatement = connection.prepareStatement("select ID from productos WHERE ID = ?");
			pStatement.setInt(1, producto.getId());
			rs = pStatement.executeQuery();
			if(rs.next()) {
				pStatement = connection.prepareStatement("update productos set activo = true, calorias = ?, cantidad = ?, cantidadcomida = ?, nombre = ?,"
						+ "precio = ?, volumen = ? where id = ?");	
				pStatement.setDouble(2, producto.getCantidad());
				pStatement.setDouble(5, producto.getPrecio());
				pStatement.setDouble(3, producto.getCantidadComida());
				pStatement.setDouble(6, producto.getVolumen());
				pStatement.setInt(1, producto.getCalorias());
				pStatement.setString(4, producto.getNombre());//comillas	
				pStatement.setInt(7,  producto.getId());
				pStatement.executeUpdate();		
				return 0;
			}else {
				return -1;
			}	
			
		} catch (Exception e) {
			return -1;
		}finally {
			closeAll();
		}
	}

	@Override
	public Integer bajaProducto(Integer id) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			Statement statement = connection.createStatement();
			String sql = "update productos set activo = false where id =" + id;
			statement.executeUpdate(sql);
			
			return 0;
		} catch (Exception e) {
			return -1;
		}finally {
			closeAll();
		}
	}

	@Override
	public TProducto devolverProducto(Integer id) throws Exception {
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from productos WHERE activo = true and ID = ?");
			pStatement.setInt(1, id);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TProducto producto = new TProducto(id, rs.getString(7), rs.getDouble(3), rs.getInt(6), rs.getInt(2), rs.getBoolean(8));
				return producto;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}finally {
			closeAll();
		}
	}
	
	public LinkedList<TProducto> listarProductos() throws Exception {
		
		LinkedList<TProducto> listaProductos = new LinkedList<>();
				try {
					int i = 0;
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
					pStatement = connection.prepareStatement("select * from productos WHERE activo = true ");
					rs = pStatement.executeQuery();
					while (rs.next()) {
						TProducto producto = new TProducto(rs.getInt(1), rs.getString(7), rs.getDouble(3), rs.getInt(6), rs.getInt(2), rs.getBoolean(8));
						listaProductos.add(producto);
						i++;
					}
					
					if(i == 0) 
						return null;
					else 
						return listaProductos;
					
				} catch (Exception e) {
					return null;
				}finally {
					closeAll();
				}
	}
	
	public TProducto readByNombre(String nombre) throws Exception {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grupo202db", "root", "");
			pStatement = connection.prepareStatement("select * from productos WHERE nombre = ?");
			pStatement.setString(1, nombre);
			rs = pStatement.executeQuery();
			if(rs.next()) {
				TProducto producto = new TProducto(rs.getInt(1), rs.getString(7), rs.getDouble(3), rs.getInt(6), rs.getInt(2), rs.getBoolean(8));
				return producto;
			}else {
				return null;
			}
			
		} catch (Exception e) {
			return null;
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
