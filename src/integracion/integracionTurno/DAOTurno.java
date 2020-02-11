package integracion.integracionTurno;

import java.util.LinkedList;
import negocio.negocioTurno.TTurno;

public interface DAOTurno {
	public abstract Integer altaTurno(TTurno turno) throws Exception;
	public abstract Integer editarTurno(TTurno turno) throws Exception;
	public abstract Integer bajaTurno(Integer id) throws Exception;
	public abstract TTurno mostrarTurno(Integer id) throws Exception;
	public abstract LinkedList<TTurno> listarTurnos() throws Exception;
	public abstract TTurno readByNombre(String Nombre) throws Exception;
}