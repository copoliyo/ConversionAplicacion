package general;

public class TestIndiceMarcas {
	/**
	 * Utilizado para probar el lanzamiento del índice de marcas Se encargará de
	 * abrir la base de datos para poder mandarle como parámetro una 'Conexion'
	 * El índice de marcas deberá devolver una cadena con las tres letras de la
	 * marca
	 */

	static final int INDICE_CENTROS = 1;
	static final int INDICE_MARCAS = 2;
	static final int INDICE_GAMAS = 3;
	static final int INDICE_FAMILIAS = 4;

	public static void main(String[] args) {
		// 'marca' contendrá las letras la marca elegida o un NULL si no se
		// elije nada
		String marca = "";

		DatosComunes dc = new DatosComunes("MV");

		DatosComunes.eEmpresa = "PV";

		System.out.println("eEmpresa : '" + DatosComunes.eEmpresa + "'");

		IndiceCemagf im = new IndiceCemagf(INDICE_MARCAS);
		// Como ya estará establecido el valor de la marca, tendremos que
		// 'pedirlo'.
		marca = im.getMarca();
		System.out.println("Marca en Test: " + marca);

	}

}
