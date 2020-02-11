package negocio.negocioCliente;

import java.util.LinkedList;

public interface SACliente {
	public abstract Integer altaCliente(TCliente cliente) throws Exception;
	public abstract Integer editarCliente(TCliente cliente) throws Exception;
	public abstract TCliente devolverCliente(Integer id) throws Exception;
	public abstract LinkedList<TCliente> listarClientes() throws Exception;
}