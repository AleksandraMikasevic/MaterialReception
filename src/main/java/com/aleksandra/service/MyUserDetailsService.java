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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IZaposleniDAORep zaposleniDAO;

 /*   public void setZaposleniDAO(IZaposleniDAORep zaposleniDAO) {
        this.zaposleniDAO = zaposleniDAO;
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOAD BY USERNAME");
        System.out.println("username: " + username);
        if(zaposleniDAO == null) {
            System.out.println("sandraaaaa");
        }
        Zaposleni zaposleni = zaposleniDAO.findByKorisnickoIme(username);
        System.out.println("sandra");
        System.out.println(zaposleni.getIme()+" ime");
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        UserDetails userDetails = new User(zaposleni.getKorisnickoIme(), zaposleni.getLozinka(), auth);
        return userDetails;
    }

}
