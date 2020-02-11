package negocio.negocioEmpleado;

import java.util.LinkedList;

public interface SAEmpleado {
	public abstract Integer altaEmpleado(TEmpleado empleado) throws Exception;
	public abstract Integer editarEmpleado(TEmpleado empleado) throws Exception;
	public abstract Integer bajaEmpleado(Integer id) throws Exception;
	public abstract TEmpleado devolverEmpleado(Integer id) throws Exception;
	public abstract LinkedList<TEmpleado> listarEmpleados() throws Exception;
	public abstract LinkedList<TEmpleado> listarEmpleadosPorTurno(Integer idTurno) throws Exception;
}