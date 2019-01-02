/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.domen.Vagarskapotvrda;
import java.util.List;

/**
 *
 * @author User
 */
public interface IVagarskaPotvrdaService {

    public void dodajVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception;

    public List<Vagarskapotvrda> ucitajVagarskePotvrde() throws Exception;

    public Vagarskapotvrda pronadjiVagarskuPotvrdu(int vagarskaPotvrdaID) throws Exception;

    public void zapamtiVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception;

    public List<Vagarskapotvrda> pronadjiMoguceVagarskePotvrde() throws Exception;

}
