/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Txus
 */
@Embeddable
public class EfecobPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "EFECOB_VENCIM")
    private long efecobVencim;
    @Basic(optional = false)
    @Column(name = "EFECOB_EFECTO")
    private long efecobEfecto;

    public EfecobPK() {
    }

    public EfecobPK(String empresa, long efecobVencim, long efecobEfecto) {
        this.empresa = empresa;
        this.efecobVencim = efecobVencim;
        this.efecobEfecto = efecobEfecto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public long getEfecobVencim() {
        return efecobVencim;
    }

    public void setEfecobVencim(long efecobVencim) {
        this.efecobVencim = efecobVencim;
    }

    public long getEfecobEfecto() {
        return efecobEfecto;
    }

    public void setEfecobEfecto(long efecobEfecto) {
        this.efecobEfecto = efecobEfecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresa != null ? empresa.hashCode() : 0);
        hash += (int) efecobVencim;
        hash += (int) efecobEfecto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EfecobPK)) {
            return false;
        }
        EfecobPK other = (EfecobPK) object;
        if ((this.empresa == null && other.empresa != null) || (this.empresa != null && !this.empresa.equals(other.empresa))) {
            return false;
        }
        if (this.efecobVencim != other.efecobVencim) {
            return false;
        }
        if (this.efecobEfecto != other.efecobEfecto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "procesos.EfecobPK[ empresa=" + empresa + ", efecobVencim=" + efecobVencim + ", efecobEfecto=" + efecobEfecto + " ]";
    }
    
}
