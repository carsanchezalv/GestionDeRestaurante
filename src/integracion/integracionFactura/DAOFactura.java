package integracion.integracionFactura;

import java.util.LinkedList;

import negocio.negocioCliente.TCliente;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.TProducto;

public interface DAOFactura {
	public abstract TProducto anadirProducto(TProducto p) throws Exception;
	public abstract double aplicarDescuento(TFactura factura) throws Exception;
	public abstract TProducto borrarProducto(TProducto p) throws Exception;
	public abstract TFactura mostrarFactura(Integer id) throws Exception;
	public abstract Integer abrirFactura(TFactura factura) throws Exception;
	public abstract Integer cerrarFactura(TFactura factura) throws Exception;
	public abstract LinkedList<TFactura> listarFacturasPorCliente(Integer idCliente) throws Exception;
	public abstract TCliente readByIdCliente(Integer id) throws Exception;
	public abstract TEmpleado readByIdEmpleado(Integer id) throws Exception;
	public abstract TProducto readByIdProducto(Integer id) throws Exception;
}