/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Vagarskapotvrda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository("vagarskaPotvrdaDAORep")
public interface IVagarskaPotvrdaDAORep extends JpaRepository<Vagarskapotvrda, Integer>{
    
}
