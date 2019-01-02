/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.domen.Prijemnica;
import java.util.List;

/**
 *
 * @author User
 */
public interface IPrijemnicaService {

    public void dodajPrijemnicu(Prijemnica prijemnica) throws Exception;

    public void obrisiPrijemnicu(int prijemnicaID) throws Exception;

    public List<Prijemnica> ucitajPrijemnice() throws Exception;

    public Prijemnica pronadjiPrijemnicu(int prijemnicaID) throws Exception;

    public void zapamtiPrijemnicu(Prijemnica prijemnica) throws Exception;

    public int vratiBrojPrijemnice() throws Exception;

    public String postojiPrijemnicaVP(int id);
}
