/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.Zaposleni;
import com.aleksandra.service.IZaposleniService;
import com.aleksandra.service.ZaposleniService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class IndexController {
@Autowired 
private IZaposleniService zaposleniService;
    
    @RequestMapping(value = "/")
    public ModelAndView index(HttpSession session) {
        System.out.println("POCELO");
        ModelAndView mv = new ModelAndView("main");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        System.out.println("SANDRAAAA");
        System.out.println(name+" IME ANKA");
        Zaposleni zaposleni = new Zaposleni();
        try {
            zaposleni = zaposleniService.pronadjiZaposlenog(name);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ime zaposlenog: "+zaposleni.getIme());
        session.setAttribute("zaposleni", zaposleni);
        return mv;
    }

    @RequestMapping(value = "/index")
    public ModelAndView logout() {
        System.out.println("POCELO");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
