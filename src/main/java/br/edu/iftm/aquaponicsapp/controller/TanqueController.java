package br.edu.iftm.aquaponicsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.iftm.aquaponicsapp.service.TanqueService;

@Controller
public class TanqueController {

    @Autowired
    private TanqueService TanqueService;
   
     @GetMapping("/tanque")
     public String index(Model model) {
        model.addAttribute("tanque", TanqueService.getAllTanque());
        return "/tanque/index";
    }
}
