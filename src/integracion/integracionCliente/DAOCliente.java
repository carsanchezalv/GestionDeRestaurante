package integracion.integracionCliente;

import java.util.LinkedList;
import negocio.negocioCliente.TCliente;

public interface DAOCliente {
	public abstract Integer altaCliente(TCliente cliente) throws Exception;
	public abstract Integer editarCliente(TCliente cliente) throws Exception;
	public abstract TCliente devolverCliente(Integer id) throws Exception;
	public abstract LinkedList<TCliente> listarClientes() throws Exception;
	public abstract TCliente readByNombre(String nombre) throws Exception;
}