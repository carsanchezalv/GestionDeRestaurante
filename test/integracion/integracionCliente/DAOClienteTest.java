package integracion.integracionCliente;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;
import negocio.negocioCliente.TCliente;

public class DAOClienteTest {

	@Test
	public void altaClienteNoExistenteExito() 
	{
		TCliente cliente= new TCliente("cliente11");
		DAOCliente daoCliente=new DAOClienteImpl();
		Integer retorno=null;
		try
		{
			retorno=daoCliente.altaCliente(cliente);
			assertTrue(retorno>-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void editarClienteExistenteExito() 
	{
		TCliente cliente= new TCliente("cliente13");
		DAOCliente daoCliente=new DAOClienteImpl();
		Integer retorno=null;
		try
		{
			daoCliente.altaCliente(cliente);
			retorno=daoCliente.editarCliente(cliente);
			assertTrue(retorno>-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editarClienteNoExistenteFallo() 
	{
		TCliente cliente= new TCliente(14000007,"cliente14");
		DAOCliente daoCliente=new DAOClienteImpl();
		Integer retorno=null;
		try
		{
			retorno=daoCliente.editarCliente(cliente);
			assertTrue(retorno<=-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolverClienteExistenteExito() 
	{
		TCliente cliente= new TCliente("cliente15");
		DAOCliente daoCliente=new DAOClienteImpl();
		TCliente retorno=null;
		Integer idCliente=null;
		try
		{
			idCliente=daoCliente.altaCliente(cliente);
			retorno=daoCliente.devolverCliente(idCliente);
			assertTrue(retorno!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolverClienteNoExistenteFallo() 
	{
		TCliente cliente= new TCliente(9991,"cliente16");
		DAOCliente daoCliente=new DAOClienteImpl();
		TCliente retorno=null;
		try
		{
			retorno=daoCliente.devolverCliente(cliente.getId());
			assertTrue(retorno==null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarClientesExistentesExito() 
	{
		TCliente cliente= new TCliente("cliente17");
		DAOCliente daoCliente=new DAOClienteImpl();
		LinkedList<TCliente> retorno=null;
		try
		{
			daoCliente.altaCliente(cliente);
			retorno=daoCliente.listarClientes();
			assertTrue(retorno!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarClientesNoExistenteFallo() 
	{
		DAOCliente daoCliente=new DAOClienteImpl();
		LinkedList<TCliente> retorno=null;
		try
		{
			retorno=daoCliente.listarClientes();
			assertTrue(retorno==null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}