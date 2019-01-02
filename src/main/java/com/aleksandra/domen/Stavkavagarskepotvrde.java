/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "stavkavagarskepotvrde")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavkavagarskepotvrde.findAll", query = "SELECT s FROM Stavkavagarskepotvrde s")
    , @NamedQuery(name = "Stavkavagarskepotvrde.findByBrojVagarskePotvrde", query = "SELECT s FROM Stavkavagarskepotvrde s WHERE s.stavkavagarskepotvrdePK.brojVagarskePotvrde = :brojVagarskePotvrde")
    , @NamedQuery(name = "Stavkavagarskepotvrde.findByBrojStavke", query = "SELECT s FROM Stavkavagarskepotvrde s WHERE s.stavkavagarskepotvrdePK.brojStavke = :brojStavke")
    , @NamedQuery(name = "Stavkavagarskepotvrde.findByRedniBroj", query = "SELECT s FROM Stavkavagarskepotvrde s WHERE s.redniBroj = :redniBroj")
    , @NamedQuery(name = "Stavkavagarskepotvrde.findByBrutoMasa", query = "SELECT s FROM Stavkavagarskepotvrde s WHERE s.brutoMasa = :brutoMasa")
    , @NamedQuery(name = "Stavkavagarskepotvrde.findByNetoMasa", query = "SELECT s FROM Stavkavagarskepotvrde s WHERE s.netoMasa = :netoMasa")})
public class Stavkavagarskepotvrde implements Serializable {

    @Size(max = 50)
    @Column(name = "registarskiBrojVozila")
    private String registarskiBrojVozila;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkavagarskepotvrdePK stavkavagarskepotvrdePK;
    @Column(name = "redniBroj")
    private Integer redniBroj;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "brutoMasa")
    private Double brutoMasa;
    @Column(name = "netoMasa")
    private Double netoMasa;
    @JoinColumn(name = "brojVagarskePotvrde", referencedColumnName = "brojVagarskePotvrde", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Vagarskapotvrda vagarskapotvrda;

    public Stavkavagarskepotvrde() {
    }

    public Stavkavagarskepotvrde(StavkavagarskepotvrdePK stavkavagarskepotvrdePK) {
        this.stavkavagarskepotvrdePK = stavkavagarskepotvrdePK;
    }

    public Stavkavagarskepotvrde(int brojVagarskePotvrde, int brojStavke) {
        this.stavkavagarskepotvrdePK = new StavkavagarskepotvrdePK(brojVagarskePotvrde, brojStavke);
    }

    public StavkavagarskepotvrdePK getStavkavagarskepotvrdePK() {
        return stavkavagarskepotvrdePK;
    }

    public void setStavkavagarskepotvrdePK(StavkavagarskepotvrdePK stavkavagarskepotvrdePK) {
        this.stavkavagarskepotvrdePK = stavkavagarskepotvrdePK;
    }

    public Integer getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Integer redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Double getBrutoMasa() {
        return brutoMasa;
    }

    public void setBrutoMasa(Double brutoMasa) {
        this.brutoMasa = brutoMasa;
    }

    public Double getNetoMasa() {
        return netoMasa;
    }

    public void setNetoMasa(Double netoMasa) {
        this.netoMasa = netoMasa;
    }

    public Vagarskapotvrda getVagarskapotvrda() {
        return vagarskapotvrda;
    }

    public void setVagarskapotvrda(Vagarskapotvrda vagarskapotvrda) {
        this.vagarskapotvrda = vagarskapotvrda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkavagarskepotvrdePK != null ? stavkavagarskepotvrdePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavkavagarskepotvrde)) {
            return false;
        }
        Stavkavagarskepotvrde other = (Stavkavagarskepotvrde) object;
        if ((this.stavkavagarskepotvrdePK == null && other.stavkavagarskepotvrdePK != null) || (this.stavkavagarskepotvrdePK != null && !this.stavkavagarskepotvrdePK.equals(other.stavkavagarskepotvrdePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.Stavkavagarskepotvrde[ stavkavagarskepotvrdePK=" + stavkavagarskepotvrdePK + " ]";
    }

    public String getRegistarskiBrojVozila() {
        return registarskiBrojVozila;
    }

    public void setRegistarskiBrojVozila(String registarskiBrojVozila) {
        this.registarskiBrojVozila = registarskiBrojVozila;
    }

}
