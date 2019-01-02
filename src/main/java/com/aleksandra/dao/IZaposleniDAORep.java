/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Zaposleni;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository("zaposleniDAORep")
public interface IZaposleniDAORep extends JpaRepository<Zaposleni, String>{
    @Query("SELECT z FROM Zaposleni z WHERE z.korisnickoIme = ?1")
    Zaposleni findByKorisnickoIme(String korisnickoIme);
}
