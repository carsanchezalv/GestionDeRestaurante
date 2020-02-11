package negocio.negocioProducto;
import static org.junit.Assert.*;


import java.util.LinkedList;

import org.junit.Test;

import negocio.negocioProducto.SAProducto;
import negocio.negocioProducto.SAProductoImpl;
import negocio.negocioProducto.TProducto;






	public class SAProductoTest {

		
		@Test
		public void altaProductoNoExistenteExito() 
		{
			 TProducto producto=new TProducto("producto0",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			try	{
				retorno=saProducto.altaProducto(producto);
				assertTrue(retorno>-1);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void altaProductoExistenteFallo() 
		{
			 TProducto producto=new TProducto("producto1",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			 Integer idProducto=null;
			try	{
				idProducto=saProducto.altaProducto(producto);
				TProducto producto1=new TProducto(idProducto,"producto1",10.2,500,1,true);
				retorno=saProducto.altaProducto(producto1);
				assertTrue(retorno==-1);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		
		
		@Test
		public void editarProductoExistenteExito() //otro prod
		{
			TProducto producto=new TProducto("producto2",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			try
			{
				saProducto.altaProducto(producto);
				retorno=saProducto.editarProducto(producto);
				assertTrue(retorno==0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void editarProductoNoExistenteFallo() 
		{
			TProducto producto=new TProducto("producto3",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			try
			{
				retorno=saProducto.editarProducto(producto);
				assertTrue(retorno!=0);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		@Test
		public void bajaProductoExistenteExito() 
		{
			TProducto producto=new TProducto("producto4",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			 Integer idProducto=null;
			try
			{
				idProducto=saProducto.altaProducto(producto);
				retorno=saProducto.bajaProducto(idProducto);
				assertTrue(retorno>-1);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void bajaProductoNoExistenteFallo() 
		{
			TProducto producto=new TProducto(54545100,"producto5",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 Integer retorno=null;
			try
			{
				retorno=saProducto.bajaProducto(producto.getId());
				assertTrue(retorno==-1);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void devolverProductoExistenteExito() 
		{
			TProducto producto=new TProducto("producto6",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 TProducto retorno=null;
			 Integer idProducto=null;
			try
			{
				idProducto=saProducto.altaProducto(producto);
				retorno=saProducto.devolverProducto(idProducto);
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
			TProducto producto=new TProducto(221515,"producto7",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 TProducto retorno=null;
			try
			{
				retorno=saProducto.devolverProducto(producto.getId());
				assertTrue(producto!=retorno);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void listarProductosExistenteExito() 
		{
			TProducto producto=new TProducto("producto8",10.2,500,1,true);
			 SAProducto saProducto=new SAProductoImpl();
			 LinkedList<TProducto> retorno=null;
			try
			{
				saProducto.altaProducto(producto);
				retorno=saProducto.listarProductos();
				assertTrue(retorno!=null);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
		@Test
		public void listarProductosNoExistenteFallo() 
		{
			 SAProducto saProducto=new SAProductoImpl();
			 LinkedList<TProducto> retorno=null;
			try
			{
				retorno=saProducto.listarProductos();
				assertTrue(retorno==null);
			}
			catch(Exception e)
			{
				fail(e.getMessage());
			}
			
		}
		
}

