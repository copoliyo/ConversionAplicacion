package tablas;

import general.DatosComunes;
import general.MysqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import util.Cadena;


public class Usuario {
	private String empresa;
	private String usuario;
	private int usuarioNumero;
	private String nombre;
	private int activo;
	private String nickAlias;
	private String password;
	private int fechaPassword;
	private String anteriorPassword[];
	private String menu;
	private String grupo;
	private int nivelContable;
	private int nivelGestion;
	private int nivelLogistica;
	private String telefonoAgenda;
	private String telefono;
	private String localizacion;
	private String correo;
	private int centroContable;
	private int centroGestion;
	private String ultimoPrograma;
	private int ultimaFecha;
	private int horaInicio;
	private int horaFin;
	private int intentosFallo;
	private String ttyPermitido[];
	private int intro;
	private int adelante;
	private int atras;
	private int acepto;
	private int rechazo;
	private int avancePagina;
	private int retrocesoPagina;
	private int derecha;
	private int izquierda;
	private int ayuda;
	private String literalIntro;
	private String literalAdelante;
	private String literalAtras;
	private String literalAcepto;
	private String literalRechazo;
	private String literalAvancePagina;
	private String literalRetrocesoPagina;
	private String literalDerecha;
	private String literalIzquierda;
	private String literalAyuda;
	private int idioma;
	private int lineasPapel;
	private int anchoPapel;
	private int impresNormal;
	private int impresComprimido;
	private int impresExpandido;
	private int impresDoceavos;
	private int impresCalidad;
	private int impresNegrita;
	private int impresDobleAltura;
	private int impresSubrayado;
	private int tipo;
	private int codigoCuenta;
	private int permiteTexto;
	private int pantalla;

