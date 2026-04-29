package br.edu.iftm.aquaponicsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.iftm.aquaponicsapp.model.Usuario;
import br.edu.iftm.aquaponicsapp.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Ir para tela de cadastro
    @GetMapping("/register")
    public String register() {
        return "user/registerUser";
    }

    // Salvar usuário
    @PostMapping("/saveUser")
    public String saveUser(
            @ModelAttribute Usuario usuario,
            Model model) {

        Integer id = usuarioService.saveUser(usuario);

        String message = "Usuário '" + id + "' salvo com sucesso!";
        model.addAttribute("msg", message);

        return "user/registerUser";
    }

    // Página de acesso negado
    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "user/accessDeniedPage";
    }
}