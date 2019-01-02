/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author User
 */
@Entity
@Table(name = "prijemnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prijemnica.findAll", query = "SELECT p FROM Prijemnica p")
    , @NamedQuery(name = "Prijemnica.findByBrojPrijemnice", query = "SELECT p FROM Prijemnica p WHERE p.brojPrijemnice = :brojPrijemnice")
    , @NamedQuery(name = "Prijemnica.findByDatum", query = "SELECT p FROM Prijemnica p WHERE p.datum = :datum")
    , @NamedQuery(name = "Prijemnica.getNumber", query = "SELECT max(p.brojPrijemnice) FROM Prijemnica p")
    , @NamedQuery(name = "Prijemnica.findByUkupno", query = "SELECT p FROM Prijemnica p WHERE p.ukupno = :ukupno")})
public class Prijemnica implements Serializable {

    @Column(name = "datumUnosa")
    @Temporal(TemporalType.DATE)
    private Date datumUnosa;

    @Column(name = "ukupnoSaPDV")
    private Double ukupnoSaPDV;
    @Column(name = "ukupanPDV")
    private Double ukupanPDV;
    @JoinColumn(name = "PIB", referencedColumnName = "PIB")
    @ManyToOne
    private Dobavljac pib;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojPrijemnice")
    private Integer brojPrijemnice;
    @Column(name = "datum")
    @DateTimeFormat(pattern = "yy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date datum;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ukupno")
    private Double ukupno;
    @JoinColumn(name = "jmbg", referencedColumnName = "jmbg")
    @ManyToOne
    @JsonIgnore
    private Zaposleni jmbg;
    @JoinColumn(name = "brojVagarskePotvrde", referencedColumnName = "brojVagarskePotvrde")
    @OneToOne
    @JsonIgnore
    private Vagarskapotvrda brojVagarskePotvrde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prijemnica", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Stavkaprijemnice> stavkaprijemniceCollection;
    @Transient
    private String datumS;

   
    
    public Prijemnica() {
        datumUnosa = new Date();
    }

    public Prijemnica(Integer brojPrijemnice) {
        this.brojPrijemnice = brojPrijemnice;
    }

    public Integer getBrojPrijemnice() {
        return brojPrijemnice;
    }

    public String getDatumS() {
        return datumS;
    }

    public void setDatumS(String datumS) {
        this.datumS = datumS;
    }

    public void setBrojPrijemnice(Integer brojPrijemnice) {
        this.brojPrijemnice = brojPrijemnice;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getUkupno() {
        return ukupno;
    }

    public void setUkupno(Double ukupno) {
        this.ukupno = ukupno;
    }

    public Zaposleni getJmbg() {
        return jmbg;
    }

    public void setJmbg(Zaposleni jmbg) {
        this.jmbg = jmbg;
    }

    public Vagarskapotvrda getBrojVagarskePotvrde() {
        return brojVagarskePotvrde;
    }

    public void setBrojVagarskePotvrde(Vagarskapotvrda brojVagarskePotvrde) {
        this.brojVagarskePotvrde = brojVagarskePotvrde;
    }

    @XmlTransient
    @JsonIgnore
    public List<Stavkaprijemnice> getStavkaprijemniceCollection() {
        return stavkaprijemniceCollection;
    }

    public void setStavkaprijemniceCollection(List<Stavkaprijemnice> stavkaprijemniceCollection) {
        this.stavkaprijemniceCollection = stavkaprijemniceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojPrijemnice != null ? brojPrijemnice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prijemnica)) {
            return false;
        }
        Prijemnica other = (Prijemnica) object;
        if ((this.brojPrijemnice == null && other.brojPrijemnice != null) || (this.brojPrijemnice != null && !this.brojPrijemnice.equals(other.brojPrijemnice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.Prijemnica[ brojPrijemnice=" + brojPrijemnice + " ]";
    }

    public Double getUkupnoSaPDV() {
        return ukupnoSaPDV;
    }

    public void setUkupnoSaPDV(Double ukupnoSaPDV) {
        this.ukupnoSaPDV = ukupnoSaPDV;
    }

    public Double getUkupanPDV() {
        return ukupanPDV;
    }

    public void setUkupanPDV(Double ukupanPDV) {
        this.ukupanPDV = ukupanPDV;
    }

    public Dobavljac getPib() {
        return pib;
    }

    public void setPib(Dobavljac pib) {
        this.pib = pib;
    }

    public Date getDatumUnosa() {
        return datumUnosa;
    }

    public void setDatumUnosa(Date datumUnosa) {
        this.datumUnosa = datumUnosa;
    }

}
