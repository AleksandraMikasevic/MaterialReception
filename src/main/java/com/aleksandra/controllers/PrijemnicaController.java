/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.Dobavljac;
import com.aleksandra.domen.Materijal;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Stavkaprijemnice;
import com.aleksandra.domen.StavkaprijemnicePK;
import com.aleksandra.domen.Vagarskapotvrda;
import com.aleksandra.domen.Zaposleni;
import com.aleksandra.service.DobavljacService;
import com.aleksandra.service.IDobavljacService;
import com.aleksandra.service.IMaterijalService;
import com.aleksandra.service.IPrijemnicaService;
import com.aleksandra.service.IVagarskaPotvrdaService;
import com.aleksandra.service.IZaposleniService;
import com.aleksandra.service.MaterijalService;
import com.aleksandra.service.PrijemnicaService;
import com.aleksandra.service.VagarskaPotvrdaService;
import com.aleksandra.service.ZaposleniService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/goods_received_note")
@Scope("session")
public class PrijemnicaController {

    Prijemnica prijemnica = new Prijemnica();

    @Autowired
    private IPrijemnicaService prijemnicaService;
    @Autowired
    private IMaterijalService materijalService;
    @Autowired
    private IVagarskaPotvrdaService vagarskaPotvrdaService;
    @Autowired
    private IDobavljacService dobavljacService;
    @Autowired
    private IZaposleniService zaposleniService;

