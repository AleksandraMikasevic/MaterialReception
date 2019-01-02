/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.IPrijemnicaDAORep;
import com.aleksandra.dao.IVagarskaPotvrdaDAORep;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Vagarskapotvrda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("vagarskaPotvrdaService")
public class VagarskaPotvrdaService implements IVagarskaPotvrdaService {

    @Autowired
    private IVagarskaPotvrdaDAORep vagarskaPotvrdaDAO;
    @Autowired
    private IPrijemnicaDAORep prijemnicaDAO;
    
    @Override
    public void dodajVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception {
        vagarskaPotvrdaDAO.save(vagarskaPotvrda);
    }

    @Override
    public List<Vagarskapotvrda> ucitajVagarskePotvrde() throws Exception {
        return vagarskaPotvrdaDAO.findAll();
    }

    @Override
    public Vagarskapotvrda pronadjiVagarskuPotvrdu(int vagarskaPotvrdaID) throws Exception {
        return vagarskaPotvrdaDAO.findOne(vagarskaPotvrdaID);
    }

    @Override
    public void zapamtiVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception {
        vagarskaPotvrdaDAO.save(vagarskaPotvrda);
    }

    @Override
    public List<Vagarskapotvrda> pronadjiMoguceVagarskePotvrde() throws Exception {
        List<Vagarskapotvrda> vagarskePotvrde = vagarskaPotvrdaDAO.findAll();
        List<Vagarskapotvrda> vagarskePotvrdeNeMoguce = new ArrayList<>();
        List<Prijemnica> prijemnice = prijemnicaDAO.findAll();
        for (Vagarskapotvrda vagarskapotvrda : vagarskePotvrde) {
            for (Prijemnica prijemnica : prijemnice) {
                if (vagarskapotvrda.getBrojVagarskePotvrde() == prijemnica.getBrojVagarskePotvrde().getBrojVagarskePotvrde()) {
                    vagarskePotvrdeNeMoguce.add(vagarskapotvrda);
                }
            }
        }
        vagarskePotvrde.removeAll(vagarskePotvrdeNeMoguce);
        return vagarskePotvrde;
    }

}
