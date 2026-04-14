package br.edu.iftm.aquaponicsapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import br.edu.iftm.aquaponicsapp.service.TanqueService;
import br.edu.iftm.aquaponicsapp.model.Tanque;
import jakarta.validation.Valid;

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
    public String save(@ModelAttribute @Valid Tanque tanque, BindingResult result, Model model) {
        System.out.println(result.getAllErrors());
        if (result.hasErrors()) {
            model.addAttribute("tanque", tanque);
            return "tanque/create";
        }

        TanqueService.saveTanque(tanque);
        return "redirect:/tanque";
    }

     @GetMapping("/tanque/delete/{id}")
    public String delete(@PathVariable Long id) {
        this.TanqueService.deleteTanqueById(id);
        return "redirect:/tanque";
    }

    @GetMapping("/tanque/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Tanque tanque = TanqueService.getTanqueById(id);
        model.addAttribute("tanque", tanque);
        return "tanque/edit";
    }
}
