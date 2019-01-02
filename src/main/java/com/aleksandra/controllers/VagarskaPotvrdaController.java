/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.dao.IPrijemnicaDAORep;
import com.aleksandra.domen.Vagarskapotvrda;
import com.aleksandra.service.IPrijemnicaService;
import com.aleksandra.service.IVagarskaPotvrdaService;
import com.aleksandra.service.VagarskaPotvrdaService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("weight_certificate")
public class VagarskaPotvrdaController {

    @Autowired
    private IVagarskaPotvrdaService vagarskaPotvrdaService;
    @Autowired
    private IPrijemnicaService prijemnicaService;

    
    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vagarskapotvrda> combobox(ModelMap model) {

        List<Vagarskapotvrda> vagarskaPotvrdaLista = new ArrayList<>();
        try {
            vagarskaPotvrdaLista = vagarskaPotvrdaService.ucitajVagarskePotvrde();
        } catch (Exception ex) {
            Logger.getLogger(VagarskaPotvrdaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vagarskaPotvrdaLista;
    }
    
        @RequestMapping(value = "/find_weight_certificate/{id}", method = RequestMethod.GET)
    public ModelAndView find_goods_received_note(@PathVariable int id) {
        Vagarskapotvrda vp = new Vagarskapotvrda();
        String bojPrijemnice = prijemnicaService.postojiPrijemnicaVP(id);
        try {
            vp = vagarskaPotvrdaService.pronadjiVagarskuPotvrdu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("find_weight_certificate", "cw", vp);
        mv.addObject("brojPrijemnice", String.valueOf(bojPrijemnice));
        return mv;
    }

    @RequestMapping("/all_weight_certificate")
    public ModelAndView all_weight_certificate() {
        List<Vagarskapotvrda> vagarskaPotvrdaLista = new ArrayList<>();
        try {
            vagarskaPotvrdaLista = vagarskaPotvrdaService.ucitajVagarskePotvrde();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_weight_certificate");
        mv.addObject("listaVagarskihPotvrda", vagarskaPotvrdaLista);
        return mv;
    }

}
