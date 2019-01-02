/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "vagarskapotvrda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vagarskapotvrda.findAll", query = "SELECT v FROM Vagarskapotvrda v")
    , @NamedQuery(name = "Vagarskapotvrda.findByBrojVagarskePotvrde", query = "SELECT v FROM Vagarskapotvrda v WHERE v.brojVagarskePotvrde = :brojVagarskePotvrde")
    , @NamedQuery(name = "Vagarskapotvrda.findByDatum", query = "SELECT v FROM Vagarskapotvrda v WHERE v.datum = :datum")
    , @NamedQuery(name = "Vagarskapotvrda.findByUkupnaMasa", query = "SELECT v FROM Vagarskapotvrda v WHERE v.ukupnaMasa = :ukupnaMasa")})
public class Vagarskapotvrda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojVagarskePotvrde")
    private Integer brojVagarskePotvrde;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ukupnaMasa")
    private Double ukupnaMasa;
    @JsonIgnore
    @JoinColumn(name = "jmbg", referencedColumnName = "jmbg")
    @ManyToOne
    private Zaposleni jmbg;
   /* @OneToMany(mappedBy = "brojVagarskePotvrde")
    @JsonIgnore
    private Collection<Prijemnica> prijemnicaCollection;*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vagarskapotvrda", fetch = FetchType.EAGER)
    @JsonIgnore
    private Collection<Stavkavagarskepotvrde> stavkavagarskepotvrdeCollection;

    public Vagarskapotvrda() {
    }

    public Vagarskapotvrda(Integer brojVagarskePotvrde) {
        this.brojVagarskePotvrde = brojVagarskePotvrde;
    }

    public Integer getBrojVagarskePotvrde() {
        return brojVagarskePotvrde;
    }

    public void setBrojVagarskePotvrde(Integer brojVagarskePotvrde) {
        this.brojVagarskePotvrde = brojVagarskePotvrde;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getUkupnaMasa() {
        return ukupnaMasa;
    }

    public void setUkupnaMasa(Double ukupnaMasa) {
        this.ukupnaMasa = ukupnaMasa;
    }

    public Zaposleni getJmbg() {
        return jmbg;
    }

    public void setJmbg(Zaposleni jmbg) {
        this.jmbg = jmbg;
    }
/*
    @XmlTransient
    @JsonIgnore
    public Collection<Prijemnica> getPrijemnicaCollection() {
        return prijemnicaCollection;
    }

    public void setPrijemnicaCollection(Collection<Prijemnica> prijemnicaCollection) {
        this.prijemnicaCollection = prijemnicaCollection;
    }
*/
    @XmlTransient
    @JsonIgnore
    public Collection<Stavkavagarskepotvrde> getStavkavagarskepotvrdeCollection() {
        return stavkavagarskepotvrdeCollection;
    }

    public void setStavkavagarskepotvrdeCollection(Collection<Stavkavagarskepotvrde> stavkavagarskepotvrdeCollection) {
        this.stavkavagarskepotvrdeCollection = stavkavagarskepotvrdeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojVagarskePotvrde != null ? brojVagarskePotvrde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vagarskapotvrda)) {
            return false;
        }
        Vagarskapotvrda other = (Vagarskapotvrda) object;
        if ((this.brojVagarskePotvrde == null && other.brojVagarskePotvrde != null) || (this.brojVagarskePotvrde != null && !this.brojVagarskePotvrde.equals(other.brojVagarskePotvrde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return brojVagarskePotvrde + "";
    }

}
