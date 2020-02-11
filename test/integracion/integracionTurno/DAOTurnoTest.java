package integracion.integracionTurno;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;
import negocio.negocioTurno.TTurno;



public class DAOTurnoTest {

	@Test
	public void altaTurnoNoExistenteExito() {
		TTurno turno=new TTurno ("tur1",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl();
		Integer retorno=null;
		try {
			retorno=daoTurno.altaTurno(turno);
			assertTrue(retorno != -1); //Si lo ha añadido correctamente
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}


	@Test
	public void editarTurnoExistenteExito() {
		TTurno turno=new TTurno ("tur5",1,2,true);
	
		DAOTurno daoTurno=new DAOTurnoImpl();
		Integer retorno=null;
		Integer idTurno=null;
		try {
			idTurno=daoTurno.altaTurno(turno);
			TTurno turno1=new TTurno (idTurno,"tur5",6,9,true);
			retorno=daoTurno.editarTurno(turno1);
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}

	@Test
	public void listarTurnosExistentesExito() {
		TTurno turno=new TTurno ("tur10",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl();
		LinkedList<TTurno> listaTurnos=null;
		try {
			daoTurno.altaTurno(turno);
			listaTurnos=daoTurno.listarTurnos();
			assertTrue(listaTurnos!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}


	@Test
	public void bajaTurnoExistenteExito() {
		TTurno turno=new TTurno ("turno11",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl();
		Integer retorno=null;
		try {
			retorno=daoTurno.altaTurno(turno);
			retorno=daoTurno.bajaTurno(turno.getId());
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}

	

	@Test
	public void mostrarTurnoExistenteExito() {
		TTurno turno=new TTurno ("tur15",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl();
		TTurno retorno=null;
		Integer idTurno=null;
		try {
			idTurno=daoTurno.altaTurno(turno);
			retorno=daoTurno.mostrarTurno(idTurno);
			assertTrue(retorno!=null); 
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	@Test
	public void mostrarTurnoNoExistenteFallo()
	{
		TTurno turno=new TTurno (00016,"tur16",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl();
		TTurno retorno=null;
		try {
			retorno=daoTurno.mostrarTurno(turno.getId());
			assertTrue(turno!=retorno);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}
