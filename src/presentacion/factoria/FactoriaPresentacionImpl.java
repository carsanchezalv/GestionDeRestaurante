package presentacion.factoria;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import presentacion.clienteGUI.ClienteGUIImpl;
import presentacion.empleadoGUI.EmpleadoGUIImpl;
import presentacion.facturaGUI.FacturaGUIImpl;
import presentacion.mainGUI.*;
import presentacion.productoGUI.ProductoGUIImpl;
import presentacion.turnoGUI.TurnoGUIImpl;

public class FactoriaPresentacionImpl extends FactoriaPresentacion {
	
	JPanel pathPanel;

	public MainGUIImpl generarMainGUI() {
		return new MainGUIImpl();
	}

	public TurnoGUIImpl generarTurnoGUI() {
		return new TurnoGUIImpl();
	}

	public ClienteGUIImpl generarClienteGUI() {
		return new ClienteGUIImpl();
	}

	public EmpleadoGUIImpl generarEmpleadoGUI() {
		return new EmpleadoGUIImpl();
	}

	public FacturaGUIImpl generarFacturaGUI() {
		return new FacturaGUIImpl();
	}

	public ProductoGUIImpl generarProductoGUI() {
		return new ProductoGUIImpl();
	}

	
	public JPanel generarPath() {
		if (pathPanel == null) {
			pathPanel = new JPanel();
			pathPanel.setLayout(new BoxLayout(pathPanel, BoxLayout.X_AXIS));
			pathPanel.setBackground(new Color(255, 255, 255));
			pathPanel.setMaximumSize(new Dimension(1024, 110));
		}
		return pathPanel;
	}

}