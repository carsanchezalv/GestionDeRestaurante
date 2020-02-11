package negocio.negocioCliente;

import java.util.LinkedList;

import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionCliente.DAOCliente;

public class SAClienteImpl implements SACliente {
	
	public Integer altaCliente(TCliente cliente) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		
		aux = clienteDAO.readByNombre(cliente.getNombre());
		if(aux == null){
			return clienteDAO.altaCliente(cliente);
		}
		else{
			return -1;
		}
	}
	
	public Integer editarCliente(TCliente cliente) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		TCliente aux2 = null;
		
		aux = clienteDAO.devolverCliente(cliente.getId());
		if(aux == null){
			return -1;
		}
		else{
			aux2 = clienteDAO.readByNombre(cliente.getNombre());
			if(aux2!= null && aux2.getNombre().equalsIgnoreCase(cliente.getNombre()) && aux2.getId() != cliente.getId()){
				return -2;
			}
			else{
				return clienteDAO.editarCliente(cliente);
			}
		}
	}
	
	public TCliente devolverCliente(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		TCliente aux = null;
		
		aux = clienteDAO.devolverCliente(id);
		return aux;
	}
	
	public LinkedList<TCliente> listarClientes() throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOCliente clienteDAO = dao.generaDAOCliente();
		LinkedList<TCliente> aux = null;
		
		aux = clienteDAO.listarClientes();
		return aux;
	}
}