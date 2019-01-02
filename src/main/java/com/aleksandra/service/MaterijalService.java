/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IMaterijalDAORep;
import com.aleksandra.dao.IPrijemnicaDAORep;
import com.aleksandra.domen.Materijal;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Stavkaprijemnice;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("materijalService")
public class MaterijalService implements IMaterijalService {

    @Autowired
    private IMaterijalDAORep materijalDAO;
    @Autowired
    private IPrijemnicaDAORep prijemnicaDAO;

    @Override
    public void dodajMaterijal(Materijal materijal) throws Exception {
        materijalDAO.save(materijal);
    }

    @Override
    public void obrisiMaterijal(String materijalID) throws Exception {
        System.out.println("obrisi materijalaaaaaaaaaaaaaaaaaaaaaaal ");
        List<Prijemnica> prijemnice = prijemnicaDAO.findAll();
        List<Prijemnica> obrisiPrijem = new ArrayList<Prijemnica>();
        for (Prijemnica prijemnica : prijemnice) {
            System.out.println("brisanje prijemniceeeeee id mat: " + materijalID);
            for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
                System.out.println("aleksandra3333");
                System.out.println("sifra materijala stavke "+stavkaprijemnice.getSifraMaterijala().getSifraMaterijala());
                if (stavkaprijemnice.getSifraMaterijala().getSifraMaterijala().equals(materijalID)) {
                    System.out.println("aleksandra111");
                    prijemnicaDAO.delete(prijemnica.getBrojPrijemnice());
                    break;
                }
            }
        }
        System.out.println("brisanje materijalaaaa");
        materijalDAO.delete(materijalID);
    }

    @Override
    public List<Materijal> ucitajMaterijale() throws Exception {
        return materijalDAO.findAll();
    }

    @Override
    public Materijal pronadjiMaterijal(String materijalID) throws Exception {
        System.out.println(" id " + materijalID);
        return materijalDAO.findOne(materijalID);
    }

    @Override
    public void zapamtiMaterijal(Materijal materijal) throws Exception {
        materijalDAO.save(materijal);
    }

}
