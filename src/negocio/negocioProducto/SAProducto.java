package negocio.negocioProducto;

import java.util.LinkedList;

public interface SAProducto {
	public abstract Integer altaProducto(TProducto producto) throws Exception;
	public abstract Integer editarProducto(TProducto producto) throws Exception;
	public abstract Integer bajaProducto(Integer id) throws Exception;
	public abstract TProducto devolverProducto(Integer id) throws Exception;
	public abstract LinkedList<TProducto> listarProductos() throws Exception;
}