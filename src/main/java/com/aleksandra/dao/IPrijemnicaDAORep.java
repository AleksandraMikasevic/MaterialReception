/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Prijemnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository("prijemnicaDAORep")
public interface IPrijemnicaDAORep extends JpaRepository<Prijemnica, Integer>{
   @Query("SELECT max(p.brojPrijemnice) FROM Prijemnica p")
    int vratiBrojPrijemnice();
    
    @Query("SELECT p FROM Prijemnica p WHERE p.brojVagarskePotvrde.brojVagarskePotvrde = ?1")
    Prijemnica postojiPrijemnica(int id);

}
