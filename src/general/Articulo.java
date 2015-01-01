package general;

public class Articulo {
	private char indNivel;
	// Que será mejor, String ó char[3]???
	private String marca;
	private int gama;
	private int familia;
	private String articulo;
	private String ean;
	private char codigoAbc;
	private String descrip;
	private int provHabitual;
	private String sustituyeA;
	private String sustituidoPor;
	// Ojito con DOUBLE ó INT
	private double stock;
	private double valExis;
	private double preCoste;
	private double stkInicejer;
	private double valInicejer;
	private double pendRecibir;
	private double asignServir;
	private double disp72h;
	private double stMinimo;
	private double stMaximo;
	private int rotBajmin;
	private int rotStAny;
	private int fecUltCom;
	private double canUltCom;
	private double preUltCom;
	private double preAntCom;
	private int prfrMgfSuper;
	private double prPrvCesion;
	private double prPrvFra;
	private double prPrvRapel;
	// 6
	private double porcCesFra[];
	// 5
	private int formCesFra[];
	// 2
	private double porcFraRapel[];
	private int formFraRapel;
	private double masImpPrFijo;
	private double newPrPrvCesion;
	private double newPrPrvFra;
	private double newPrPrvRapel;
	// 6
	private double newPorcCesFra[];
	// 5
	private int newFormCesFra[];
	// 2
	private double newPorcFraRapel[];
	private int newFormFraRapel;
	private double newfutMasInpPrFijo;
	private double antPrPrvCesion;
	private double anrPrPrvFra;
	private double antPrPrvRapel;
	// 6
	private double antPorcCesFra[];
	// 5
	private int antFormCesFra[];
	// 2
	private double antPorcFraRapel[];
	private int antFormFraRapel;
	private double antfutMasImpPrFijo;
	private int diasAntelPed;
	private int diasDemoraServ;
	private int loteServicio;
	private int lotePedido;
	private String campania;
	private int margenSuper;
	// 9
	private double margen[];
	// 6
	private double precVenta[];
	// 6
	private double precNewTarifa[];
	// 6
	private int dto[];
	// 2
	private double pvpTienda[];
	// 2
	private double pvrefTienda[];
	// 2
	private double pvespTienda[];
	private double precomendFabr;
	private int iva;
	private int requ;
	// 3
	private double comision[];
	private String ctaVtas;
	private int tipo;
	private int exclusivo;
	private int fecModif;
	private int fecAlta;
	private int noValstk;
	private int prvrcpFec;
	private double prvrcpCan;

	// Constructores

	Articulo() {
		indNivel = '0';
		// Que será mejor, String ó char[3]???
		marca = "";
		gama = familia = 0;
		articulo = ean = "";
		codigoAbc = ' ';
		descrip = "";
		provHabitual = 0;
		sustituyeA = sustituidoPor = "";
		// Ojito con DOUBLE ó INT
		stock = valExis = preCoste = stkInicejer = valInicejer = pendRecibir = asignServir = disp72h = stMinimo = stMaximo = 0.0;
		rotBajmin = rotStAny = fecUltCom = 0;
		canUltCom = preUltCom = preAntCom = 0.0;
		prfrMgfSuper = 0;
		prPrvCesion = prPrvFra = prPrvRapel = 0.0;
		// 6
		porcCesFra = new double[6];
		for (int i = 0; i < 6; i++)
			porcCesFra[i] = 0.0;

		// 5
		formCesFra = new int[5];
		for (int i = 0; i < 5; i++)
			formCesFra[i] = 0;
		// 2
		porcFraRapel = new double[2];
		for (int i = 0; i < 2; i++)
			porcFraRapel[i] = 0.0;

		formFraRapel = 0;

		masImpPrFijo = newPrPrvCesion = newPrPrvFra = newPrPrvRapel = 0.0;

		// 6
		newPorcCesFra = new double[6];
		for (int i = 0; i < 6; i++)
			newPorcCesFra[i] = 0.0;
		// 5
		newFormCesFra = new int[5];
		for (int i = 0; i < 5; i++)
			newFormCesFra[i] = 0;
		// 2
		newPorcFraRapel = new double[2];
		for (int i = 0; i < 2; i++)
			newPorcFraRapel[i] = 0.0;

		newFormFraRapel = 0;
		newfutMasInpPrFijo = antPrPrvCesion = anrPrPrvFra = antPrPrvRapel = 0.0;
		// 6
		antPorcCesFra = new double[6];
		for (int i = 0; i < 6; i++)
			antPorcCesFra[i] = 0.0;
		// 5
		antFormCesFra = new int[5];
		for (int i = 0; i < 5; i++)
			antFormCesFra[i] = 0;
		// 2
		antPorcFraRapel = new double[2];
		for (int i = 0; i < 2; i++)
			antPorcFraRapel[i] = 0.0;

		antFormFraRapel = 0;
		antfutMasImpPrFijo = 0.0;
		diasAntelPed = diasDemoraServ = loteServicio = lotePedido = 0;
		campania = "";
		margenSuper = 0;
		// 9
		margen = new double[9];
		for (int i = 0; i < 9; i++)
			margen[i] = 0.0;
		// 6
		precVenta = new double[6];
		for (int i = 0; i < 6; i++)
			precVenta[i] = 0.0;
		// 6
		precNewTarifa = new double[6];
		for (int i = 0; i < 6; i++)
			precNewTarifa[i] = 0.0;
		// 6
		dto = new int[6];
		for (int i = 0; i < 6; i++)
			dto[i] = 0;
		// 2
		pvpTienda = new double[2];
		for (int i = 0; i < 2; i++)
			pvpTienda[i] = 0;
		// 2
		pvrefTienda = new double[2];
		for (int i = 0; i < 2; i++)
			pvrefTienda[i] = 0.0;
		// 2
		pvespTienda = new double[2];
		for (int i = 0; i < 2; i++)
			pvespTienda[i] = 0.0;

		precomendFabr = 0.0;
		iva = requ = 0;
		// 3
		comision = new double[3];
		for (int i = 0; i < 3; i++)
			comision[i] = 0.0;

		ctaVtas = "";
		tipo = exclusivo = fecModif = fecAlta = noValstk = prvrcpFec = 0;
		prvrcpCan = 0.0;
	}

