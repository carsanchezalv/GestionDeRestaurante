package negocio.negocioTurno;

import java.util.LinkedList;

import negocio.negocioEmpleado.TEmpleado;
import integracion.factoriaDAO.FactoriaDAO;
import integracion.integracionEmpleado.DAOEmpleado;
import integracion.integracionTurno.DAOTurno;

public class SATurnoImpl implements SATurno {
	
	public Integer altaTurno(TTurno turno) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.readByNombre(turno.getNombre());
		if(aux != null){
			if(aux.getActivo() == false){
				aux.setActivo(true);
				aux.setNombre(turno.getNombre());
				aux.setHoraInicio(turno.getHoraInicio());
				aux.setHoraFin(turno.getHoraFin());
				turnoDAO.editarTurno(aux);
				return aux.getId();
			}
			else{
				return -1;
			}
		}
		else{
			if(turno.getHoraFin() < 0 || turno.getHoraFin() > 23 || turno.getHoraInicio() < 0 || turno.getHoraInicio() > 23){
				return -2;
			}
			else{
				return turnoDAO.altaTurno(turno);
			}
		}
	}
	
	public Integer editarTurno(TTurno turno) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		TTurno aux2 = null;
		
		aux = turnoDAO.mostrarTurno(turno.getId());
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			aux2 = turnoDAO.readByNombre(turno.getNombre());
			if(aux2!= null && aux2.getNombre().equalsIgnoreCase(turno.getNombre()) && aux2.getId() != turno.getId()){
				return -2;
			}
			else if(turno.getHoraFin() < 1 || turno.getHoraFin() > 23){
				return -3;
			}
			else{
				return turnoDAO.editarTurno(turno);
			}
		}
	}
	
	public Integer bajaTurno(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		LinkedList<TEmpleado> listaEmpleados = null;
		
		DAOEmpleado empleadoDAO = dao.generaDAOEmpleado();
		
		aux = turnoDAO.mostrarTurno(id);
		if(aux == null || aux.getActivo() == false){
			return -1;
		}
		else{
			listaEmpleados = empleadoDAO.listarEmpleadosPorTurno(id);
			if(listaEmpleados == null){
				return turnoDAO.bajaTurno(id);
			}
			else{
				return -2;
			}		
		}
	}
	
	public TTurno mostrarTurno(Integer id) throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		TTurno aux = null;
		
		aux = turnoDAO.mostrarTurno(id);
		return aux;
	}

	public LinkedList<TTurno> listarTurnos() throws Exception {
		FactoriaDAO dao = FactoriaDAO.getInstancia();
		DAOTurno turnoDAO = dao.generaDAOTurno();
		LinkedList<TTurno> aux = null;
		
		aux = turnoDAO.listarTurnos();
		return aux;
	}
}