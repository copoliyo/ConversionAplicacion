/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Txus
 */
@Entity
@Table(name = "efecob", catalog = "test", schema = "")
@NamedQueries({
    @NamedQuery(name = "Efecob.findAll", query = "SELECT e FROM Efecob e"),
    @NamedQuery(name = "Efecob.findByEmpresa", query = "SELECT e FROM Efecob e WHERE e.efecobPK.empresa = :empresa"),
    @NamedQuery(name = "Efecob.findByEfecobBanco", query = "SELECT e FROM Efecob e WHERE e.efecobBanco = :efecobBanco"),
    @NamedQuery(name = "Efecob.findByEfecobFechaCobro", query = "SELECT e FROM Efecob e WHERE e.efecobFechaCobro = :efecobFechaCobro"),
    @NamedQuery(name = "Efecob.findByEfecobVencim", query = "SELECT e FROM Efecob e WHERE e.efecobPK.efecobVencim = :efecobVencim"),
    @NamedQuery(name = "Efecob.findByEfecobEfecto", query = "SELECT e FROM Efecob e WHERE e.efecobPK.efecobEfecto = :efecobEfecto"),
    @NamedQuery(name = "Efecob.findByEfecobCentro", query = "SELECT e FROM Efecob e WHERE e.efecobCentro = :efecobCentro"),
    @NamedQuery(name = "Efecob.findByEfecobSituacion", query = "SELECT e FROM Efecob e WHERE e.efecobSituacion = :efecobSituacion"),
    @NamedQuery(name = "Efecob.findByEfecobFechaRemesa", query = "SELECT e FROM Efecob e WHERE e.efecobFechaRemesa = :efecobFechaRemesa"),
    @NamedQuery(name = "Efecob.findByEfecobRemesa", query = "SELECT e FROM Efecob e WHERE e.efecobRemesa = :efecobRemesa"),
    @NamedQuery(name = "Efecob.findByEfecobFactura", query = "SELECT e FROM Efecob e WHERE e.efecobFactura = :efecobFactura"),
    @NamedQuery(name = "Efecob.findByEfecobCuenta", query = "SELECT e FROM Efecob e WHERE e.efecobCuenta = :efecobCuenta"),
    @NamedQuery(name = "Efecob.findByEfecobFechAstoApt", query = "SELECT e FROM Efecob e WHERE e.efecobFechAstoApt = :efecobFechAstoApt"),
    @NamedQuery(name = "Efecob.findByEfecobFechaEfe", query = "SELECT e FROM Efecob e WHERE e.efecobFechaEfe = :efecobFechaEfe"),
    @NamedQuery(name = "Efecob.findByEfecobImporte", query = "SELECT e FROM Efecob e WHERE e.efecobImporte = :efecobImporte"),
    @NamedQuery(name = "Efecob.findByEfecobVtoDv", query = "SELECT e FROM Efecob e WHERE e.efecobVtoDv = :efecobVtoDv"),
    @NamedQuery(name = "Efecob.findByEfecobNroBco", query = "SELECT e FROM Efecob e WHERE e.efecobNroBco = :efecobNroBco"),
    @NamedQuery(name = "Efecob.findByEfecobNroSuc", query = "SELECT e FROM Efecob e WHERE e.efecobNroSuc = :efecobNroSuc"),
    @NamedQuery(name = "Efecob.findByEfecobDc", query = "SELECT e FROM Efecob e WHERE e.efecobDc = :efecobDc"),
    @NamedQuery(name = "Efecob.findByEfecobCta", query = "SELECT e FROM Efecob e WHERE e.efecobCta = :efecobCta"),
    @NamedQuery(name = "Efecob.findByEfecobTimbres", query = "SELECT e FROM Efecob e WHERE e.efecobTimbres = :efecobTimbres"),
    @NamedQuery(name = "Efecob.findByEfecobFechaFra", query = "SELECT e FROM Efecob e WHERE e.efecobFechaFra = :efecobFechaFra"),
    @NamedQuery(name = "Efecob.findByEfecobRepre", query = "SELECT e FROM Efecob e WHERE e.efecobRepre = :efecobRepre"),
    @NamedQuery(name = "Efecob.findByEfecobReciboImpreso", query = "SELECT e FROM Efecob e WHERE e.efecobReciboImpreso = :efecobReciboImpreso"),
    @NamedQuery(name = "Efecob.findByEfecobOrigen", query = "SELECT e FROM Efecob e WHERE e.efecobOrigen = :efecobOrigen"),
    @NamedQuery(name = "Efecob.findByEfecobFiller", query = "SELECT e FROM Efecob e WHERE e.efecobFiller = :efecobFiller")})
