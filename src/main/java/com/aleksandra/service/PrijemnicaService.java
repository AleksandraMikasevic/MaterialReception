/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IPrijemnicaDAORep;
import com.aleksandra.domen.Prijemnica;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("prijemnicaService")
public class PrijemnicaService implements IPrijemnicaService {

    @Autowired
    private IPrijemnicaDAORep prijemnicaDAO;

    @Override
    public void dodajPrijemnicu(Prijemnica prijemnica) throws Exception {
        prijemnica.setDatumUnosa(new Date());
        prijemnicaDAO.save(prijemnica);
    }

    @Override
    public void obrisiPrijemnicu(int prijemnicaID) throws Exception {
        prijemnicaDAO.delete(prijemnicaID);
    }

    @Override
    public List<Prijemnica> ucitajPrijemnice() throws Exception {
        return prijemnicaDAO.findAll();
    }

    @Override
    public Prijemnica pronadjiPrijemnicu(int prijemnicaID) throws Exception {
        return prijemnicaDAO.findOne(prijemnicaID);
    }

    @Override
    public void zapamtiPrijemnicu(Prijemnica prijemnica) throws Exception {
        prijemnicaDAO.save(prijemnica);
    }

    @Override
    public int vratiBrojPrijemnice() throws Exception {
        List<Prijemnica> prijemnice = prijemnicaDAO.findAll();
        if (prijemnice.isEmpty()) {
            return 1;
        }
        return prijemnicaDAO.vratiBrojPrijemnice()+1;
    }
    

    @Override
    public String postojiPrijemnicaVP(int id) {
        Prijemnica prijemnica = prijemnicaDAO.postojiPrijemnica(id);
        if(prijemnica == null)
            return "";
        else 
            return prijemnica.getBrojPrijemnice().toString();
    }
    

}
