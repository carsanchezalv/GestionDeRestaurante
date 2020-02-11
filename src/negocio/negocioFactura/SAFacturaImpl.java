package negocio.negocioFactura;

import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionFactura.DAOFactura;
import negocio.negocioCliente.TCliente;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioProducto.TProducto;

public class SAFacturaImpl implements SAFactura {
	
	public TProducto anadirProducto(TProducto p) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TProducto producto = null;
		
		producto = facturaDAO.readByIdProducto(p.getId());
		if(producto == null || producto.getActivo() == false)
			return null;
		
		else{
			if(producto.getCantidad() == 0 || p.getCantidad() > producto.getCantidad()){
				return null;
			}
			else{
				return facturaDAO.anadirProducto(p);
			}
		}
	}
	
	public double aplicarDescuento(TFactura factura) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		
		if(factura.getDescuento() < 0){
			return -1;
		}
		return facturaDAO.aplicarDescuento(factura);
	}
	
	public TProducto borrarProducto(TProducto p) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDao = dao.generaDAOFactura();
		
		return facturaDao.borrarProducto(p);
	}

	public TFactura mostrarFactura(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		TFactura aux = null;
		
		aux = facturaDAO.mostrarFactura(id);
		return aux;
	}

	public Integer abrirFactura(TFactura factura) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		
		TCliente cliente = null;
		TEmpleado empleado = null;
		
		cliente = facturaDAO.readByIdCliente(factura.getIdCliente());
		empleado = facturaDAO.readByIdEmpleado(factura.getIdEmpleado());
		
		if(cliente == null || empleado == null){
			return -1;
		}
		
		return facturaDAO.abrirFactura(factura);
	}

	public Integer cerrarFactura(TFactura factura) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		
		return facturaDAO.cerrarFactura(factura);
	}
	
	public LinkedList<TFactura> listarFacturasPorCliente(Integer idCliente)throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOFactura facturaDAO = dao.generaDAOFactura();
		LinkedList<TFactura> aux = null;
		
		aux = facturaDAO.listarFacturasPorCliente(idCliente);
		return aux;
	}
}