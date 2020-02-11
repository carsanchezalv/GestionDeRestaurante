package negocio.negocioEmpleado;

import java.util.LinkedList;

import negocio.negocioTurno.TTurno;
import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionEmpleado.DAOEmpleado;
import integracion.integracionTurno.DAOTurno;

public class SAEmpleadoImpl implements SAEmpleado {
	
	public Integer altaEmpleado(TEmpleado empleado) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		TTurno turno = null;
		
		turno = empleadoDAO.readByIdTurno(empleado.getIdTurno());
		if(turno == null){
			return -1;
		}
		else{
			aux = empleadoDAO.readByDNI(empleado.getDni());
			if(aux == null){
				return empleadoDAO.altaEmpleado(empleado);
			}
			else if(aux != null && aux.getActivo() == false){
				aux.setActivo(true);
				aux.setNombre(empleado.getNombre());
				aux.setIdTurno(empleado.getIdTurno());
				empleadoDAO.editarEmpleado(aux);
				return aux.getId();
			}
			else{
				return -2;
			}
		}
	}
	
	public Integer editarEmpleado(TEmpleado empleado) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		TEmpleado aux2 = null;
		
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno turno = null;
		
		aux = empleadoDAO.devolverEmpleado(empleado.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			turno = turnoDAO.mostrarTurno(empleado.getIdTurno());
			aux2 = empleadoDAO.readByDNI(empleado.getDni());
			if(aux2 != null && aux2.getDni().equalsIgnoreCase(empleado.getDni()) && aux2.getId() != empleado.getId()){
				return -2;
			}
			else if(turno == null){
				return -3;
			}
			else{
				return empleadoDAO.editarEmpleado(empleado);
			}
		}
	}
	
	public Integer bajaEmpleado(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(id);
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			empleadoDAO.bajaEmpleado(id);
			return id;
		}
	}
	
	public TEmpleado devolverEmpleado(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		TEmpleado aux = null;
		
		aux = empleadoDAO.devolverEmpleado(id);
		return aux;
	}
	
	public LinkedList<TEmpleado> listarEmpleados() throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		LinkedList<TEmpleado> aux = null;
		
		aux = empleadoDAO.listarEmpleados();
		return aux;
	}
	
	public LinkedList<TEmpleado> listarEmpleadosPorTurno(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		LinkedList<TEmpleado> aux = null;
		
		aux = empleadoDAO.listarEmpleadosPorTurno(id);
		return aux;
	}
}