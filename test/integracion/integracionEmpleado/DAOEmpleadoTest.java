package integracion.integracionEmpleado;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import integracion.integracionTurno.DAOTurno;
import integracion.integracionTurno.DAOTurnoImpl;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.TTurno;

public class DAOEmpleadoTest {

	@Test
	public void altaEmpleadoNoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado("Empleado1", "0", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();
		Integer retorno = null;
		try 
		{
			retorno = daoEmpleado.altaEmpleado(empleado);
			assertTrue(retorno >-1); //Si lo ha añadido correctamente
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosDeTurnoExistenteExito() {
		TTurno turno=new TTurno ("tur9",1,2,true);
		DAOTurno daoTurno=new DAOTurnoImpl(); //////
		DAOEmpleado daoEmpleado=new DAOEmpleadoImpl();
		LinkedList<TEmpleado> retorno=null;
		Integer idTurno=null;
		try {
			idTurno=daoTurno.altaTurno(turno);
			TEmpleado empleado= new TEmpleado("emplead21","1",true,idTurno);
			daoEmpleado.altaEmpleado(empleado);
			retorno=daoEmpleado.listarEmpleadosPorTurno(turno.getId());
			assertTrue(retorno!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}
		

	@Test
	public void listarEmpleadosDeTurnoNoExistenteFallo() {
		TTurno turno=new TTurno (66654,"tur9",1,2,true);
		TEmpleado empleado= new TEmpleado(00021,"empleado21","2",true,00077);
		DAOEmpleado daoEmpleado=new DAOEmpleadoImpl();
		LinkedList<TEmpleado> retorno=null;
		try {
			daoEmpleado.altaEmpleado(empleado);
			retorno=daoEmpleado.listarEmpleadosPorTurno(turno.getId());
			assertTrue(retorno==null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}	}

	
	@Test
	public void editarEmpleadoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado("Empleado4", "4", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();
		Integer retorno = null;
		try
		{
			retorno =daoEmpleado.altaEmpleado(empleado);
			retorno = daoEmpleado.editarEmpleado(empleado);
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void borrarEmpleadoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado("Empleado5", "5", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();
		Integer retorno = null;
		Integer idEmpleado=null;
		try
		{
			idEmpleado =daoEmpleado.altaEmpleado(empleado);
			retorno =daoEmpleado.bajaEmpleado(idEmpleado);
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void devolverEmpleadoExistenteExito() 
	{
		TEmpleado empleado = new TEmpleado("Empleado7", "7", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();
		TEmpleado retorno = null;
		Integer idEmpleado=null;
		try
		{
			idEmpleado=daoEmpleado.altaEmpleado(empleado);
			retorno=daoEmpleado.devolverEmpleado(idEmpleado);
			assertTrue(retorno!=null);//assertNotNull(retorno);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolverEmpleadoNoExistenteFallo() 
	{
		TEmpleado empleado = new TEmpleado(0000010,"Empleado10", "8", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();	
		TEmpleado retorno = null;
		try
		{
			retorno=daoEmpleado.devolverEmpleado(empleado.getId());
			assertTrue(retorno==null);//assertNull(retorno);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosExistentesExito() 
	{
		TEmpleado empleado = new TEmpleado("Empleado11", "9", true, 10000);
		DAOEmpleado daoEmpleado = new DAOEmpleadoImpl();	
		LinkedList<TEmpleado> retorno=null;
		try
		{
			daoEmpleado.altaEmpleado(empleado);
			retorno=daoEmpleado.listarEmpleados();
			assertTrue(retorno!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

}
