package negocio.negocioProducto;

import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionProducto.DAOProducto;

public class SAProductoImpl implements SAProducto {

	public Integer altaProducto(TProducto producto) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.readByNombre(producto.getNombre());
		if(aux != null){
			if(aux.getActivo() == false){
				aux.setActivo(true);
				aux.setNombre(producto.getNombre());
				aux.setCantidad(producto.getCantidad());
				aux.setCalorias(producto.getCalorias());
				aux.setPrecio(producto.getPrecio());
				productoDAO.editarProducto(aux);
				return aux.getId();
			}
			else{
				return -1;
			}
		}
		else{
			if(producto.getPrecio() < 0 || producto.getCantidad() < 0 || producto.getCalorias() < 0){
				return -2;
			}
			else{
				return productoDAO.altaProducto(producto);
			}
		}
	}
	
	public Integer editarProducto(TProducto producto) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		TProducto aux2 = null;
		
		aux = productoDAO.devolverProducto(producto.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			aux2 = productoDAO.readByNombre(producto.getNombre());
			if(aux2!= null && aux2.getNombre().equalsIgnoreCase(producto.getNombre()) && aux2.getId() != producto.getId()){
				return -2;
			}
			else if(producto.getPrecio() < 0 || producto.getCantidad() < 0 || producto.getCalorias() < 0){
				return -3;
			}
			else{
				return productoDAO.editarProducto(producto);
			}
		}
	}
	
	public Integer bajaProducto(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(id);
		if(aux == null){
			return -1;
		}
		else{
			productoDAO.bajaProducto(id);
			return id;
		}
	}
	
	public TProducto devolverProducto(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		TProducto aux = null;
		
		aux = productoDAO.devolverProducto(id);
		return aux;
	}
	
	public LinkedList<TProducto> listarProductos() throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOProducto productoDAO = dao.generaDAOProducto();
		LinkedList<TProducto> aux = null;
		
		aux = productoDAO.listarProductos();
		return aux;
	}
}