
package negocio.negocioCliente;


import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import negocio.negocioCliente.SACliente;
import negocio.negocioCliente.SAClienteImpl;
import negocio.negocioCliente.TCliente;

public class SAClienteTest {
	
	@Test
	public void altaClienteNoExistenteExito() 
	{
		TCliente cliente= new TCliente("cliente1");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno=null;
		try
		{
			retorno=saCliente.altaCliente(cliente);
			assertTrue(retorno>-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	} 
	
	@Test
	public void altaClienteExistenteFallo() 
	{
		TCliente cliente= new TCliente("cliente2");
		TCliente cliente1= new TCliente("cliente2");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno=null;
		try
		{
			saCliente.altaCliente(cliente);
			retorno=saCliente.altaCliente(cliente1);
			assertTrue(retorno<=-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editarClienteExistenteExito() 
	{
		TCliente cliente= new TCliente("cliente3");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno=null;
		try
		{
			saCliente.altaCliente(cliente);
			retorno=saCliente.editarCliente(cliente);
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
		TCliente cliente= new TCliente(91919191,"cliente4");
		SACliente saCliente = new SAClienteImpl();
		Integer retorno=null;
		try
		{
			retorno=saCliente.editarCliente(cliente);
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
		TCliente cliente= new TCliente("cliente5");
		SACliente saCliente = new SAClienteImpl();
		TCliente retorno=null;
		Integer idCliente=null;
		try
		{
			idCliente=saCliente.altaCliente(cliente);
			retorno=saCliente.devolverCliente(idCliente);
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
		TCliente cliente= new TCliente(77777,"cliente6");
		SACliente saCliente = new SAClienteImpl();
		TCliente retorno=null;
		try
		{
			
			retorno=saCliente.devolverCliente(cliente.getId());
			assertTrue(cliente!=retorno);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void listarClientesExistentesExito() 
	{
		TCliente cliente= new TCliente("cliente7");
		SACliente saCliente = new SAClienteImpl();
		LinkedList<TCliente> retorno=null;
		try
		{
			saCliente.altaCliente(cliente);
			retorno=saCliente.listarClientes();
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
		SACliente saCliente = new SAClienteImpl();
		LinkedList<TCliente> retorno=null;
		try
		{
			retorno=saCliente.listarClientes();
			assertTrue(retorno==null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}