    @RequestMapping("/all_goods_received_notes")
    public ModelAndView all_goods_received_notes() {

        prijemnica = new Prijemnica();
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_goods_received_notes");
        mv.addObject("listaPrijemnica", prijemnice);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_goods_received_note_json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prijemnica> all_goods_received_notes_json() {
        prijemnica = new Prijemnica();
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prijemnice;
    }

    @ResponseBody
    @RequestMapping(value = "/items_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stavkaprijemnice> items_json() {
        System.out.println("vraca collection ///////////////////");
        return prijemnica.getStavkaprijemniceCollection();
    }

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prijemnica> mat_json() {
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prijemnice;
    }

    @RequestMapping(value = "/add_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView add_goods_received_note_get(@PathVariable int id) {
        prijemnica = new Prijemnica();
        ModelAndView mv = new ModelAndView("add_goods_received_note");
        try {
            int brojPrijemnice = prijemnicaService.vratiBrojPrijemnice();
            System.out.println(brojPrijemnice + "  -----------------------------------broj prijemnice");
            prijemnica.setBrojPrijemnice(brojPrijemnice);
            Vagarskapotvrda vagarskapotvrda = vagarskaPotvrdaService.pronadjiVagarskuPotvrdu(id);
            prijemnica.setBrojVagarskePotvrde(vagarskapotvrda);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("grcn", prijemnica);
        List<Vagarskapotvrda> listaVagarskihPotvrda = new ArrayList<>();
        List<Zaposleni> listaZaposlenih = new ArrayList<>();
        List<Dobavljac> listaDobavljaca = new ArrayList<>();
        try {
            listaVagarskihPotvrda = vagarskaPotvrdaService.pronadjiMoguceVagarskePotvrde();
            listaZaposlenih = zaposleniService.ucitajZaposlene();
            listaDobavljaca = dobavljacService.ucitajDobavljace();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaVagarskihPotvrda", listaVagarskihPotvrda);
        mv.addObject("listaZaposlenih", listaZaposlenih);
        mv.addObject("listaDobavljaca", listaDobavljaca);
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_note", method = RequestMethod.POST)
    public String add_goods_received_note(RedirectAttributes redirectAttributes) {
        izracunajUkupno();
        try {
            prijemnicaService.dodajPrijemnicu(prijemnica);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Nije moguce kreirati prijemnicu");
            return "redirect:/goods_received_note/add_goods_received_note";
        }
    }

    @RequestMapping(value = "/add_goods_received_note_info", method = RequestMethod.POST)
    public String add_goods_received_note_info(@ModelAttribute("grcn") Prijemnica prijemnicaM) {
        prijemnica.setDatum(prijemnicaM.getDatum());
        System.out.println(prijemnicaM.getDatum());
        prijemnica.setBrojVagarskePotvrde(prijemnicaM.getBrojVagarskePotvrde());
        prijemnica.setJmbg(prijemnicaM.getJmbg());
        prijemnica.setPib(prijemnicaM.getPib());
        return "redirect:/goods_received_note/add_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_note_info", method = RequestMethod.POST)
    public String change_goods_received_note_info(@ModelAttribute("grcn") Prijemnica prijemnicaM) {
        prijemnica.setDatum(prijemnicaM.getDatum());
        prijemnica.setBrojVagarskePotvrde(prijemnicaM.getBrojVagarskePotvrde());
        prijemnica.setJmbg(prijemnicaM.getJmbg());

        return "redirect:/goods_received_note/change_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_items", method = RequestMethod.GET)
    public ModelAndView change_goods_received_items() {
        ModelAndView mv = new ModelAndView("change_goods_received_items");
        mv.addObject("grcn", prijemnica);
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalService.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_items", method = RequestMethod.GET)
    public ModelAndView add_goods_received_items() {
        ModelAndView mv = new ModelAndView("add_goods_received_items");
        mv.addObject("grcn", prijemnica);
        prijemnica.setStavkaprijemniceCollection(new ArrayList<Stavkaprijemnice>());
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalService.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/add_item/{sifraMaterijala}/{kolicina}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Stavkaprijemnice add_item(@PathVariable String sifraMaterijala, @PathVariable double kolicina) {
        Stavkaprijemnice stavka = new Stavkaprijemnice();
        try {
            if (prijemnica.getStavkaprijemniceCollection().isEmpty()) {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), 1));
                stavka.setRedniBroj(1);
            } else {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), prijemnica.getStavkaprijemniceCollection().get(prijemnica.getStavkaprijemniceCollection().size() - 1).getStavkaprijemnicePK().getBrojStavke() + 1));
                stavka.setRedniBroj(prijemnica.getStavkaprijemniceCollection().size() + 1);
            }
            System.out.println("sifraMaterijala +  " + sifraMaterijala);
            stavka.setSifraMaterijala(materijalService.pronadjiMaterijal(sifraMaterijala));
            stavka.setPrijemnica(prijemnica);
            stavka.setKolicina(kolicina);
            prijemnica.getStavkaprijemniceCollection().add(stavka);
            System.out.println("add iteeeeeeeeeeeeeem");
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stavka;
    }

    @ResponseBody
    @RequestMapping(value = "/update_item/{rbr}/{sifraMaterijala}/{kolicina}", method = RequestMethod.GET)
    public String update_item(@PathVariable int rbr, @PathVariable String sifraMaterijala, @PathVariable double kolicina) {
        Stavkaprijemnice stv = new Stavkaprijemnice();
        stv.setKolicina(kolicina);
        stv.getSifraMaterijala().setSifraMaterijala(sifraMaterijala);
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == rbr) {
                stv = stavkaprijemnice;
                stavkaprijemnice.setKolicina(kolicina);
                Materijal materijal = new Materijal();
                materijal.setSifraMaterijala(sifraMaterijala);
                stavkaprijemnice.setSifraMaterijala(materijal);
                break;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/remove_item/{brojStavke}", method = RequestMethod.GET)
    public Stavkaprijemnice remove_item(@PathVariable int brojStavke) {
        Stavkaprijemnice stv = new Stavkaprijemnice();
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == brojStavke) {
                stv = stavkaprijemnice;
            }
        }
        prijemnica.getStavkaprijemniceCollection().remove(stv);
        srediRbr();
        return null;
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView remove_goods_received_note(@PathVariable int id) {
        try {
            prijemnica = prijemnicaService.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("remove_goods_received_note", "grcn", prijemnica);

        return mv;
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.POST)
    public String remove_goods_received_note_post(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            prijemnicaService.obrisiPrijemnicu(id);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Nije moguce obrisati prijemnicu");
            return "redirect:/goods_received_note/remove_goods_received_note";

        }

    }

    @RequestMapping(value = "/find_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView find_goods_received_note(@PathVariable int id) {
        try {
            prijemnica = prijemnicaService.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("find_goods_received_note", "grcn", prijemnica);

        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView update_goods_received_note(@PathVariable int id) {
        prijemnica = new Prijemnica();
        try {
            prijemnica = prijemnicaService.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Vagarskapotvrda> listaVagarskihPotvrda = new ArrayList<>();
        List<Zaposleni> listaZaposlenih = new ArrayList<>();
        List<Dobavljac> listaDobavljaca = new ArrayList<>();
        try {
            listaDobavljaca = dobavljacService.ucitajDobavljace();
            listaVagarskihPotvrda = vagarskaPotvrdaService.pronadjiMoguceVagarskePotvrde();
            listaVagarskihPotvrda.add(prijemnica.getBrojVagarskePotvrde());
            listaZaposlenih = zaposleniService.ucitajZaposlene();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_goods_received_note", "grcn", prijemnica);
        mv.addObject("listaVagarskihPotvrda", listaVagarskihPotvrda);
        mv.addObject("listaZaposlenih", listaZaposlenih);
        mv.addObject("listaDobavljaca", listaDobavljaca);
        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note", method = RequestMethod.POST)
    public String update_goods_received_note_post(@ModelAttribute("goodsReceivedNote") Prijemnica prijemnicaM, RedirectAttributes redirectAttributes) {
        try {
            izracunajUkupno();
            prijemnicaService.zapamtiPrijemnicu(prijemnica);
            return "redirect:/goods_received_note/all_goods_received_notes";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Nije moguce izmeniti prijemnicu");
            return "redirect:/goods_received_note/update_goods_received_note";
        }
    }

    private void srediRbr() {
        for (int i = 0; i < prijemnica.getStavkaprijemniceCollection().size(); i++) {
            prijemnica.getStavkaprijemniceCollection().get(i).setRedniBroj(i + 1);
        }
    }

    private void izracunajUkupno() {
        double ukupno = 0;
        double pdv = 0;
        for (Stavkaprijemnice stavka : prijemnica.getStavkaprijemniceCollection()) {
            try {
                ukupno = ukupno + (stavka.getKolicina() * stavka.getSifraMaterijala().getCena());
                pdv = pdv + (stavka.getKolicina() * stavka.getSifraMaterijala().getCena() * stavka.getSifraMaterijala().getPdv() * 0.01);
            } catch (Exception ex) {
                Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        prijemnica.setUkupanPDV(pdv);
        prijemnica.setUkupno(ukupno);
        prijemnica.setUkupnoSaPDV(ukupno + pdv);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    private Stavkaprijemnice pronadjiStavku(int rbr) {
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == rbr) {
                return stavkaprijemnice;
            }
        }
        return null;
    }
}
