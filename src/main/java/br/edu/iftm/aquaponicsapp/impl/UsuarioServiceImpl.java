package br.edu.iftm.aquaponicsapp.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.iftm.aquaponicsapp.model.Usuario;
import br.edu.iftm.aquaponicsapp.repository.UsuarioRepository;
import br.edu.iftm.aquaponicsapp.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Integer saveUser(Usuario usuario) {
        String senha = usuario.getSenha();
        String senhaCriptografada = passwordEncoder.encode(senha);
        usuario.setSenha(senhaCriptografada);
        usuario = usuarioRepo.save(usuario);
        return usuario.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Usuario> opt = usuarioRepo.findUsuarioByEmail(email);

        org.springframework.security.core.userdetails.User springUser = null;

        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario com email: " + email + " não encontrado");
        } else {
            Usuario usuario = opt.get();
            List<String> perfis = usuario.getPerfis();
            Set<GrantedAuthority> ga = new HashSet<>();

            for (String perfil : perfis) {
                ga.add(new SimpleGrantedAuthority(perfil));
            }

            springUser = new org.springframework.security.core.userdetails.User(
                    email,
                    usuario.getSenha(),
                    ga);
        }

        return springUser;
    }
}