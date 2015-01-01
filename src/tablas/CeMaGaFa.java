package tablas;

public class CeMaGaFa {
	private int centro;
	private String marca;
	private int gama;
	private int familia;
	private String descrip;
	private int provHabitual;
	private int dosDadirPedido;
	private int activo;
	private String observac;
	private int fechaAplicTarPrv;
	private int fechaAplicTarClt;
	private int formaEnvPedpra;
	private String marcaProveedor;
	// 6
	private double[] porcCesFra;
	// 5
	private int[] formCesFra;
	// 2
	private double porcFraRapel;
	private int formFraRapel;
	private double masImpPrFijo;
	// 6
	private double[] newPorcCesFra;
	// 5
	private int[] newFormCesFra;
	// 2
	private double[] newPorcFraRapel;
	private int newFormFraRapel;
	private double newfutMasImpPrFijo;
	// 6
	private double[] antPorcCesFra;
	// 5
	private int[] antFormCesFra;
	// 2
	private double[] antPorcFraRapel;
	private int antFormFraRapel;
	private double antfutMasImpPrFijo;
	private double rapelCondic;
	private double rapelPublicid;
	private int margenSuper;
	// 9
	private double[] margen;
	// 6
	private int[] dto;
	private int newTarif;
	private int centroHabitual;
	private double stock;
	private double valExis;
	private double stkInicejer;
	private double valInicejer;
	private int famConImagen;
	private char marcaSelecc;
	private int marcaPrtstk;
	private String marcaFiller;
	private int famSubastDsdmes;
	private int famSubastDtomes;
	private char famFiller;
	private int famCoeficLineales;
	private String Filler;
	
	CeMaGaFa(){
		centro = 0;
		marca = "";
		gama = familia = 0;
		descrip = "";
		provHabitual = dosDadirPedido = activo = 0;
		observac = "";
		fechaAplicTarPrv = fechaAplicTarClt = formaEnvPedpra = 0;
		marcaProveedor = "";
		// 6
		porcCesFra = new double[6];
		for(int i = 0; i < 6; i++)
			porcCesFra[i] = 0.0;
		// 5		
		formCesFra = new int[5];
		for(int i = 0; i < 5; i++)
			formCesFra[i] = 0;
		// 2
		porcFraRapel = 0.0;
		formFraRapel = 0;
		masImpPrFijo = 0.0;
		// 6
		newPorcCesFra = new double[6];
		for(int i = 0; i <6; i++)
			newPorcCesFra[i] = 0.0;
		// 5
		newFormCesFra = new int[5];
		for(int i = 0; i < 5; i++)
			newFormCesFra[i] = 0;
		// 2
		newPorcFraRapel = new double[2];
		for(int i = 0; i < 2; i++)
			newPorcFraRapel[i] = 0.0;
		
		newFormFraRapel = 0;
		newfutMasImpPrFijo = 0.0;
		// 6
		antPorcCesFra = new double[6];
		for(int i = 0; i < 6; i++)
			antPorcCesFra[i] = 0.0;
		// 5
		antFormCesFra = new int[5];
		for(int i = 0; i < 5; i++)
			antFormCesFra[i] = 0;
		// 2
		antPorcFraRapel = new double[2];
		for(int i = 0; i <2; i++)
			antPorcFraRapel[i] = 0.0;
		
		antFormFraRapel = 0;
		antfutMasImpPrFijo = rapelCondic = rapelPublicid = 0.0;
		margenSuper = 0;
		// 9
		margen = new double[9];
		for(int i = 0; i < 9; i++)
			margen[i] = 0.0;
		// 6
		dto = new int[6];
		for(int i = 0; i < 6; i++)
			dto[i] = 0;
		
		newTarif = centroHabitual = 0;
		stock = valExis = stkInicejer =	valInicejer = 0;
		famConImagen = 0;
		marcaSelecc = ' ';
		marcaPrtstk = 0;
		marcaFiller = "";
		famSubastDsdmes = famSubastDtomes = 0;
		famFiller = ' ';
		famCoeficLineales = 0;
		Filler = "";	
	}
	