	public Usuario(){
		empresa = DatosComunes.eEmpresa;
		usuario = "";
		usuarioNumero = 0;
		nombre = "";
		activo = 0;
		nickAlias = "";
		password = "";
		fechaPassword = 0;

		anteriorPassword = new String[5];
		for(int i = 0; i < 5; i++)
			anteriorPassword[i] = "";
		menu = "";
		grupo = "";
		nivelContable = 9;
		nivelGestion = 9;
		nivelLogistica = 9;
		telefonoAgenda = "";
		telefono = "";
		localizacion = "";
		correo = "";
		centroContable = 0;
		centroGestion = 0;
		ultimoPrograma = "";
		ultimaFecha = 0;
		horaInicio = 0;
		horaFin = 0;
		intentosFallo = 0;

		ttyPermitido = new String[10];
		for(int i = 0; i < 10; i++)
			ttyPermitido[i] = "";
		intro = 0;
		adelante = 0;
		atras = 0;
		acepto = 0;
		rechazo = 0;
		avancePagina = 0;
		retrocesoPagina = 0;
		derecha = 0;
		izquierda = 0;
		ayuda = 0;
		literalIntro = "";
		literalAdelante = "";
		literalAtras = "";
		literalAcepto = "";
		literalRechazo = "";
		literalAvancePagina = "";
		literalRetrocesoPagina = "";
		literalDerecha = "";
		literalIzquierda = "";
		literalAyuda = "";
		idioma = 0;
		lineasPapel = 0;
		anchoPapel = 0;
		impresNormal = 0;
		impresComprimido = 0;
		impresExpandido = 0;
		impresDoceavos = 0;
		impresCalidad = 0;
		impresNegrita = 0;
		impresDobleAltura = 0;
		impresSubrayado = 0;
		tipo = 0;
		codigoCuenta = 0;
		permiteTexto = 0;
		pantalla = 0;		
	}
	public Usuario(ResultSet rs){
		try{
			if(rs.next() == true){				
				empresa = rs.getString("EMPRESA").trim();
				usuario = rs.getString("ACCUSU_USUARIO").trim();
				usuarioNumero = rs.getInt("ACCUSU_USUARION");
				nombre = rs.getString("ACCUSU_NOMBRE").trim();
				activo = rs.getInt("ACCUSU_ACTIVO");
				nickAlias = rs.getString("ACCUSU_NIK_ALIAS").trim();
				password = rs.getString("ACCUSU_PASSWORD").trim();
				fechaPassword = rs.getInt("ACCUSU_FECH_PASSW");	
				anteriorPassword = new String[5];
				anteriorPassword[0] = rs.getString("ACCUSU_ANT_PASSWD_1").trim();
				anteriorPassword[1] = rs.getString("ACCUSU_ANT_PASSWD_2").trim();
				anteriorPassword[2] = rs.getString("ACCUSU_ANT_PASSWD_3").trim();
				anteriorPassword[3] = rs.getString("ACCUSU_ANT_PASSWD_4").trim();
				anteriorPassword[4] = rs.getString("ACCUSU_ANT_PASSWD_5").trim();
				menu = rs.getString("ACCUSU_MENU").trim();
				grupo = rs.getString("ACCUSU_GRUPO").trim();
				nivelContable = rs.getInt("ACCUSU_NIVEL_ACCONT");
				nivelGestion = rs.getInt("ACCUSU_NIVEL_ACGEST");
				nivelLogistica = rs.getInt("ACCUSU_NIVEL_LOGIST");
				telefonoAgenda = rs.getString("ACCUSU_TLFNO_AGEND").trim();
				telefono = rs.getString("ACCUSU_TLFNO").trim();
				localizacion = rs.getString("ACCUSU_LOCALIZACION").trim();
				correo = rs.getString("ACCUSU_CORREO").trim();
				centroContable = rs.getInt("ACCUSU_CENTRO_CONT");
				centroGestion = rs.getInt("ACCUSU_CENTRO_GEST");
				ultimoPrograma = rs.getString("ACCUSU_ULT_PROGRAMA").trim();
				ultimaFecha = rs.getInt("ACCUSU_ULT_FECHA");
				horaInicio = rs.getInt("ACCUSU_HORA_INICIO");
				horaFin = rs.getInt("ACCUSU_HORA_FIN");
				intentosFallo = rs.getInt("ACCUSU_INTENTOS_FALLO");
				ttyPermitido = new String[10];
				ttyPermitido[0] = rs.getString("ACCUSU_TTY_PERMIT_1").trim();
				ttyPermitido[1] = rs.getString("ACCUSU_TTY_PERMIT_2").trim();
				ttyPermitido[2] = rs.getString("ACCUSU_TTY_PERMIT_3").trim();
				ttyPermitido[3] = rs.getString("ACCUSU_TTY_PERMIT_4").trim();
				ttyPermitido[4] = rs.getString("ACCUSU_TTY_PERMIT_5").trim();
				ttyPermitido[5] = rs.getString("ACCUSU_TTY_PERMIT_6").trim();
				ttyPermitido[6] = rs.getString("ACCUSU_TTY_PERMIT_7").trim();
				ttyPermitido[7] = rs.getString("ACCUSU_TTY_PERMIT_8").trim();
				ttyPermitido[8] = rs.getString("ACCUSU_TTY_PERMIT_9").trim();
				ttyPermitido[9] = rs.getString("ACCUSU_TTY_PERMIT_10").trim();				
				intro = rs.getInt("ACCUSU_INTRO");
				adelante = rs.getInt("ACCUSU_ALANT");
				atras = rs.getInt("ACCUSU_ATRAS");
				acepto = rs.getInt("ACCUSU_ACEPT");
				rechazo = rs.getInt("ACCUSU_RCHAZ");
				avancePagina = rs.getInt("ACCUSU_AVPAG");
				retrocesoPagina = rs.getInt("ACCUSU_REPAG");
				derecha = rs.getInt("ACCUSU_DRCHA");
				izquierda = rs.getInt("ACCUSU_IZQDA");
				ayuda = rs.getInt("ACCUSU_AYUDA");
				literalIntro = rs.getString("ACCUSU_LIT_INTRO").trim();
				literalAdelante = rs.getString("ACCUSU_LIT_ALANT").trim();
				literalAtras = rs.getString("ACCUSU_LIT_ATRAS").trim();
				literalAcepto = rs.getString("ACCUSU_LIT_ACEPT").trim();
				literalRechazo = rs.getString("ACCUSU_LIT_RCHAZ").trim();
				literalAvancePagina = rs.getString("ACCUSU_LIT_AVPAG").trim();
				literalRetrocesoPagina = rs.getString("ACCUSU_LIT_REPAG").trim();
				literalDerecha = rs.getString("ACCUSU_LIT_DRCHA").trim();
				literalIzquierda = rs.getString("ACCUSU_LIT_IZQDA").trim();
				literalAyuda = rs.getString("ACCUSU_LIT_AYUDA").trim();
				idioma = rs.getInt("ACCUSU_IDIOMA");
				lineasPapel = rs.getInt("ACCUSU_LINEAS_PAPEL");
				anchoPapel = rs.getInt("ACCUSU_ANCHO_PAPEL");
				impresNormal = rs.getInt("ACCUSU_IMPRES_NORMAL");
				impresComprimido = rs.getInt("ACCUSU_IMPRES_COMPRIM");
				impresExpandido = rs.getInt("ACCUSU_IMPRES_EXPAND");
				impresDoceavos = rs.getInt("ACCUSU_IMPRES_DOCEAVOS");
				impresCalidad = rs.getInt("ACCUSU_IMPRES_CALIDAD");
				impresNegrita = rs.getInt("ACCUSU_IMPRES_NEGRITA");
				impresDobleAltura = rs.getInt("ACCUSU_IMPRES_DOBLALTU");
				impresSubrayado = rs.getInt("ACCUSU_IMPRES_SUBRAYA");
				tipo = rs.getInt("ACCUSU_TIPO");
				codigoCuenta = rs.getInt("ACCUSU_CODIGO_CUENTA");
				permiteTexto = rs.getInt("ACCUSU_PERMIT_TEXTO");
				pantalla = rs.getInt("ACCUSU_PANTALLA");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Usuario!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void read(ResultSet rs){
		try{
			empresa = rs.getString("EMPRESA").trim();
			usuario = rs.getString("ACCUSU_USUARIO").trim();
			usuarioNumero = rs.getInt("ACCUSU_USUARION");
			nombre = rs.getString("ACCUSU_NOMBRE").trim();
			activo = rs.getInt("ACCUSU_ACTIVO");
			nickAlias = rs.getString("ACCUSU_NIK_ALIAS").trim();
			password = rs.getString("ACCUSU_PASSWORD").trim();
			fechaPassword = rs.getInt("ACCUSU_FECH_PASSW");							
			anteriorPassword[0] = rs.getString("ACCUSU_ANT_PASSWD_1").trim();
			anteriorPassword[1] = rs.getString("ACCUSU_ANT_PASSWD_2").trim();
			anteriorPassword[2] = rs.getString("ACCUSU_ANT_PASSWD_3").trim();
			anteriorPassword[3] = rs.getString("ACCUSU_ANT_PASSWD_4").trim();
			anteriorPassword[4] = rs.getString("ACCUSU_ANT_PASSWD_5").trim();
			menu = rs.getString("ACCUSU_MENU").trim();
			grupo = rs.getString("ACCUSU_GRUPO").trim();
			nivelContable = rs.getInt("ACCUSU_NIVEL_ACCONT");
			nivelGestion = rs.getInt("ACCUSU_NIVEL_ACGEST");
			nivelLogistica = rs.getInt("ACCUSU_NIVEL_LOGIST");
			telefonoAgenda = rs.getString("ACCUSU_TLFNO_AGEND").trim();
			telefono = rs.getString("ACCUSU_TLFNO").trim();
			localizacion = rs.getString("ACCUSU_LOCALIZACION").trim();
			correo = rs.getString("ACCUSU_CORREO").trim();
			centroContable = rs.getInt("ACCUSU_CENTRO_CONT");
			centroGestion = rs.getInt("ACCUSU_CENTRO_GEST");
			ultimoPrograma = rs.getString("ACCUSU_ULT_PROGRAMA").trim();
			ultimaFecha = rs.getInt("ACCUSU_ULT_FECHA");
			horaInicio = rs.getInt("ACCUSU_HORA_INICIO");
			horaFin = rs.getInt("ACCUSU_HORA_FIN");
			intentosFallo = rs.getInt("ACCUSU_INTENTOS_FALLO");
			ttyPermitido[0] = rs.getString("ACCUSU_TTY_PERMIT_1").trim();
			ttyPermitido[1] = rs.getString("ACCUSU_TTY_PERMIT_2").trim();
			ttyPermitido[2] = rs.getString("ACCUSU_TTY_PERMIT_3").trim();
			ttyPermitido[3] = rs.getString("ACCUSU_TTY_PERMIT_4").trim();
			ttyPermitido[4] = rs.getString("ACCUSU_TTY_PERMIT_5").trim();
			ttyPermitido[5] = rs.getString("ACCUSU_TTY_PERMIT_6").trim();
			ttyPermitido[6] = rs.getString("ACCUSU_TTY_PERMIT_7").trim();
			ttyPermitido[7] = rs.getString("ACCUSU_TTY_PERMIT_8").trim();
			ttyPermitido[8] = rs.getString("ACCUSU_TTY_PERMIT_9").trim();
			ttyPermitido[9] = rs.getString("ACCUSU_TTY_PERMIT_10").trim();				
			intro = rs.getInt("ACCUSU_INTRO");
			adelante = rs.getInt("ACCUSU_ALANT");
			atras = rs.getInt("ACCUSU_ATRAS");
			acepto = rs.getInt("ACCUSU_ACEPT");
			rechazo = rs.getInt("ACCUSU_RCHAZ");
			avancePagina = rs.getInt("ACCUSU_AVPAG");
			retrocesoPagina = rs.getInt("ACCUSU_REPAG");
			derecha = rs.getInt("ACCUSU_DRCHA");
			izquierda = rs.getInt("ACCUSU_IZQDA");
			ayuda = rs.getInt("ACCUSU_AYUDA");
			literalIntro = rs.getString("ACCUSU_LIT_INTRO").trim();
			literalAdelante = rs.getString("ACCUSU_LIT_ALANT").trim();
			literalAtras = rs.getString("ACCUSU_LIT_ATRAS").trim();
			literalAcepto = rs.getString("ACCUSU_LIT_ACEPT").trim();
			literalRechazo = rs.getString("ACCUSU_LIT_RCHAZ").trim();
			literalAvancePagina = rs.getString("ACCUSU_LIT_AVPAG").trim();
			literalRetrocesoPagina = rs.getString("ACCUSU_LIT_REPAG").trim();
			literalDerecha = rs.getString("ACCUSU_LIT_DRCHA").trim();
			literalIzquierda = rs.getString("ACCUSU_LIT_IZQDA").trim();
			literalAyuda = rs.getString("ACCUSU_LIT_AYUDA").trim();
			idioma = rs.getInt("ACCUSU_IDIOMA");
			lineasPapel = rs.getInt("ACCUSU_LINEAS_PAPEL");
			anchoPapel = rs.getInt("ACCUSU_ANCHO_PAPEL");
			impresNormal = rs.getInt("ACCUSU_IMPRES_NORMAL");
			impresComprimido = rs.getInt("ACCUSU_IMPRES_COMPRIM");
			impresExpandido = rs.getInt("ACCUSU_IMPRES_EXPAND");
			impresDoceavos = rs.getInt("ACCUSU_IMPRES_DOCEAVOS");
			impresCalidad = rs.getInt("ACCUSU_IMPRES_CALIDAD");
			impresNegrita = rs.getInt("ACCUSU_IMPRES_NEGRITA");
			impresDobleAltura = rs.getInt("ACCUSU_IMPRES_DOBLALTU");
			impresSubrayado = rs.getInt("ACCUSU_IMPRES_SUBRAYA");
			tipo = rs.getInt("ACCUSU_TIPO");
			codigoCuenta = rs.getInt("ACCUSU_CODIGO_CUENTA");
			permiteTexto = rs.getInt("ACCUSU_PERMIT_TEXTO");
			pantalla = rs.getInt("ACCUSU_PANTALLA");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
			"Error en lectura fichero de Usuario!!!");
			if(DatosComunes.enDebug)
				e.printStackTrace();
		}
	}

	public void write(){
		PreparedStatement ps = null;
   
		String sqlInsert = "INSERT INTO ACCUSU " +
						   "(EMPRESA, " +
						   "ACCUSU_USUARIO, " +
						   "ACCUSU_USUARION, " +
						   "ACCUSU_NOMBRE, " +
						   "ACCUSU_ACTIVO, " +
						   "ACCUSU_NIK_ALIAS, " +
						   "ACCUSU_PASSWORD, " +
						   "ACCUSU_FECH_PASSW, " +
						   "ACCUSU_ANT_PASSWD_1, " +
						   "ACCUSU_ANT_PASSWD_2, " +
						   "ACCUSU_ANT_PASSWD_3, " +
						   "ACCUSU_ANT_PASSWD_4, " +
						   "ACCUSU_ANT_PASSWD_5, " +
						   "ACCUSU_MENU, " +
						   "ACCUSU_GRUPO, " +
						   "ACCUSU_NIVEL_ACCONT, " +
						   "ACCUSU_NIVEL_ACGEST, " +
						   "ACCUSU_TLFNO_AGEND, " +
						   "ACCUSU_TLFNO, " +
						   "ACCUSU_LOCALIZACION, " +
						   "ACCUSU_CORREO, " +
						   "ACCUSU_CENTRO_CONT, " +
						   "ACCUSU_CENTRO_GEST, " +
						   "ACCUSU_ULT_PROGRAMA, " +
						   "ACCUSU_ULT_FECHA, " +
						   "ACCUSU_HORA_INICIO, " +
						   "ACCUSU_HORA_FIN, " +
						   "ACCUSU_INTENTOS_FALLO, " +
						   "ACCUSU_TTY_PERMIT_1, " +
						   "ACCUSU_TTY_PERMIT_2, " +
						   "ACCUSU_TTY_PERMIT_3, " +
						   "ACCUSU_TTY_PERMIT_4, " +
						   "ACCUSU_TTY_PERMIT_5, " +
						   "ACCUSU_TTY_PERMIT_6, " +
						   "ACCUSU_TTY_PERMIT_7, " +
						   "ACCUSU_TTY_PERMIT_8, " +
						   "ACCUSU_TTY_PERMIT_9, " +
						   "ACCUSU_TTY_PERMIT_10, " +
						   "ACCUSU_INTRO, " +
						   "ACCUSU_ALANT, " +
						   "ACCUSU_ATRAS, " +
						   "ACCUSU_ACEPT, " +
						   "ACCUSU_RCHAZ, " +
						   "ACCUSU_AVPAG, " +
						   "ACCUSU_REPAG, " +
						   "ACCUSU_DRCHA, " +
						   "ACCUSU_IZQDA, " +
						   "ACCUSU_AYUDA, " +
						   "ACCUSU_LIT_INTRO, " +
						   "ACCUSU_LIT_ALANT, " +
						   "ACCUSU_LIT_ATRAS, " +
						   "ACCUSU_LIT_ACEPT, " +
						   "ACCUSU_LIT_RCHAZ, " +
						   "ACCUSU_LIT_AVPAG, " +
						   "ACCUSU_LIT_REPAG, " +
						   "ACCUSU_LIT_DRCHA, " +
						   "ACCUSU_LIT_IZQDA, " +
						   "ACCUSU_LIT_AYUDA, " +
						   "ACCUSU_IDIOMA, " +
						   "ACCUSU_LINEAS_PAPEL, " +
						   "ACCUSU_ANCHO_PAPEL, " +
						   "ACCUSU_IMPRES_NORMAL, " +
						   "ACCUSU_IMPRES_COMPRIM, " +
						   "ACCUSU_IMPRES_EXPAND, " +
						   "ACCUSU_IMPRES_DOCEAVOS, " +
						   "ACCUSU_IMPRES_CALIDAD, " +
						   "ACCUSU_IMPRES_NEGRITA, " +
						   "ACCUSU_IMPRES_DOBLALTU, " +
						   "ACCUSU_IMPRES_SUBRAYA, " +
						   "ACCUSU_TIPO, " +
						   "ACCUSU_CODIGO_CUENTA, " +
						   "ACCUSU_PERMIT_TEXTO, " +
						   "ACCUSU_PANTALLA, " +
						   "ACCUSU_NIVEL_LOGIST) " +
				           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				                   "?, ?, ?, ? )" +
				           "ON DUPLICATE KEY UPDATE " +
				           "EMPRESA = ?, " +
				           "ACCUSU_USUARIO = ?, " +
						   "ACCUSU_USUARION = ?, " +
						   "ACCUSU_NOMBRE = ?, " +
						   "ACCUSU_ACTIVO = ?, " +
						   "ACCUSU_NIK_ALIAS = ?, " +
						   "ACCUSU_PASSWORD = ?, " +
						   "ACCUSU_FECH_PASSW = ?, " +
						   "ACCUSU_ANT_PASSWD_1 = ?, " +
						   "ACCUSU_ANT_PASSWD_2 = ?, " +
						   "ACCUSU_ANT_PASSWD_3 = ?, " +
						   "ACCUSU_ANT_PASSWD_4 = ?, " +
						   "ACCUSU_ANT_PASSWD_5 = ?, " +
						   "ACCUSU_MENU = ?, " +
						   "ACCUSU_GRUPO = ?, " +
						   "ACCUSU_NIVEL_ACCONT = ?, " +
						   "ACCUSU_NIVEL_ACGEST = ?, " +
						   "ACCUSU_TLFNO_AGEND = ?, " +
						   "ACCUSU_TLFNO = ?, " +
						   "ACCUSU_LOCALIZACION = ?, " +
						   "ACCUSU_CORREO = ?, " +
						   "ACCUSU_CENTRO_CONT = ?, " +
						   "ACCUSU_CENTRO_GEST = ?, " +
						   "ACCUSU_ULT_PROGRAMA = ?, " +
						   "ACCUSU_ULT_FECHA = ?, " +
						   "ACCUSU_HORA_INICIO = ?, " +
						   "ACCUSU_HORA_FIN = ?, " +
						   "ACCUSU_INTENTOS_FALLO = ?, " +
						   "ACCUSU_TTY_PERMIT_1 = ?, " +
						   "ACCUSU_TTY_PERMIT_2 = ?, " +
						   "ACCUSU_TTY_PERMIT_3 = ?, " +
						   "ACCUSU_TTY_PERMIT_4 = ?, " +
						   "ACCUSU_TTY_PERMIT_5 = ?, " +
						   "ACCUSU_TTY_PERMIT_6 = ?, " +
						   "ACCUSU_TTY_PERMIT_7 = ?, " +
						   "ACCUSU_TTY_PERMIT_8 = ?, " +
						   "ACCUSU_TTY_PERMIT_9 = ?, " +
						   "ACCUSU_TTY_PERMIT_10 = ?, " +
						   "ACCUSU_INTRO = ?, " +
						   "ACCUSU_ALANT = ?, " +
						   "ACCUSU_ATRAS = ?, " +
						   "ACCUSU_ACEPT = ?, " +
						   "ACCUSU_RCHAZ = ?, " +
						   "ACCUSU_AVPAG = ?, " +
						   "ACCUSU_REPAG = ?, " +
						   "ACCUSU_DRCHA = ?, " +
						   "ACCUSU_IZQDA = ?, " +
						   "ACCUSU_AYUDA = ?, " +
						   "ACCUSU_LIT_INTRO = ?, " +
						   "ACCUSU_LIT_ALANT = ?, " +
						   "ACCUSU_LIT_ATRAS = ?, " +
						   "ACCUSU_LIT_ACEPT = ?, " +
						   "ACCUSU_LIT_RCHAZ = ?, " +
						   "ACCUSU_LIT_AVPAG = ?, " +
						   "ACCUSU_LIT_REPAG = ?, " +
						   "ACCUSU_LIT_DRCHA = ?, " +
						   "ACCUSU_LIT_IZQDA = ?, " +
						   "ACCUSU_LIT_AYUDA = ?, " +
						   "ACCUSU_IDIOMA = ?, " +
						   "ACCUSU_LINEAS_PAPEL = ?, " +
						   "ACCUSU_ANCHO_PAPEL = ?, " +
						   "ACCUSU_IMPRES_NORMAL = ?, " +
						   "ACCUSU_IMPRES_COMPRIM = ?, " +
						   "ACCUSU_IMPRES_EXPAND = ?, " +
						   "ACCUSU_IMPRES_DOCEAVOS = ?, " +
						   "ACCUSU_IMPRES_CALIDAD = ?, " +
						   "ACCUSU_IMPRES_NEGRITA = ?, " +
						   "ACCUSU_IMPRES_DOBLALTU = ?, " +
						   "ACCUSU_IMPRES_SUBRAYA = ?, " +
						   "ACCUSU_TIPO = ?, " +
						   "ACCUSU_CODIGO_CUENTA = ?, " +
						   "ACCUSU_PERMIT_TEXTO = ?, " +
						   "ACCUSU_PANTALLA = ?, " +
						   "ACCUSU_NIVEL_LOGIST = ?";
		
		try {
			ps = MysqlConnect.db.conn.prepareStatement(sqlInsert);
			// Insert
			ps.setString(1, Cadena.left(empresa, 2));
			ps.setString(2, Cadena.left(usuario, 8));
			ps.setInt(3, usuarioNumero);
			ps.setString(4, Cadena.left(nombre, 30));
			ps.setInt(5, activo);
			ps.setString(6, Cadena.left(nickAlias, 16));
			ps.setString(7, Cadena.left(password, 8));
			ps.setInt(8, fechaPassword);
			ps.setString(9, Cadena.left(anteriorPassword[0], 8));
			ps.setString(10, Cadena.left(anteriorPassword[1], 8));
			ps.setString(11, Cadena.left(anteriorPassword[2], 8));
			ps.setString(12, Cadena.left(anteriorPassword[3], 8));
			ps.setString(13, Cadena.left(anteriorPassword[4], 8));
			ps.setString(14, Cadena.left(menu, 8));
			ps.setString(15, Cadena.left(grupo, 8));
			ps.setInt(16, nivelContable);
			ps.setInt(17, nivelGestion);
			ps.setString(18, Cadena.left(telefonoAgenda, 8));
			ps.setString(19, Cadena.left(telefono, 16));
			ps.setString(20, Cadena.left(localizacion, 4));
			ps.setString(21, Cadena.left(correo, 3));
			ps.setInt(22, centroContable);
			ps.setInt(23, centroGestion);
			ps.setString(24, Cadena.left(ultimoPrograma, 8));
			ps.setInt(25, ultimaFecha);
			ps.setInt(26, horaInicio);
			ps.setInt(27, horaFin);
			ps.setInt(28, intentosFallo);
			// 29
			for(int i = 0; i < 10; i++)
				ps.setString(29+i, Cadena.left(ttyPermitido[i], 5));
			// 39
			ps.setInt(39, intro);
			ps.setInt(40, adelante);
			ps.setInt(41, atras);
			ps.setInt(42, acepto);
			ps.setInt(43, rechazo);
			ps.setInt(44, avancePagina);
			ps.setInt(45, retrocesoPagina);
			ps.setInt(46, derecha);
			ps.setInt(47, izquierda);
			ps.setInt(48, ayuda);
			ps.setString(49, Cadena.left(literalIntro, 6));
			ps.setString(50, Cadena.left(literalAdelante, 6));
			ps.setString(51, Cadena.left(literalAtras, 6));
			ps.setString(52, Cadena.left(literalAcepto, 6));
			ps.setString(53, Cadena.left(literalRechazo, 6));
			ps.setString(54, Cadena.left(literalAvancePagina, 6));
			ps.setString(55, Cadena.left(literalRetrocesoPagina, 6));
			ps.setString(56, Cadena.left(literalDerecha, 6));
			ps.setString(57, Cadena.left(literalIzquierda, 6));
			ps.setString(58, Cadena.left(literalAyuda, 6));
			ps.setInt(59, idioma);
			ps.setInt(60, lineasPapel);
			ps.setInt(61, anchoPapel);
			ps.setInt(62, impresNormal);
			ps.setInt(63, impresComprimido);
			ps.setInt(64, impresExpandido);
			ps.setInt(65, impresDoceavos);
			ps.setInt(66, impresCalidad);
			ps.setInt(67, impresNegrita);
			ps.setInt(68, impresDobleAltura);
			ps.setInt(69, impresSubrayado);
			ps.setInt(70, tipo);
			ps.setInt(71, codigoCuenta);
			ps.setInt(72, permiteTexto);
			ps.setInt(73, pantalla);
			ps.setInt(74, nivelLogistica);
			
			// Update
			ps.setString(75, Cadena.left(empresa, 2));
			ps.setString(76, Cadena.left(usuario, 8));
			ps.setInt(77, usuarioNumero);
			ps.setString(78, Cadena.left(nombre, 30));
			ps.setInt(79, activo);
			ps.setString(80, Cadena.left(nickAlias, 16));
			ps.setString(81, Cadena.left(password, 8));
			ps.setInt(82, fechaPassword);
			ps.setString(83, Cadena.left(anteriorPassword[0], 8));
			ps.setString(84, Cadena.left(anteriorPassword[1], 8));
			ps.setString(85, Cadena.left(anteriorPassword[2], 8));
			ps.setString(86, Cadena.left(anteriorPassword[3], 8));
			ps.setString(87, Cadena.left(anteriorPassword[4], 8));
			ps.setString(88, Cadena.left(menu, 8));
			ps.setString(89, Cadena.left(grupo, 8));
			ps.setInt(90, nivelContable);
			ps.setInt(91, nivelGestion);
			ps.setString(92, Cadena.left(telefonoAgenda, 8));
			ps.setString(93, Cadena.left(telefono, 16));
			ps.setString(94, Cadena.left(localizacion, 4));
			ps.setString(95, Cadena.left(correo, 3));
			ps.setInt(96, centroContable);
			ps.setInt(97, centroGestion);
			ps.setString(98, Cadena.left(ultimoPrograma, 8));
			ps.setInt(99, ultimaFecha);
			ps.setInt(100, horaInicio);
			ps.setInt(101, horaFin);
			ps.setInt(102, intentosFallo);
			// 103
			for(int i = 0; i < 10; i++)
				ps.setString(103+i, Cadena.left(ttyPermitido[i], 5));
			// 113
			ps.setInt(113, intro);
			ps.setInt(114, adelante);
			ps.setInt(115, atras);
			ps.setInt(116, acepto);
			ps.setInt(117, rechazo);
			ps.setInt(118, avancePagina);
			ps.setInt(119, retrocesoPagina);
			ps.setInt(120, derecha);
			ps.setInt(121, izquierda);
			ps.setInt(122, ayuda);
			ps.setString(123, Cadena.left(literalIntro, 6));
			ps.setString(124, Cadena.left(literalAdelante, 6));
			ps.setString(125, Cadena.left(literalAtras, 6));
			ps.setString(126, Cadena.left(literalAcepto, 6));
			ps.setString(127, Cadena.left(literalRechazo, 6));
			ps.setString(128, Cadena.left(literalAvancePagina, 6));
			ps.setString(129, Cadena.left(literalRetrocesoPagina, 6));
			ps.setString(130, Cadena.left(literalDerecha, 6));
			ps.setString(131, Cadena.left(literalIzquierda, 6));
			ps.setString(132, Cadena.left(literalAyuda, 6));
			ps.setInt(133, idioma);
			ps.setInt(134, lineasPapel);
			ps.setInt(135, anchoPapel);
			ps.setInt(136, impresNormal);
			ps.setInt(137, impresComprimido);
			ps.setInt(138, impresExpandido);
			ps.setInt(139, impresDoceavos);
			ps.setInt(140, impresCalidad);
			ps.setInt(141, impresNegrita);
			ps.setInt(142, impresDobleAltura);
			ps.setInt(143, impresSubrayado);
			ps.setInt(144, tipo);
			ps.setInt(145, codigoCuenta);
			ps.setInt(146, permiteTexto);
			ps.setInt(147, pantalla);
			ps.setInt(148, nivelLogistica);
			
			ps.execute();
			
		} catch (SQLException e) {
			if(DatosComunes.enDebug){
				JOptionPane.showMessageDialog(null,
				"Error en escritura fichero de Usuario!!!");
				e.printStackTrace();
			}
		}
	}

	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getUsuarioNumero() {
		return usuarioNumero;
	}
	public void setUsuarioNumero(int usuarioNumero) {
		this.usuarioNumero = usuarioNumero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public String getNickAlias() {
		return nickAlias;
	}
	public void setNickAlias(String nickAlias) {
		this.nickAlias = nickAlias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFechaPassword() {
		return fechaPassword;
	}
	public void setFechaPassword(int fechaPassword) {
		this.fechaPassword = fechaPassword;
	}
	public String getAnteriorPassword(int i) {
		return anteriorPassword[i];
	}
	public String[] getAnteriorPassword() {
		return anteriorPassword;
	}
	public void setAnteriorPassword(int indice, String valor) {
		this.anteriorPassword[indice] = valor;
	}
	public void setAnteriorPassword(String[] anteriorPassword) {
		this.anteriorPassword = anteriorPassword;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public int getNivelContable() {
		return nivelContable;
	}
	public void setNivelContable(int nivelContable) {
		this.nivelContable = nivelContable;
	}
	public int getNivelGestion() {
		return nivelGestion;
	}
	public void setNivelGestion(int nivelGestion) {
		this.nivelGestion = nivelGestion;
	}
	public int getNivelLogistica() {
		return nivelLogistica;
	}
	public void setNivelLogistica(int nivelLogistica) {
		this.nivelLogistica = nivelLogistica;
	}
	public String getTelefonoAgenda() {
		return telefonoAgenda;
	}
	public void setTelefonoAgenda(String telefonoAgenda) {
		this.telefonoAgenda = telefonoAgenda;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public int getCentroContable() {
		return centroContable;
	}
	public void setCentroContable(int centroContable) {
		this.centroContable = centroContable;
	}
	public int getCentroGestion() {
		return centroGestion;
	}
	public void setCentroGestion(int centroGestion) {
		this.centroGestion = centroGestion;
	}
	public String getUltimoPrograma() {
		return ultimoPrograma;
	}
	public void setUltimoPrograma(String ultimoPrograma) {
		this.ultimoPrograma = ultimoPrograma;
	}
	public int getUltimaFecha() {
		return ultimaFecha;
	}
	public void setUltimaFecha(int ultimaFecha) {
		this.ultimaFecha = ultimaFecha;
	}
	public int getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}
	public int getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}
	public int getIntentosFallo() {
		return intentosFallo;
	}
	public void setIntentosFallo(int intentosFallo) {
		this.intentosFallo = intentosFallo;
	}
	public String getTtyPermitido(int i) {
		return ttyPermitido[i];
	}
	public String[] getTtyPermitido() {
		return ttyPermitido;
	}
	public void setTtyPermitido(int indice, String valor) {
		this.ttyPermitido[indice] = valor;
	}
	public void setTtyPermitido(String[] ttyPermitido) {
		this.ttyPermitido = ttyPermitido;
	}
	public int getIntro() {
		return intro;
	}
	public void setIntro(int intro) {
		this.intro = intro;
	}
	public int getAdelante() {
		return adelante;
	}
	public void setAdelante(int adelante) {
		this.adelante = adelante;
	}
	public int getAtras() {
		return atras;
	}
	public void setAtras(int atras) {
		this.atras = atras;
	}
	public int getAcepto() {
		return acepto;
	}
	public void setAcepto(int acepto) {
		this.acepto = acepto;
	}
	public int getRechazo() {
		return rechazo;
	}
	public void setRechazo(int rechazo) {
		this.rechazo = rechazo;
	}
	public int getAvancePagina() {
		return avancePagina;
	}
	public void setAvancePagina(int avancePagina) {
		this.avancePagina = avancePagina;
	}
	public int getRetrocesoPagina() {
		return retrocesoPagina;
	}
	public void setRetrocesoPagina(int retrocesoPagina) {
		this.retrocesoPagina = retrocesoPagina;
	}
	public int getDerecha() {
		return derecha;
	}
	public void setDerecha(int derecha) {
		this.derecha = derecha;
	}
	public int getIzquierda() {
		return izquierda;
	}
	public void setIzquierda(int izquierda) {
		this.izquierda = izquierda;
	}
	public int getAyuda() {
		return ayuda;
	}
	public void setAyuda(int ayuda) {
		this.ayuda = ayuda;
	}
	public String getLiteralIntro() {
		return literalIntro;
	}
	public void setLiteralIntro(String literalIntro) {
		this.literalIntro = literalIntro;
	}
	public String getLiteralAdelante() {
		return literalAdelante;
	}
	public void setLiteralAdelante(String literalAdelante) {
		this.literalAdelante = literalAdelante;
	}
	public String getLiteralAtras() {
		return literalAtras;
	}
	public void setLiteralAtras(String literalAtras) {
		this.literalAtras = literalAtras;
	}
	public String getLiteralAcepto() {
		return literalAcepto;
	}
	public void setLiteralAcepto(String literalAcepto) {
		this.literalAcepto = literalAcepto;
	}
	public String getLiteralRechazo() {
		return literalRechazo;
	}
	public void setLiteralRechazo(String literalRechazo) {
		this.literalRechazo = literalRechazo;
	}
	public String getLiteralAvancePagina() {
		return literalAvancePagina;
	}
	public void setLiteralAvancePagina(String literalAvancePagina) {
		this.literalAvancePagina = literalAvancePagina;
	}
	public String getLiteralRetrocesoPagina() {
		return literalRetrocesoPagina;
	}
	public void setLiteralRetrocesoPagina(String literalRetrocesoPagina) {
		this.literalRetrocesoPagina = literalRetrocesoPagina;
	}
	public String getLiteralDerecha() {
		return literalDerecha;
	}
	public void setLiteralDerecha(String literalDerecha) {
		this.literalDerecha = literalDerecha;
	}
	public String getLiteralIzquierda() {
		return literalIzquierda;
	}
	public void setLiteralIzquierda(String literalIzquierda) {
		this.literalIzquierda = literalIzquierda;
	}
	public String getLiteralAyuda() {
		return literalAyuda;
	}
	public void setLiteralAyuda(String literalAyuda) {
		this.literalAyuda = literalAyuda;
	}
	public int getIdioma() {
		return idioma;
	}
	public void setIdioma(int idioma) {
		this.idioma = idioma;
	}
	public int getLineasPapel() {
		return lineasPapel;
	}
	public void setLineasPapel(int lineasPapel) {
		this.lineasPapel = lineasPapel;
	}
	public int getAnchoPapel() {
		return anchoPapel;
	}
	public void setAnchoPapel(int anchoPapel) {
		this.anchoPapel = anchoPapel;
	}
	public int getImpresNormal() {
		return impresNormal;
	}
	public void setImpresNormal(int impresNormal) {
		this.impresNormal = impresNormal;
	}
	public int getImpresComprimido() {
		return impresComprimido;
	}
	public void setImpresComprimido(int impresComprimido) {
		this.impresComprimido = impresComprimido;
	}
	public int getImpresExpandido() {
		return impresExpandido;
	}
	public void setImpresExpandido(int impresExpandido) {
		this.impresExpandido = impresExpandido;
	}
	public int getImpresDoceavos() {
		return impresDoceavos;
	}
	public void setImpresDoceavos(int impresDoceavos) {
		this.impresDoceavos = impresDoceavos;
	}
	public int getImpresCalidad() {
		return impresCalidad;
	}
	public void setImpresCalidad(int impresCalidad) {
		this.impresCalidad = impresCalidad;
	}
	public int getImpresNegrita() {
		return impresNegrita;
	}
	public void setImpresNegrita(int impresNegrita) {
		this.impresNegrita = impresNegrita;
	}
	public int getImpresDobleAltura() {
		return impresDobleAltura;
	}
	public void setImpresDobleAltura(int impresDobleAltura) {
		this.impresDobleAltura = impresDobleAltura;
	}
	public int getImpresSubrayado() {
		return impresSubrayado;
	}
	public void setImpresSubrayado(int impresSubrayado) {
		this.impresSubrayado = impresSubrayado;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getCodigoCuenta() {
		return codigoCuenta;
	}
	public void setCodigoCuenta(int codigoCuenta) {
		this.codigoCuenta = codigoCuenta;
	}
	public int getPermiteTexto() {
		return permiteTexto;
	}
	public void setPermiteTexto(int permiteTexto) {
		this.permiteTexto = permiteTexto;
	}
	public int getPantalla() {
		return pantalla;
	}
	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}

}
