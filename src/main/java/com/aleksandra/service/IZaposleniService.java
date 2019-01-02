/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.domen.Zaposleni;
import java.util.List;

/**
 *
 * @author User
 */
public interface IZaposleniService {

    public List<Zaposleni> ucitajZaposlene() throws Exception;
    public Zaposleni proveriZaposlenog(String username, String password) throws Exception;
    public Zaposleni pronadjiZaposlenog(String username) throws Exception;

}
