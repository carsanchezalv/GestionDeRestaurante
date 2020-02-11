package negocio.negocioTurno;

import java.util.LinkedList;

public interface SATurno {
	public abstract Integer altaTurno(TTurno turno) throws Exception;
	public abstract Integer editarTurno(TTurno turno) throws Exception;
	public abstract Integer bajaTurno(Integer id) throws Exception;
	public abstract TTurno mostrarTurno(Integer id) throws Exception;
	public abstract LinkedList<TTurno> listarTurnos() throws Exception;
}