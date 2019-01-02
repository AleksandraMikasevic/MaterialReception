/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;

/**
 *
 * @author User
 */
@Entity
@Table(name = "dobavljac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dobavljac.findAll", query = "SELECT d FROM Dobavljac d")
    , @NamedQuery(name = "Dobavljac.findByPib", query = "SELECT d FROM Dobavljac d WHERE d.pib = :pib")
    , @NamedQuery(name = "Dobavljac.findBySediste", query = "SELECT d FROM Dobavljac d WHERE d.sediste = :sediste")
    , @NamedQuery(name = "Dobavljac.findByNaziv", query = "SELECT d FROM Dobavljac d WHERE d.naziv = :naziv")})
public class Dobavljac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PIB")
    private String pib;
    @Size(max = 30)
    @Column(name = "sediste")
    private String sediste;
    @Size(max = 30)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "pib", cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Prijemnica> prijemnicaCollection;

    public Dobavljac() {
    }

    public Dobavljac(String pib) {
        this.pib = pib;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getSediste() {
        return sediste;
    }

    public void setSediste(String sediste) {
        this.sediste = sediste;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prijemnica> getPrijemnicaCollection() {
        return prijemnicaCollection;
    }

    public void setPrijemnicaCollection(Collection<Prijemnica> prijemnicaCollection) {
        this.prijemnicaCollection = prijemnicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pib != null ? pib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dobavljac)) {
            return false;
        }
        Dobavljac other = (Dobavljac) object;
        if ((this.pib == null && other.pib != null) || (this.pib != null && !this.pib.equals(other.pib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.Dobavljac[ pib=" + pib + " ]";
    }
    
}
