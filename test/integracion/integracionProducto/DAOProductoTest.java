package integracion.integracionProducto;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import negocio.negocioProducto.TProducto;

public class DAOProductoTest {

	@Test
	public void altaProductoNoExistenteExito() 
	{
		TProducto producto=new TProducto("product0",10.2,500,1,true);
		DAOProducto daoProducto=new DAOProductoImpl();
		Integer retorno=null;
		try
		{
			retorno=daoProducto.altaProducto(producto);
			assertTrue(retorno>-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	
	
	
	@Test
	public void editarProductoExistenteExito() 
	{
		TProducto producto=new TProducto("product3",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		Integer retorno=null;
		try
		{
			daoProducto.altaProducto(producto);
			retorno=daoProducto.editarProducto(producto);
			assertTrue(retorno!=-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void editarProductoNoExistenteFallo() 
	{
		TProducto producto=new TProducto("product4",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		Integer retorno=null;
		try
		{
			retorno=daoProducto.editarProducto(producto);
			assertTrue(retorno==-1);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	@Test
	public void bajaProductoExistenteExito() 
	{
		TProducto producto=new TProducto("product5",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		Integer retorno=null;
		Integer idProducto=null;
		try
		{
			daoProducto.altaProducto(producto);
			retorno=daoProducto.bajaProducto(idProducto);
			assertTrue(retorno==0);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	

	@Test
	public void devolverProductoExistenteExito() 
	{
		TProducto producto=new TProducto("product7",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		TProducto retorno=null;
		Integer idProducto=null;
		try
		{
			idProducto=daoProducto.altaProducto(producto);
			retorno=daoProducto.devolverProducto(idProducto);
			assertTrue(retorno!=null);

		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void devolverProductoNoExistenteFallo() 
	{
		TProducto producto=new TProducto(222251,"product8",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		TProducto retorno=null;
		try
		{
			retorno=daoProducto.devolverProducto(producto.getId());
			assertNotEquals(retorno,producto);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void listarProductosExistenteExito() 
	{
		TProducto producto=new TProducto("product9",10.2,500,1,true); 
		DAOProducto daoProducto=new DAOProductoImpl();
		LinkedList<TProducto> retorno=null;
		try
		{
			daoProducto.altaProducto(producto);
			retorno=daoProducto.listarProductos();
			assertTrue(retorno!=null);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
		
	}
	
	

}
