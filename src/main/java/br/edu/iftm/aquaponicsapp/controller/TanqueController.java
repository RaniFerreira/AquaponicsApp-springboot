package br.edu.iftm.aquaponicsapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TanqueController {
   
     @GetMapping("/tanque")
    public String getMethodName() {
        return "/tanque/index";
    }
}
