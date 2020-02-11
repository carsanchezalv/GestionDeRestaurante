package negocio.negocioEmpleado;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;

import negocio.negocioEmpleado.SAEmpleado;
import negocio.negocioEmpleado.SAEmpleadoImpl;
import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.SATurno;
import negocio.negocioTurno.SATurnoImpl;
import negocio.negocioTurno.TTurno;



public class SAEmpleadoTest {
	
	@Test
	public void altaEmpleadoNoExistenteExito() 
	{
		TTurno turno=new TTurno("t1",1,7,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer idTurno = null;

		try 
		{
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado = new TEmpleado("Empleado1", "11", true, idTurno);
			retorno = saEmpleado.altaEmpleado(empleado);
			assertTrue(retorno >-1); //Si lo ha a�adido correctamente
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void altaEmpleadoExistenteFallo() 
	{
		TTurno turno=new TTurno("t2",1,7,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		try
		{
			Integer idTurno = null;
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado = new TEmpleado("Empleado2", "12", true, idTurno);
			TEmpleado empleado1 = new TEmpleado("Empleado2", "12", true, idTurno);
			saEmpleado.altaEmpleado(empleado);
			retorno = saEmpleado.altaEmpleado(empleado1);
			assertTrue(retorno <=-1); //Si lo ha añadido correctamente
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosDeTurnoExistenteExito()
	{
		TTurno turno=new TTurno ("tur7",1,2,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado=new SAEmpleadoImpl();
		LinkedList<TEmpleado> retorno=null;
		Integer idTurno=null;
		try {
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado= new TEmpleado("emp1","13",true,idTurno);
			saEmpleado.altaEmpleado(empleado);
			retorno=saEmpleado.listarEmpleadosPorTurno(idTurno);
			assertTrue(retorno!=null); //Devuelve true si no esta vacia la lista
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void listarEmpleadosDeTurnoNoExistenteFallo()
	{
		TEmpleado empleado= new TEmpleado("emp2","14",true,0065);
		TTurno turno=new TTurno (229,"tur8",1,2,true);
		SAEmpleado saEmpleado=new SAEmpleadoImpl();
		LinkedList<TEmpleado> retorno=null;
		try {
			saEmpleado.altaEmpleado(empleado);
			retorno=saEmpleado.listarEmpleadosPorTurno(turno.getId());
			assertTrue(retorno==null); //Devuelve true si esta vacia la lista
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void editarEmpleadoNoExistenteFallo() 
	{
		TEmpleado empleado = new TEmpleado(2121212120,"Empleado3", "15", true, 10000);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		try
		{
			retorno = saEmpleado.editarEmpleado(empleado);
			assertTrue(retorno!=0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editarEmpleadoExistenteExito() 
	{
		TTurno turno=new TTurno ("tur07",1,2,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer idTurno = null;
		try
		{
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado = new TEmpleado("Empleado4", "16", true, idTurno);
			saEmpleado.altaEmpleado(empleado);
			retorno = saEmpleado.editarEmpleado(empleado);
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
		TTurno turno=new TTurno ("tur09",1,2,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		Integer idTurno = null;
		Integer idEmpleado=null;
		try
		{
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado = new TEmpleado("Empleado5", "16", true, idTurno);
			idEmpleado =saEmpleado.altaEmpleado(empleado);
			retorno =saEmpleado.bajaEmpleado(idEmpleado);
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void borrarEmpleadoNoExistenteFallo() 
	{
		TEmpleado empleado = new TEmpleado(23232,"Empleado6", "18", true, 10000);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		Integer retorno = null;
		try
		{
			retorno =saEmpleado.bajaEmpleado(empleado.getId());
			assertTrue(retorno!=0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolverEmpleadoExistenteExito() 
	{
		TTurno turno=new TTurno ("tur008",1,2,true);
		SATurno saTurno=new SATurnoImpl();
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		TEmpleado retorno = null;
		Integer idTurno = null;
		Integer idEmpleado=null;
		try
		{
			idTurno=saTurno.altaTurno(turno);
			TEmpleado empleado = new TEmpleado("Empleado7", "16", true, idTurno);
			idEmpleado=saEmpleado.altaEmpleado(empleado);
			retorno=saEmpleado.devolverEmpleado(idEmpleado);
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
		TEmpleado empleado = new TEmpleado(3232364,"Empleado10", "20", true, 10000);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		TEmpleado retorno = null;
		try
		{
			retorno=saEmpleado.devolverEmpleado(empleado.getId());
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
		TEmpleado empleado = new TEmpleado("Empleado11", "21", true, 10000);
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		LinkedList<TEmpleado> listaEmpleados=null;
		try
		{
			saEmpleado.altaEmpleado(empleado);
			listaEmpleados=saEmpleado.listarEmpleados();
			assertTrue(listaEmpleados!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarEmpleadosNoExistentesFallo() 
	{
		SAEmpleado saEmpleado = new SAEmpleadoImpl();
		LinkedList<TEmpleado> retorno=null;
		try
		{	
			retorno=saEmpleado.listarEmpleados();
			assertTrue(retorno==null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}