public class Efecob implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EfecobPK efecobPK;
    @Column(name = "EFECOB_BANCO")
    private BigInteger efecobBanco;
    @Column(name = "EFECOB_FECHA_COBRO")
    private BigInteger efecobFechaCobro;
    @Column(name = "EFECOB_CENTRO")
    private BigInteger efecobCentro;
    @Column(name = "EFECOB_SITUACION")
    private BigInteger efecobSituacion;
    @Column(name = "EFECOB_FECHA_REMESA")
    private BigInteger efecobFechaRemesa;
    @Column(name = "EFECOB_REMESA")
    private BigInteger efecobRemesa;
    @Column(name = "EFECOB_FACTURA")
    private BigInteger efecobFactura;
    @Column(name = "EFECOB_CUENTA")
    private String efecobCuenta;
    @Column(name = "EFECOB_FECH_ASTO_APT")
    private BigInteger efecobFechAstoApt;
    @Column(name = "EFECOB_FECHA_EFE")
    private BigInteger efecobFechaEfe;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EFECOB_IMPORTE")
    private BigDecimal efecobImporte;
    @Column(name = "EFECOB_VTO_DV")
    private BigInteger efecobVtoDv;
    @Column(name = "EFECOB_NRO_BCO")
    private BigInteger efecobNroBco;
    @Column(name = "EFECOB_NRO_SUC")
    private BigInteger efecobNroSuc;
    @Column(name = "EFECOB_DC")
    private BigInteger efecobDc;
    @Column(name = "EFECOB_CTA")
    private BigInteger efecobCta;
    @Column(name = "EFECOB_TIMBRES")
    private BigDecimal efecobTimbres;
    @Column(name = "EFECOB_FECHA_FRA")
    private BigInteger efecobFechaFra;
    @Column(name = "EFECOB_REPRE")
    private BigInteger efecobRepre;
    @Column(name = "EFECOB_RECIBO_IMPRESO")
    private BigInteger efecobReciboImpreso;
    @Column(name = "EFECOB_ORIGEN")
    private BigInteger efecobOrigen;
    @Column(name = "EFECOB_FILLER")
    private String efecobFiller;

    public Efecob() {
    }

    public Efecob(EfecobPK efecobPK) {
        this.efecobPK = efecobPK;
    }

    public Efecob(String empresa, long efecobVencim, long efecobEfecto) {
        this.efecobPK = new EfecobPK(empresa, efecobVencim, efecobEfecto);
    }

    public EfecobPK getEfecobPK() {
        return efecobPK;
    }

    public void setEfecobPK(EfecobPK efecobPK) {
        this.efecobPK = efecobPK;
    }

    public BigInteger getEfecobBanco() {
        return efecobBanco;
    }

    public void setEfecobBanco(BigInteger efecobBanco) {
        BigInteger oldEfecobBanco = this.efecobBanco;
        this.efecobBanco = efecobBanco;
        changeSupport.firePropertyChange("efecobBanco", oldEfecobBanco, efecobBanco);
    }

    public BigInteger getEfecobFechaCobro() {
        return efecobFechaCobro;
    }

    public void setEfecobFechaCobro(BigInteger efecobFechaCobro) {
        BigInteger oldEfecobFechaCobro = this.efecobFechaCobro;
        this.efecobFechaCobro = efecobFechaCobro;
        changeSupport.firePropertyChange("efecobFechaCobro", oldEfecobFechaCobro, efecobFechaCobro);
    }

    public BigInteger getEfecobCentro() {
        return efecobCentro;
    }

    public void setEfecobCentro(BigInteger efecobCentro) {
        BigInteger oldEfecobCentro = this.efecobCentro;
        this.efecobCentro = efecobCentro;
        changeSupport.firePropertyChange("efecobCentro", oldEfecobCentro, efecobCentro);
    }

    public BigInteger getEfecobSituacion() {
        return efecobSituacion;
    }

    public void setEfecobSituacion(BigInteger efecobSituacion) {
        BigInteger oldEfecobSituacion = this.efecobSituacion;
        this.efecobSituacion = efecobSituacion;
        changeSupport.firePropertyChange("efecobSituacion", oldEfecobSituacion, efecobSituacion);
    }

    public BigInteger getEfecobFechaRemesa() {
        return efecobFechaRemesa;
    }

    public void setEfecobFechaRemesa(BigInteger efecobFechaRemesa) {
        BigInteger oldEfecobFechaRemesa = this.efecobFechaRemesa;
        this.efecobFechaRemesa = efecobFechaRemesa;
        changeSupport.firePropertyChange("efecobFechaRemesa", oldEfecobFechaRemesa, efecobFechaRemesa);
    }

    public BigInteger getEfecobRemesa() {
        return efecobRemesa;
    }

    public void setEfecobRemesa(BigInteger efecobRemesa) {
        BigInteger oldEfecobRemesa = this.efecobRemesa;
        this.efecobRemesa = efecobRemesa;
        changeSupport.firePropertyChange("efecobRemesa", oldEfecobRemesa, efecobRemesa);
    }

    public BigInteger getEfecobFactura() {
        return efecobFactura;
    }

    public void setEfecobFactura(BigInteger efecobFactura) {
        BigInteger oldEfecobFactura = this.efecobFactura;
        this.efecobFactura = efecobFactura;
        changeSupport.firePropertyChange("efecobFactura", oldEfecobFactura, efecobFactura);
    }

    public String getEfecobCuenta() {
        return efecobCuenta;
    }

    public void setEfecobCuenta(String efecobCuenta) {
        String oldEfecobCuenta = this.efecobCuenta;
        this.efecobCuenta = efecobCuenta;
        changeSupport.firePropertyChange("efecobCuenta", oldEfecobCuenta, efecobCuenta);
    }

    public BigInteger getEfecobFechAstoApt() {
        return efecobFechAstoApt;
    }

    public void setEfecobFechAstoApt(BigInteger efecobFechAstoApt) {
        BigInteger oldEfecobFechAstoApt = this.efecobFechAstoApt;
        this.efecobFechAstoApt = efecobFechAstoApt;
        changeSupport.firePropertyChange("efecobFechAstoApt", oldEfecobFechAstoApt, efecobFechAstoApt);
    }

    public BigInteger getEfecobFechaEfe() {
        return efecobFechaEfe;
    }

    public void setEfecobFechaEfe(BigInteger efecobFechaEfe) {
        BigInteger oldEfecobFechaEfe = this.efecobFechaEfe;
        this.efecobFechaEfe = efecobFechaEfe;
        changeSupport.firePropertyChange("efecobFechaEfe", oldEfecobFechaEfe, efecobFechaEfe);
    }

    public BigDecimal getEfecobImporte() {
        return efecobImporte;
    }

    public void setEfecobImporte(BigDecimal efecobImporte) {
        BigDecimal oldEfecobImporte = this.efecobImporte;
        this.efecobImporte = efecobImporte;
        changeSupport.firePropertyChange("efecobImporte", oldEfecobImporte, efecobImporte);
    }

    public BigInteger getEfecobVtoDv() {
        return efecobVtoDv;
    }

    public void setEfecobVtoDv(BigInteger efecobVtoDv) {
        BigInteger oldEfecobVtoDv = this.efecobVtoDv;
        this.efecobVtoDv = efecobVtoDv;
        changeSupport.firePropertyChange("efecobVtoDv", oldEfecobVtoDv, efecobVtoDv);
    }

    public BigInteger getEfecobNroBco() {
        return efecobNroBco;
    }

    public void setEfecobNroBco(BigInteger efecobNroBco) {
        BigInteger oldEfecobNroBco = this.efecobNroBco;
        this.efecobNroBco = efecobNroBco;
        changeSupport.firePropertyChange("efecobNroBco", oldEfecobNroBco, efecobNroBco);
    }

    public BigInteger getEfecobNroSuc() {
        return efecobNroSuc;
    }

    public void setEfecobNroSuc(BigInteger efecobNroSuc) {
        BigInteger oldEfecobNroSuc = this.efecobNroSuc;
        this.efecobNroSuc = efecobNroSuc;
        changeSupport.firePropertyChange("efecobNroSuc", oldEfecobNroSuc, efecobNroSuc);
    }

    public BigInteger getEfecobDc() {
        return efecobDc;
    }

    public void setEfecobDc(BigInteger efecobDc) {
        BigInteger oldEfecobDc = this.efecobDc;
        this.efecobDc = efecobDc;
        changeSupport.firePropertyChange("efecobDc", oldEfecobDc, efecobDc);
    }

    public BigInteger getEfecobCta() {
        return efecobCta;
    }

    public void setEfecobCta(BigInteger efecobCta) {
        BigInteger oldEfecobCta = this.efecobCta;
        this.efecobCta = efecobCta;
        changeSupport.firePropertyChange("efecobCta", oldEfecobCta, efecobCta);
    }

    public BigDecimal getEfecobTimbres() {
        return efecobTimbres;
    }

    public void setEfecobTimbres(BigDecimal efecobTimbres) {
        BigDecimal oldEfecobTimbres = this.efecobTimbres;
        this.efecobTimbres = efecobTimbres;
        changeSupport.firePropertyChange("efecobTimbres", oldEfecobTimbres, efecobTimbres);
    }

    public BigInteger getEfecobFechaFra() {
        return efecobFechaFra;
    }

    public void setEfecobFechaFra(BigInteger efecobFechaFra) {
        BigInteger oldEfecobFechaFra = this.efecobFechaFra;
        this.efecobFechaFra = efecobFechaFra;
        changeSupport.firePropertyChange("efecobFechaFra", oldEfecobFechaFra, efecobFechaFra);
    }

    public BigInteger getEfecobRepre() {
        return efecobRepre;
    }

    public void setEfecobRepre(BigInteger efecobRepre) {
        BigInteger oldEfecobRepre = this.efecobRepre;
        this.efecobRepre = efecobRepre;
        changeSupport.firePropertyChange("efecobRepre", oldEfecobRepre, efecobRepre);
    }

    public BigInteger getEfecobReciboImpreso() {
        return efecobReciboImpreso;
    }

    public void setEfecobReciboImpreso(BigInteger efecobReciboImpreso) {
        BigInteger oldEfecobReciboImpreso = this.efecobReciboImpreso;
        this.efecobReciboImpreso = efecobReciboImpreso;
        changeSupport.firePropertyChange("efecobReciboImpreso", oldEfecobReciboImpreso, efecobReciboImpreso);
    }

    public BigInteger getEfecobOrigen() {
        return efecobOrigen;
    }

    public void setEfecobOrigen(BigInteger efecobOrigen) {
        BigInteger oldEfecobOrigen = this.efecobOrigen;
        this.efecobOrigen = efecobOrigen;
        changeSupport.firePropertyChange("efecobOrigen", oldEfecobOrigen, efecobOrigen);
    }

    public String getEfecobFiller() {
        return efecobFiller;
    }

    public void setEfecobFiller(String efecobFiller) {
        String oldEfecobFiller = this.efecobFiller;
        this.efecobFiller = efecobFiller;
        changeSupport.firePropertyChange("efecobFiller", oldEfecobFiller, efecobFiller);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (efecobPK != null ? efecobPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Efecob)) {
            return false;
        }
        Efecob other = (Efecob) object;
        if ((this.efecobPK == null && other.efecobPK != null) || (this.efecobPK != null && !this.efecobPK.equals(other.efecobPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "procesos.Efecob[ efecobPK=" + efecobPK + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
