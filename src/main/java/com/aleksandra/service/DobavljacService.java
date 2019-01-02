/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IDobavljacDAORep;
import com.aleksandra.dao.IPrijemnicaDAORep;
import com.aleksandra.domen.Dobavljac;
import com.aleksandra.domen.Prijemnica;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("dobavljacService")
public class DobavljacService implements IDobavljacService {

    @Autowired
    private IDobavljacDAORep dobavljacDAO;
    @Autowired
    private IPrijemnicaDAORep prijemnicaDAO;

    @Override
    public List<Dobavljac> ucitajDobavljace() throws Exception {
        System.out.println("dobavljacDAO find all");
        System.out.println(dobavljacDAO.findAll().size() + " duzinaaaaa");
        return dobavljacDAO.findAll();
    }

    @Override
    public void dodajDobavljac(Dobavljac dobavljac) throws Exception {
        dobavljacDAO.save(dobavljac);
    }

    @Override
    public void obrisiDobavljaca(String dobavljacID) throws Exception {
        dobavljacDAO.delete(dobavljacID);
    }

    @Override
    public Dobavljac pronadjiDobavljaca(String dobavljacID) throws Exception {
        return dobavljacDAO.findOne(dobavljacID);
    }

    @Override
    public void zapamtiDobavljaca(Dobavljac dobavljac) throws Exception {
        dobavljacDAO.save(dobavljac);
    }
}
