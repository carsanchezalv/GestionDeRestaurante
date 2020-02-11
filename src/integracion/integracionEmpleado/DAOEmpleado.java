package integracion.integracionEmpleado;

import java.util.LinkedList;

import negocio.negocioEmpleado.TEmpleado;
import negocio.negocioTurno.TTurno;

public interface DAOEmpleado {
	public abstract Integer altaEmpleado(TEmpleado empleado) throws Exception;
	public abstract Integer editarEmpleado(TEmpleado empleado) throws Exception;
	public abstract Integer bajaEmpleado(Integer id) throws Exception;
	public abstract TEmpleado devolverEmpleado(Integer id) throws Exception;
	public abstract LinkedList<TEmpleado> listarEmpleados() throws Exception;
	public abstract TEmpleado readByDNI(String dni) throws Exception;
	public abstract TTurno readByIdTurno(Integer id) throws Exception;
	public abstract LinkedList<TEmpleado> listarEmpleadosPorTurno(Integer idTurno) throws Exception;
}