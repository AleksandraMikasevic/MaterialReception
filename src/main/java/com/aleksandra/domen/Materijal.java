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
import javax.persistence.Transient;
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
@Table(name = "materijal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materijal.findAll", query = "SELECT m FROM Materijal m")
    , @NamedQuery(name = "Materijal.findBySifraMaterijala", query = "SELECT m FROM Materijal m WHERE m.sifraMaterijala = :sifraMaterijala")
    , @NamedQuery(name = "Materijal.findByNazivMaterijala", query = "SELECT m FROM Materijal m WHERE m.nazivMaterijala = :nazivMaterijala")
    , @NamedQuery(name = "Materijal.findByJedinicaMere", query = "SELECT m FROM Materijal m WHERE m.jedinicaMere = :jedinicaMere")
    , @NamedQuery(name = "Materijal.findByPdv", query = "SELECT m FROM Materijal m WHERE m.pdv = :pdv")
    , @NamedQuery(name = "Materijal.findByCena", query = "SELECT m FROM Materijal m WHERE m.cena = :cena")})
public class Materijal implements Serializable {

    @Size(max = 255)
    @Column(name = "opis")
    private String opis;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "sifraMaterijala")
    private String sifraMaterijala;
    @Size(max = 30)
    @Column(name = "nazivMaterijala")
    private String nazivMaterijala;
    @Size(max = 30)
    @Column(name = "jedinicaMere")
    private String jedinicaMere;
    @Column(name = "pdv")
    private Integer pdv;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;
    @OneToMany(mappedBy = "sifraMaterijala")
    private Collection<Stavkaprijemnice> stavkaprijemniceCollection;
    @Transient
    private JedinicaMere jm;

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public Materijal() {
    }

    public Materijal(String sifraMaterijala) {
        this.sifraMaterijala = sifraMaterijala;
    }

    public String getSifraMaterijala() {
        return sifraMaterijala;
    }

    public void setSifraMaterijala(String sifraMaterijala) {
        this.sifraMaterijala = sifraMaterijala;
    }

    public String getNazivMaterijala() {
        return nazivMaterijala;
    }

    public void setNazivMaterijala(String nazivMaterijala) {
        this.nazivMaterijala = nazivMaterijala;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public Integer getPdv() {
        return pdv;
    }

    public void setPdv(Integer pdv) {
        this.pdv = pdv;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Stavkaprijemnice> getStavkaprijemniceCollection() {
        return stavkaprijemniceCollection;
    }

    public void setStavkaprijemniceCollection(Collection<Stavkaprijemnice> stavkaprijemniceCollection) {
        this.stavkaprijemniceCollection = stavkaprijemniceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraMaterijala != null ? sifraMaterijala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materijal)) {
            return false;
        }
        Materijal other = (Materijal) object;
        if ((this.sifraMaterijala == null && other.sifraMaterijala != null) || (this.sifraMaterijala != null && !this.sifraMaterijala.equals(other.sifraMaterijala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivMaterijala;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}
