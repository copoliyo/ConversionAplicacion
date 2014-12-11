package general;

import consultasMantenimientos.ConManProveedor;
import util.Apariencia;

public class TestConManProveedores {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DatosComunes("MV");
		// false -> Consulta
		// true  -> Mantenimiento
		new ConManProveedor(false);
	}

}
