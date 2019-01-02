/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author User
 */
@Entity
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z")
    , @NamedQuery(name = "Zaposleni.findByJmbg", query = "SELECT z FROM Zaposleni z WHERE z.jmbg = :jmbg")
    , @NamedQuery(name = "Zaposleni.findByIme", query = "SELECT z FROM Zaposleni z WHERE z.ime = :ime")
    , @NamedQuery(name = "Zaposleni.findByPrezime", query = "SELECT z FROM Zaposleni z WHERE z.prezime = :prezime")
    , @NamedQuery(name = "Zaposleni.findByPozicija", query = "SELECT z FROM Zaposleni z WHERE z.pozicija = :pozicija")
    , @NamedQuery(name = "Zaposleni.findByLozinka", query = "SELECT z FROM Zaposleni z WHERE z.lozinka = :lozinka")
    ,@NamedQuery(name = "Zaposleni.findByLogin", query = "SELECT z FROM Zaposleni z WHERE z.lozinka = :lozinka and z.korisnickoIme = :korisnickoIme")
    , @NamedQuery(name = "Zaposleni.findByKorisnickoIme", query = "SELECT z FROM Zaposleni z WHERE z.korisnickoIme = :korisnickoIme")})
public class Zaposleni implements Serializable {

    @OneToMany(mappedBy = "jmbg")
    private Collection<Vagarskapotvrda> vagarskapotvrdaCollection;
    @OneToMany(mappedBy = "jmbg")
    private Collection<Prijemnica> prijemnicaCollection;


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "jmbg")
    private String jmbg;
    @Size(max = 30)
    @Column(name = "ime")
    private String ime;
    @Size(max = 30)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 20)
    @Column(name = "pozicija")
    private String pozicija;
    @Size(max = 30)
    @Column(name = "lozinka")
    private String lozinka;
    @Size(max = 30)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;

    public Zaposleni() {
    }

    public Zaposleni(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jmbg != null ? jmbg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

 

    @XmlTransient
    @JsonIgnore
    public Collection<Vagarskapotvrda> getVagarskapotvrdaCollection() {
        return vagarskapotvrdaCollection;
    }

    public void setVagarskapotvrdaCollection(Collection<Vagarskapotvrda> vagarskapotvrdaCollection) {
        this.vagarskapotvrdaCollection = vagarskapotvrdaCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prijemnica> getPrijemnicaCollection() {
        return prijemnicaCollection;
    }

    public void setPrijemnicaCollection(Collection<Prijemnica> prijemnicaCollection) {
        this.prijemnicaCollection = prijemnicaCollection;
    }

}
