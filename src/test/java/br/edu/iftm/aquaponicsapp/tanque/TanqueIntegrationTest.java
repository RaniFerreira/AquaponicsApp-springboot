package br.edu.iftm.aquaponicsapp.tanque;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.iftm.aquaponicsapp.model.Tanque;
import br.edu.iftm.aquaponicsapp.repository.TanqueRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Usa application-test.properties
@Transactional // Limpa o banco após cada teste
public class TanqueIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TanqueRepository tanqueRepository;

    @Test
    @WithMockUser(authorities = { "Admin" })
    void testSaveTanqueIntegration() throws Exception {
        Tanque tanque = new Tanque();
        tanque.setNome("Tanque Tilápia");
        tanque.setDescricao("Tanque principal de tilápias");
        tanque.setEspecie("Tilápia");
        tanque.setTamanhoPeixes(25.0);
        tanque.setQuantidadePeixes(100);
        tanque.setPeso(0.5);

        mockMvc.perform(post("/tanque/save")
                .with(csrf())
                .flashAttr("tanque", tanque))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tanque"));

        // Verifica no banco se foi salvo
        assertTrue(tanqueRepository.findAll()
                .stream()
                .anyMatch(t -> "Tanque Tilápia".equals(t.getNome())));
    }
}