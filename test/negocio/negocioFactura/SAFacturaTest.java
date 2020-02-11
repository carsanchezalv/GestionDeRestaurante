package negocio.negocioFactura;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


import negocio.negocioProducto.SAProducto;
import negocio.negocioProducto.SAProductoImpl;
import negocio.negocioProducto.TProducto;


public class SAFacturaTest {
	
	@Test
	public void anadirProductoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=new TProducto("produc1",10.2,500,2,true);
		SAFactura saFactura = new SAFacturaImpl();
		SAProducto saProducto= new SAProductoImpl();
		TProducto retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			saProducto.altaProducto(producto);
			retorno=saFactura.anadirProducto(producto);
			assertTrue(retorno!=null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void anadirProductoNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=new TProducto("produc2",10.2,500,2,true);
		SAFactura saFactura = new SAFacturaImpl();
		TProducto retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			retorno=saFactura.anadirProducto(producto);
			assertTrue(retorno==null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void aplicarDescuentoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(10.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Double retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			retorno=saFactura.aplicarDescuento(factura);
			assertTrue(retorno>=0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void aplicarDescuentoNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(-10.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Double retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			retorno=saFactura.aplicarDescuento(factura);
			assertTrue(retorno==-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolucionProductoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=new TProducto("produc3",10.2,500,2,true);
		SAFactura saFactura = new SAFacturaImpl();
		SAProducto saProducto= new SAProductoImpl();
		TProducto retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			saProducto.altaProducto(producto);
			saFactura.anadirProducto(producto);
			retorno=saFactura.borrarProducto(producto);
			assertTrue(retorno!=null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolucionProductoNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=null;
		SAFactura saFactura = new SAFacturaImpl();
		TProducto retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			retorno=saFactura.borrarProducto(producto);
			assertTrue(retorno==null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarFacturaExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		TFactura retorno=null;
		Integer idFactura=null;
		try 
		{
			idFactura=saFactura.abrirFactura(factura);
			
			retorno=saFactura.mostrarFactura(idFactura);
			assertTrue(retorno!=null);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarFacturaNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(11111011,0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		TFactura retorno=null;
		try 
		{
			retorno=saFactura.mostrarFactura(factura.getId());
			assertTrue(retorno==null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void abrirFacturaExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno=null;
		Integer idFactura=null;
		try 
		{
			idFactura=saFactura.abrirFactura(factura);
			TFactura factura1=new TFactura(idFactura,0.0,20, fechaActual ,2222,3333);
			retorno=saFactura.abrirFactura(factura1);
			assertTrue(retorno<=-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void abrirFacturaNoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno=null;
		try 
		{
			retorno=saFactura.abrirFactura(factura);
			assertTrue(retorno>-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void cerrarFacturaExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno=null;
		try 
		{
			saFactura.abrirFactura(factura);
			retorno=saFactura.cerrarFactura(factura);
			assertTrue(retorno>-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void cerrarFacturaNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(998989841,0.0,20, fechaActual ,2222,3333);
		SAFactura saFactura = new SAFacturaImpl();
		Integer retorno=null;
		try 
		{
			retorno=saFactura.cerrarFactura(factura);
			assertTrue(retorno<=-1);	
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}


