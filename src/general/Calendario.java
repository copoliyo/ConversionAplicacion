package general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.*;

public class Calendario {

	static final int MAX_COLUMNAS = 7;
	static final int MAX_FILAS = 7;

	JDialog pantallaCalendario;
	JLabel lMes, lAnio;
	JButton bUnBoton;
	JButton[][] bDia;
	JButton bIzquierdaAnio, bDerechaAnio, bIzquierdaMes, bDerechaMes;
	String nombreMes[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre" };
	int diaDeLaSemana, diasEnElMes;
	int mes = Calendar.getInstance().get(Calendar.MONTH);
	int anio = Calendar.getInstance().get(Calendar.YEAR);
	int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	private int fecha = 0;

	Calendario() {
		String[] cabecera = { "L", "M", "X", "J", "V", "S", "D" };

		bDia = new JButton[MAX_COLUMNAS][MAX_FILAS];

		pantallaCalendario = new JDialog();
		pantallaCalendario.setModal(true);
		pantallaCalendario.setSize(400, 400);
		pantallaCalendario
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// BorderLayour para TODO el calendario.
		BorderLayout border = new BorderLayout();
		pantallaCalendario.setLayout(border);

		// La parte superior del calendario va a estar subdividida en dos
		// paneles
		JPanel pSuperior = new JPanel();
		JPanel pSuperiorIzq = new JPanel();
		JPanel pSuperiorDer = new JPanel();

		pSuperiorIzq.setBackground(Color.WHITE);
		pSuperiorDer.setBackground(Color.WHITE);

		// La parte superior albergará otros dos paneles uno al lado del otro
		BoxLayout horizontal = new BoxLayout(pSuperior, BoxLayout.X_AXIS);
		pSuperior.setLayout(horizontal);

		// Cada uno de los paneles tendrá dos botones (EAST, WEST) y la
		// Etiqueta de año/mes (CENTER).
		BorderLayout borderSupIzq = new BorderLayout();
		BorderLayout borderSupDer = new BorderLayout();

		pSuperiorIzq.setLayout(borderSupIzq);
		pSuperiorDer.setLayout(borderSupDer);

		bIzquierdaAnio = new JButton("<<");
		bIzquierdaAnio.setBackground(Color.WHITE);
		bIzquierdaAnio.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));
		bIzquierdaAnio.addActionListener(new BotonCambiaFechaListener());
		bDerechaAnio = new JButton(">>");
		bDerechaAnio.setBackground(Color.WHITE);
		bDerechaAnio.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));
		bDerechaAnio.addActionListener(new BotonCambiaFechaListener());
		bIzquierdaMes = new JButton("<");
		bIzquierdaMes.setBackground(Color.WHITE);
		bIzquierdaMes.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));
		bIzquierdaMes.addActionListener(new BotonCambiaFechaListener());
		bDerechaMes = new JButton(">");
		bDerechaMes.setBackground(Color.WHITE);
		bDerechaMes.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));
		bDerechaMes.addActionListener(new BotonCambiaFechaListener());

		lMes = new JLabel(nombreMes[mes], SwingConstants.CENTER);
		lMes.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));
		lAnio = new JLabel(String.valueOf(anio), SwingConstants.CENTER);
		lAnio.setFont(new java.awt.Font("Terminal", Font.BOLD, 20));

		pSuperiorIzq.add(bIzquierdaAnio, BorderLayout.WEST);
		pSuperiorIzq.add(lAnio, BorderLayout.CENTER);
		pSuperiorIzq.add(bDerechaAnio, BorderLayout.EAST);

		pSuperiorDer.add(bIzquierdaMes, BorderLayout.WEST);
		pSuperiorDer.add(lMes, BorderLayout.CENTER);
		pSuperiorDer.add(bDerechaMes, BorderLayout.EAST);

		pSuperior.add(pSuperiorIzq, borderSupIzq);
		pSuperior.add(pSuperiorDer, borderSupDer);

		pantallaCalendario.add(pSuperior, BorderLayout.NORTH);

		// Creamos el GridLayout
		GridLayout matriz = new GridLayout(MAX_FILAS, MAX_COLUMNAS);
		JPanel pGrid = new JPanel();
		pGrid.setLayout(matriz);

		// Ahora tenemos que añadir los botones en un GridLayout
		for (int y = 0; y < MAX_FILAS; y++) {
			for (int x = 0; x < MAX_COLUMNAS; x++) {
				if (y == 0) {
					bDia[x][y] = new JButton(cabecera[x]);
					bDia[x][y].setBorderPainted(false);
					bDia[x][y].setFont(new java.awt.Font("Terminal", Font.BOLD,
							20));
				} else {
					bDia[x][y] = new JButton("");
					bDia[x][y].setFocusPainted(false);
					bDia[x][y].setBackground(Color.WHITE);
					bDia[x][y].addActionListener(new BotonPulsadoListener());
					bDia[x][y].setBorderPainted(false);
					bDia[x][y].setFont(new java.awt.Font("Terminal", Font.BOLD,
							20));
				}
				pGrid.add(bDia[x][y]);
			}
		}

		pantallaCalendario.add(pGrid, BorderLayout.CENTER);

		mostrarFecha();
		pantallaCalendario.setVisible(true);

	}

	class BotonPulsadoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getActionCommand().toString().length() > 0) {
				String fechaStr = String.format("%04d%02d%02d", anio, mes + 1,
						Integer.valueOf(arg0.getActionCommand().toString()));
				fecha = Integer.valueOf(fechaStr);
			} else {
				fecha = 0;
			}

			pantallaCalendario.dispose();
		}

	}

	class BotonCambiaFechaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String command = arg0.getActionCommand();
			if (command.equalsIgnoreCase("<<"))
				anio--;
			if (command.equalsIgnoreCase(">>"))
				anio++;
			if (command.equalsIgnoreCase(">")) {
				mes++;
				if (mes > 11)
					mes = 0;
			}
			if (command.equalsIgnoreCase("<")) {
				mes--;
				if (mes < 0)
					mes = 11;
			}

			mostrarFecha();
		}

	}

	public void mostrarFecha() {

		borrarCalendario();

		lMes.setText(nombreMes[mes]);
		lAnio.setText(String.valueOf(anio));
		// Dias de la semana: 1 D 2 L 3 M 4 X 5 J 6 V 7 S
		Calendar cal = java.util.Calendar.getInstance(Locale.UK);
		cal.set(anio, mes, 1);
		diaDeLaSemana = cal.get(java.util.Calendar.DAY_OF_WEEK) - 2;
		if (diaDeLaSemana < 0)
			diaDeLaSemana = 6;
		// Ahora dias de la semana: 0 L 1 M 2 X 3 J 4 V 5 S 6 D
		diasEnElMes = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

		int y = 1;
		int x = diaDeLaSemana;
		int textoDia = 1;

		while (y < MAX_FILAS && textoDia <= diasEnElMes) {
			while (x < MAX_COLUMNAS && textoDia <= diasEnElMes) {
				bDia[x][y].setText(String.valueOf(textoDia));
				if (dia == textoDia)
					bDia[x][y].setBackground(Color.RED);
				textoDia++;
				x++;
			}
			x = 0;
			y++;
		}
	}

	public void borrarCalendario() {
		for (int y = 1; y < MAX_FILAS; y++) {
			for (int x = 0; x < MAX_COLUMNAS; x++) {
				bDia[x][y].setText("");
				bDia[x][y].setFocusPainted(false);
				bDia[x][y].setBackground(Color.WHITE);
			}
		}
	}

	public int getFecha() {
		// Devolvemos un entero del tipo 20131214 o si hemos pinchado en casilla
		// vacia un 0
		return fecha;
	}
}
