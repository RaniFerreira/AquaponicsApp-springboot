package br.edu.iftm.aquaponicsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.aquaponicsapp.service.TanqueService;
import br.edu.iftm.aquaponicsapp.model.Tanque;

@Controller
public class TanqueController {

    @Autowired
    private TanqueService TanqueService;
   
     @GetMapping("/tanque")
     public String index(Model model) {
        model.addAttribute("tanque", TanqueService.getAllTanque());
        return "/tanque/index";
    }

    @GetMapping("/tanque/create")
    public String create(Model model) {
        model.addAttribute("tanque", new Tanque());
        return "tanque/create";
    }

    @PostMapping("/tanque/save")
    public String postMethodName(@ModelAttribute("tanque") Tanque tanque) {
        TanqueService.saveTanque(tanque);
        return "redirect:/tanque";
    }
}