	public char getIndNivel() {
		return indNivel;
	}

	public void setIndNivel(char indNivel) {
		this.indNivel = indNivel;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getGama() {
		return gama;
	}

	public void setGama(int gama) {
		this.gama = gama;
	}

	public int getFamilia() {
		return familia;
	}

	public void setFamilia(int familia) {
		this.familia = familia;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public char getCodigoAbc() {
		return codigoAbc;
	}

	public void setCodigoAbc(char codigoAbc) {
		this.codigoAbc = codigoAbc;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public int getProvHabitual() {
		return provHabitual;
	}

	public void setProvHabitual(int provHabitual) {
		this.provHabitual = provHabitual;
	}

	public String getSustituyeA() {
		return sustituyeA;
	}

	public void setSustituyeA(String sustituyeA) {
		this.sustituyeA = sustituyeA;
	}

	public String getSustituidoPor() {
		return sustituidoPor;
	}

	public void setSustituidoPor(String sustituidoPor) {
		this.sustituidoPor = sustituidoPor;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getValExis() {
		return valExis;
	}

	public void setValExis(double valExis) {
		this.valExis = valExis;
	}

	public double getPreCoste() {
		return preCoste;
	}

	public void setPreCoste(double preCoste) {
		this.preCoste = preCoste;
	}

	public double getStkInicejer() {
		return stkInicejer;
	}

	public void setStkInicejer(double stkInicejer) {
		this.stkInicejer = stkInicejer;
	}

	public double getValInicejer() {
		return valInicejer;
	}

	public void setValInicejer(double valInicejer) {
		this.valInicejer = valInicejer;
	}

	public double getPendRecibir() {
		return pendRecibir;
	}

	public void setPendRecibir(double pendRecibir) {
		this.pendRecibir = pendRecibir;
	}

	public double getAsignServir() {
		return asignServir;
	}

	public void setAsignServir(double asignServir) {
		this.asignServir = asignServir;
	}

	public double getDisp72h() {
		return disp72h;
	}

	public void setDisp72h(double disp72h) {
		this.disp72h = disp72h;
	}

	public double getStMinimo() {
		return stMinimo;
	}

	public void setStMinimo(double stMinimo) {
		this.stMinimo = stMinimo;
	}

	public double getStMaximo() {
		return stMaximo;
	}

	public void setStMaximo(double stMaximo) {
		this.stMaximo = stMaximo;
	}

	public int getRotBajmin() {
		return rotBajmin;
	}

	public void setRotBajmin(int rotBajmin) {
		this.rotBajmin = rotBajmin;
	}

	public int getRotStAny() {
		return rotStAny;
	}

	public void setRotStAny(int rotStAny) {
		this.rotStAny = rotStAny;
	}

	public int getFecUltCom() {
		return fecUltCom;
	}

	public void setFecUltCom(int fecUltCom) {
		this.fecUltCom = fecUltCom;
	}

	public double getCanUltCom() {
		return canUltCom;
	}

	public void setCanUltCom(double canUltCom) {
		this.canUltCom = canUltCom;
	}

	public double getPreUltCom() {
		return preUltCom;
	}

	public void setPreUltCom(double preUltCom) {
		this.preUltCom = preUltCom;
	}

	public double getPreAntCom() {
		return preAntCom;
	}

	public void setPreAntCom(double preAntCom) {
		this.preAntCom = preAntCom;
	}

	public int getPrfrMgfSuper() {
		return prfrMgfSuper;
	}

	public void setPrfrMgfSuper(int prfrMgfSuper) {
		this.prfrMgfSuper = prfrMgfSuper;
	}

	public double getPrPrvCesion() {
		return prPrvCesion;
	}

	public void setPrPrvCesion(double prPrvCesion) {
		this.prPrvCesion = prPrvCesion;
	}

	public double getPrPrvFra() {
		return prPrvFra;
	}

	public void setPrPrvFra(double prPrvFra) {
		this.prPrvFra = prPrvFra;
	}

	public double getPrPrvRapel() {
		return prPrvRapel;
	}

	public void setPrPrvRapel(double prPrvRapel) {
		this.prPrvRapel = prPrvRapel;
	}

	public double[] getPorcCesFra() {
		return porcCesFra;
	}

	public void setPorcCesFra(double[] porcCesFra) {
		this.porcCesFra = porcCesFra;
	}

	public int[] getFormCesFra() {
		return formCesFra;
	}

	public void setFormCesFra(int[] formCesFra) {
		this.formCesFra = formCesFra;
	}

	public double[] getPorcFraRapel() {
		return porcFraRapel;
	}

	public void setPorcFraRapel(double[] porcFraRapel) {
		this.porcFraRapel = porcFraRapel;
	}

	public int getFormFraRapel() {
		return formFraRapel;
	}

	public void setFormFraRapel(int formFraRapel) {
		this.formFraRapel = formFraRapel;
	}

	public double getMasImpPrFijo() {
		return masImpPrFijo;
	}

	public void setMasImpPrFijo(double masImpPrFijo) {
		this.masImpPrFijo = masImpPrFijo;
	}

	public double getNewPrPrvCesion() {
		return newPrPrvCesion;
	}

	public void setNewPrPrvCesion(double newPrPrvCesion) {
		this.newPrPrvCesion = newPrPrvCesion;
	}

	public double getNewPrPrvFra() {
		return newPrPrvFra;
	}

	public void setNewPrPrvFra(double newPrPrvFra) {
		this.newPrPrvFra = newPrPrvFra;
	}

	public double getNewPrPrvRapel() {
		return newPrPrvRapel;
	}

	public void setNewPrPrvRapel(double newPrPrvRapel) {
		this.newPrPrvRapel = newPrPrvRapel;
	}

	public double[] getNewPorcCesFra() {
		return newPorcCesFra;
	}

	public void setNewPorcCesFra(double[] newPorcCesFra) {
		this.newPorcCesFra = newPorcCesFra;
	}

	public int[] getNewFormCesFra() {
		return newFormCesFra;
	}

	public void setNewFormCesFra(int[] newFormCesFra) {
		this.newFormCesFra = newFormCesFra;
	}

	public double[] getNewPorcFraRapel() {
		return newPorcFraRapel;
	}

	public void setNewPorcFraRapel(double[] newPorcFraRapel) {
		this.newPorcFraRapel = newPorcFraRapel;
	}

	public int getNewFormFraRapel() {
		return newFormFraRapel;
	}

	public void setNewFormFraRapel(int newFormFraRapel) {
		this.newFormFraRapel = newFormFraRapel;
	}

	public double getNewfutMasInpPrFijo() {
		return newfutMasInpPrFijo;
	}

	public void setNewfutMasInpPrFijo(double newfutMasInpPrFijo) {
		this.newfutMasInpPrFijo = newfutMasInpPrFijo;
	}

	public double getAntPrPrvCesion() {
		return antPrPrvCesion;
	}

	public void setAntPrPrvCesion(double antPrPrvCesion) {
		this.antPrPrvCesion = antPrPrvCesion;
	}

	public double getAnrPrPrvFra() {
		return anrPrPrvFra;
	}

	public void setAnrPrPrvFra(double anrPrPrvFra) {
		this.anrPrPrvFra = anrPrPrvFra;
	}

	public double getAntPrPrvRapel() {
		return antPrPrvRapel;
	}

	public void setAntPrPrvRapel(double antPrPrvRapel) {
		this.antPrPrvRapel = antPrPrvRapel;
	}

	public double[] getAntPorcCesFra() {
		return antPorcCesFra;
	}

	public void setAntPorcCesFra(double[] antPorcCesFra) {
		this.antPorcCesFra = antPorcCesFra;
	}

	public int[] getAntFormCesFra() {
		return antFormCesFra;
	}

	public void setAntFormCesFra(int[] antFormCesFra) {
		this.antFormCesFra = antFormCesFra;
	}

	public double[] getAntPorcFraRapel() {
		return antPorcFraRapel;
	}

	public void setAntPorcFraRapel(double[] antPorcFraRapel) {
		this.antPorcFraRapel = antPorcFraRapel;
	}

	public int getAntFormFraRapel() {
		return antFormFraRapel;
	}

	public void setAntFormFraRapel(int antFormFraRapel) {
		this.antFormFraRapel = antFormFraRapel;
	}

	public double getAntfutMasImpPrFijo() {
		return antfutMasImpPrFijo;
	}

	public void setAntfutMasImpPrFijo(double antfutMasImpPrFijo) {
		this.antfutMasImpPrFijo = antfutMasImpPrFijo;
	}

	public int getDiasAntelPed() {
		return diasAntelPed;
	}

	public void setDiasAntelPed(int diasAntelPed) {
		this.diasAntelPed = diasAntelPed;
	}

	public int getDiasDemoraServ() {
		return diasDemoraServ;
	}

	public void setDiasDemoraServ(int diasDemoraServ) {
		this.diasDemoraServ = diasDemoraServ;
	}

	public int getLoteServicio() {
		return loteServicio;
	}

	public void setLoteServicio(int loteServicio) {
		this.loteServicio = loteServicio;
	}

	public int getLotePedido() {
		return lotePedido;
	}

	public void setLotePedido(int lotePedido) {
		this.lotePedido = lotePedido;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public int getMargenSuper() {
		return margenSuper;
	}

	public void setMargenSuper(int margenSuper) {
		this.margenSuper = margenSuper;
	}

	public double[] getMargen() {
		return margen;
	}

	public void setMargen(double[] margen) {
		this.margen = margen;
	}

	public double[] getPrecVenta() {
		return precVenta;
	}

	public void setPrecVenta(double[] precVenta) {
		this.precVenta = precVenta;
	}

	public double[] getPrecnewTarifa() {
		return precNewTarifa;
	}

	public void setPrecnewTarifa(double[] precnewTarifa) {
		this.precNewTarifa = precnewTarifa;
	}

	public int[] getDto() {
		return dto;
	}

	public void setDto(int[] dto) {
		this.dto = dto;
	}

	public double[] getPvpTienda() {
		return pvpTienda;
	}

	public void setPvpTienda(double[] pvpTienda) {
		this.pvpTienda = pvpTienda;
	}

	public double[] getPvrefTienda() {
		return pvrefTienda;
	}

	public void setPvrefTienda(double[] pvrefTienda) {
		this.pvrefTienda = pvrefTienda;
	}

	public double[] getPvespTienda() {
		return pvespTienda;
	}

	public void setPvespTienda(double[] pvespTienda) {
		this.pvespTienda = pvespTienda;
	}

	public double getPrecomendFabr() {
		return precomendFabr;
	}

	public void setPrecomendFabr(double precomendFabr) {
		this.precomendFabr = precomendFabr;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	public int getRequ() {
		return requ;
	}

	public void setRequ(int requ) {
		this.requ = requ;
	}

	public double[] getComision() {
		return comision;
	}

	public void setComision(double[] comision) {
		this.comision = comision;
	}

	public String getCtaVtas() {
		return ctaVtas;
	}

	public void setCtaVtas(String ctaVtas) {
		this.ctaVtas = ctaVtas;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getExclusivo() {
		return exclusivo;
	}

	public void setExclusivo(int exclusivo) {
		this.exclusivo = exclusivo;
	}

	public int getFecModif() {
		return fecModif;
	}

	public void setFecModif(int fecModif) {
		this.fecModif = fecModif;
	}

	public int getFecAlta() {
		return fecAlta;
	}

	public void setFecAlta(int fecAlta) {
		this.fecAlta = fecAlta;
	}

	public int getNoValstk() {
		return noValstk;
	}

	public void setNoValstk(int noValstk) {
		this.noValstk = noValstk;
	}

	public int getPrvrcpFec() {
		return prvrcpFec;
	}

	public void setPrvrcpFec(int prvrcpFec) {
		this.prvrcpFec = prvrcpFec;
	}

	public double getPrvrcpCan() {
		return prvrcpCan;
	}

	public void setPrvrcpCan(double prvrcpCan) {
		this.prvrcpCan = prvrcpCan;
	}

}
