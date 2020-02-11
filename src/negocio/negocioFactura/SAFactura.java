package negocio.negocioFactura;

import java.util.LinkedList;

import negocio.negocioProducto.TProducto;

public interface SAFactura {
	public abstract TProducto anadirProducto(TProducto p) throws Exception;
	public abstract double aplicarDescuento(TFactura factura) throws Exception;
	public abstract TProducto borrarProducto(TProducto p) throws Exception;
	public abstract TFactura mostrarFactura(Integer id) throws Exception;
	public abstract Integer abrirFactura(TFactura factura) throws Exception;
	public abstract Integer cerrarFactura(TFactura factura) throws Exception;
	public abstract LinkedList<TFactura> listarFacturasPorCliente(Integer idCliente) throws Exception;
}