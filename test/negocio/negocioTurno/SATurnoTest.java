package negocio.negocioTurno;


	import static org.junit.Assert.*;

	
import java.util.LinkedList;

import org.junit.Test;


import negocio.negocioTurno.SATurno;
import negocio.negocioTurno.SATurnoImpl;
import negocio.negocioTurno.TTurno;

	public class SATurnoTest {
	
		@Test
		public void altaTurnoNoExistenteExito()
		{
			TTurno turno=new TTurno ("turno1",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				retorno = saTurno.altaTurno(turno);
				 assertTrue(retorno >-1); //Si lo ha añadido correctamente
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}

		@Test
		public void altaTurnoExistenteFallo()
		{
			TTurno turno=new TTurno ("turno2",1,2,true);
			TTurno turno1=new TTurno ("turno2",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {
				saTurno.altaTurno(turno);
				retorno=saTurno.altaTurno(turno1);
				 assertTrue(retorno <= -1); //Si lo ha añadido correctamente devuelve el error
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void editarTurnoNoExistenteFallo()
		{
			TTurno turno=new TTurno (220251515,"turno4",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null; 
			try {
				retorno=saTurno.editarTurno(turno);
				assertTrue(retorno!=0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void editarTurnoExistenteExito() 
		{
			TTurno turno=new TTurno ("turno5",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			Integer idTurno=null;
			try {
				idTurno=saTurno.altaTurno(turno);
				TTurno turno1=new TTurno (idTurno,"turno5",1,2,true);
				retorno=saTurno.editarTurno(turno1);
				assertTrue(retorno==0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		
		@Test
		public void listarTurnosExistentesExito()
		{
			TTurno turno=new TTurno ("turno11",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			LinkedList<TTurno> listaTurnos=null;
			try {
				saTurno.altaTurno(turno);
				listaTurnos=saTurno.listarTurnos();
				assertTrue(listaTurnos!=null); //Devuelve true si no esta vacia la lista
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		
		@Test
		public void bajaTurnoExistenteExito()
		{
			TTurno turno=new TTurno ("turno12",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			Integer idTurno=null;
			try {
				idTurno =saTurno.altaTurno(turno);
				retorno=saTurno.bajaTurno(idTurno);
				assertTrue(retorno==0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void bajaTurnoNoExistenteFallo()
		{
			TTurno turno=new TTurno (1212,"turno13",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			Integer retorno=null;
			try {   //No se añade el turno
				retorno=saTurno.bajaTurno(turno.getId());
				assertTrue(retorno!=0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void mostrarTurnoExistentExito()
		{
			TTurno turno=new TTurno ("turno3321",1,2,true);
			SATurno saTurno=new SATurnoImpl();
			TTurno retorno=null;
			Integer idTurno=null;
			try {
				idTurno=saTurno.altaTurno(turno);
				retorno=saTurno.mostrarTurno(idTurno);
				assertTrue(retorno!=null); 
			}
			catch(Exception e) 
			{
				fail(e.getMessage());
			}
		}
		
		@Test
		public void devolverTurnoNoExistenteFallo()
		{
			
			TTurno turno=new TTurno (00017,"turno17",1,2,true); 
			SATurno saTurno=new SATurnoImpl();
			TTurno retorno=null;
			try {
				//No da de alta el turno
				retorno=saTurno.mostrarTurno(turno.getId());
				assertTrue(turno!=retorno);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
		}
		
	}

