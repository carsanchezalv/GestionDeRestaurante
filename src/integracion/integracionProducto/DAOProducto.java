package integracion.integracionProducto;

import java.util.LinkedList;

import negocio.negocioProducto.TProducto;

public interface DAOProducto {
	public abstract Integer altaProducto(TProducto producto) throws Exception;
	public abstract Integer editarProducto(TProducto producto) throws Exception;
	public abstract Integer bajaProducto(Integer id) throws Exception;
	public abstract TProducto devolverProducto(Integer id) throws Exception;
	public abstract LinkedList<TProducto> listarProductos() throws Exception;
	public abstract TProducto readByNombre(String nombre) throws Exception;
}