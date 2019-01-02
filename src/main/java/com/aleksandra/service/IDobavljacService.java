/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.domen.Dobavljac;
import java.util.List;

/**
 *
 * @author User
 */
public interface IDobavljacService {

    public void dodajDobavljac(Dobavljac dobavljac) throws Exception;

    public void obrisiDobavljaca(String dobavljacID) throws Exception;

    public List<Dobavljac> ucitajDobavljace() throws Exception;

    public Dobavljac pronadjiDobavljaca(String dobavljacID) throws Exception;

    public void zapamtiDobavljaca(Dobavljac dobavljac) throws Exception;

}