	public int getCentro() {
		return centro;
	}
	public void setCentro(int centro) {
		this.centro = centro;
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
	public int getDosDadirPedido() {
		return dosDadirPedido;
	}
	public void setDosDadirPedido(int dosDadirPedido) {
		this.dosDadirPedido = dosDadirPedido;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public String getObservac() {
		return observac;
	}
	public void setObservac(String observac) {
		this.observac = observac;
	}
	public int getFechaAplicTarPrv() {
		return fechaAplicTarPrv;
	}
	public void setFechaAplicTarPrv(int fechaAplicTarPrv) {
		this.fechaAplicTarPrv = fechaAplicTarPrv;
	}
	public int getFechaAplicTarClt() {
		return fechaAplicTarClt;
	}
	public void setFechaAplicTarClt(int fechaAplicTarClt) {
		this.fechaAplicTarClt = fechaAplicTarClt;
	}
	public int getFormaEnvPedpra() {
		return formaEnvPedpra;
	}
	public void setFormaEnvPedpra(int formaEnvPedpra) {
		this.formaEnvPedpra = formaEnvPedpra;
	}
	public String getMarcaProveedor() {
		return marcaProveedor;
	}
	public void setMarcaProveedor(String marcaProveedor) {
		this.marcaProveedor = marcaProveedor;
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
	public double getPorcFraRapel() {
		return porcFraRapel;
	}
	public void setPorcFraRapel(double porcFraRapel) {
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
	public double getNewfutMasImpPrFijo() {
		return newfutMasImpPrFijo;
	}
	public void setNewfutMasImpPrFijo(double newfutMasImpPrFijo) {
		this.newfutMasImpPrFijo = newfutMasImpPrFijo;
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
	public double getRapelCondic() {
		return rapelCondic;
	}
	public void setRapelCondic(double rapelCondic) {
		this.rapelCondic = rapelCondic;
	}
	public double getRapelPublicid() {
		return rapelPublicid;
	}
	public void setRapelPublicid(double rapelPublicid) {
		this.rapelPublicid = rapelPublicid;
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
	public int[] getDto() {
		return dto;
	}
	public void setDto(int[] dto) {
		this.dto = dto;
	}
	public int getNewTarif() {
		return newTarif;
	}
	public void setNewTarif(int newTarif) {
		this.newTarif = newTarif;
	}
	public int getCentroHabitual() {
		return centroHabitual;
	}
	public void setCentroHabitual(int centroHabitual) {
		this.centroHabitual = centroHabitual;
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
	public int getFamConImagen() {
		return famConImagen;
	}
	public void setFamConImagen(int famConImagen) {
		this.famConImagen = famConImagen;
	}
	public char getMarcaSelecc() {
		return marcaSelecc;
	}
	public void setMarcaSelecc(char marcaSelecc) {
		this.marcaSelecc = marcaSelecc;
	}
	public int getMarcaPrtstk() {
		return marcaPrtstk;
	}
	public void setMarcaPrtstk(int marcaPrtstk) {
		this.marcaPrtstk = marcaPrtstk;
	}
	public String getMarcaFiller() {
		return marcaFiller;
	}
	public void setMarcaFiller(String marcaFiller) {
		this.marcaFiller = marcaFiller;
	}
	public int getFamSubastDsdmes() {
		return famSubastDsdmes;
	}
	public void setFamSubastDsdmes(int famSubastDsdmes) {
		this.famSubastDsdmes = famSubastDsdmes;
	}
	public int getFamSubastDtomes() {
		return famSubastDtomes;
	}
	public void setFamSubastDtomes(int famSubastDtomes) {
		this.famSubastDtomes = famSubastDtomes;
	}
	public char getFamFiller() {
		return famFiller;
	}
	public void setFamFiller(char famFiller) {
		this.famFiller = famFiller;
	}
	public int getFamCoeficLineales() {
		return famCoeficLineales;
	}
	public void setFamCoeficLineales(int famCoeficLineales) {
		this.famCoeficLineales = famCoeficLineales;
	}
	public String getFiller() {
		return Filler;
	}
	public void setFiller(String filler) {
		Filler = filler;
	}
	
	

}
