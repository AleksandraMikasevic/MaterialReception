/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IZaposleniDAORep;
import com.aleksandra.domen.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("zaposleniService")
public class ZaposleniService implements IZaposleniService {

    @Autowired
    private IZaposleniDAORep zaposleniDAO;

    @Override
    public List<Zaposleni> ucitajZaposlene() throws Exception {
        List<Zaposleni> zaposleni = zaposleniDAO.findAll();
        return zaposleni;
    }

    @Override
    public Zaposleni proveriZaposlenog(String username, String password) throws Exception {
//        List<Zaposleni> zaposleni = zaposleniDAO.fi(username, password);
        List<Zaposleni> zaposleni = new ArrayList<>();
        if (zaposleni.size() == 0) {
            throw new Exception("Pogresno ime i/ili lozinka.");
        }
        return zaposleni.get(0);
    }

    @Override
    public Zaposleni pronadjiZaposlenog(String username) throws Exception {
        return zaposleniDAO.findByKorisnickoIme(username);
    }
}
