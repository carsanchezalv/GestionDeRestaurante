package integracion.integracionFactura;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import integracion.integracionProducto.DAOProducto;
import integracion.integracionProducto.DAOProductoImpl;
import negocio.negocioFactura.TFactura;
import negocio.negocioProducto.TProducto;

public class DAOFacturaTest {

	@Test
	public void anadirProductoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=new TProducto("produ1",10.2,500,2,true);
		DAOFactura daoFactura = new DAOFacturaImpl();
		DAOProducto daoProducto= new DAOProductoImpl();
		TProducto retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			daoProducto.altaProducto(producto);
			retorno=daoFactura.anadirProducto(producto);
			assertTrue(retorno!=null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void aplicarDescuentoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(10.0,20, fechaActual ,2222,3333);
		DAOFactura daoFactura = new DAOFacturaImpl();
		Double retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			retorno=daoFactura.aplicarDescuento(factura);
			assertTrue(retorno!=-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void devolucionProductoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		TProducto producto=new TProducto("produ3",10.2,500,2,true);
		DAOFactura daoFactura = new DAOFacturaImpl();		
		DAOProducto daoProducto= new DAOProductoImpl();
		TProducto retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			daoProducto.altaProducto(producto);
			daoFactura.anadirProducto(producto);
			retorno=daoFactura.borrarProducto(producto);
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
		TProducto producto=new TProducto("produ4",10.2,500,2,true);
		DAOFactura daoFactura = new DAOFacturaImpl();		
		TProducto retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			retorno=daoFactura.borrarProducto(producto);
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
		DAOFactura daoFactura = new DAOFacturaImpl();
		TFactura retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			
			retorno=daoFactura.mostrarFactura(factura.getId());
			assertTrue(retorno!=null);
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mostrarFacturaNoExistenteFallo() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		DAOFactura daoFactura = new DAOFacturaImpl();
		TFactura retorno=null;
		try 
		{
			retorno=daoFactura.mostrarFactura(factura.getId());
			assertTrue(retorno==null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	@Test
	public void abrirFacturaNoExistenteExito() 
	{
		Date fechaActual = new Date();
		TFactura factura=new TFactura(0.0,20, fechaActual ,2222,3333);
		DAOFactura daoFactura = new DAOFacturaImpl();
		Integer retorno=null;
		try 
		{
			retorno=daoFactura.abrirFactura(factura);
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
		DAOFactura daoFactura = new DAOFacturaImpl();
		Integer retorno=null;
		try 
		{
			daoFactura.abrirFactura(factura);
			retorno=daoFactura.cerrarFactura(factura);
			assertTrue(retorno>-1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
}
