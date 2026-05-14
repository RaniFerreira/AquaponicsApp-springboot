package br.edu.iftm.aquaponicsapp.tanque;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.iftm.aquaponicsapp.config.TestConfig;
import br.edu.iftm.aquaponicsapp.controller.TanqueController;
import br.edu.iftm.aquaponicsapp.model.Tanque;
import br.edu.iftm.aquaponicsapp.service.TanqueService;

@WebMvcTest(TanqueController.class)
@Import(TestConfig.class)
public class TanqueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TanqueService tanqueService;

    @AfterEach
    void resetMocks() {
        reset(tanqueService);
    }

    private List<Tanque> testCreateTanqueList() {
        Tanque tanque = new Tanque();
        tanque.setId(1L);
        tanque.setNome("Tanque Tilápia");
        tanque.setDescricao("Tanque principal de tilápias");
        tanque.setEspecie("Tilápia");
        tanque.setTamanhoPeixes(25.0);
        tanque.setQuantidadePeixes(100);
        tanque.setPeso(0.5);

        return List.of(tanque);
    }

    @Test
    @DisplayName("GET /tanque - Listar tanques na tela index sem usuário autenticado")
    void testIndexNotAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/tanque"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /tanque - Listar tanques na tela index com usuário logado")
    void testIndexAuthenticatedUser() throws Exception {
        when(tanqueService.getAllTanque()).thenReturn(testCreateTanqueList());

        mockMvc.perform(get("/tanque"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tanque/index"))
                .andExpect(model().attributeExists("tanque"))
                .andExpect(content().string(containsString("Tanque Tilápia")));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("GET /tanque/create - Exibe formulário de criação para usuário autorizado")
    void testCreateFormAuthorizedUser() throws Exception {
        mockMvc.perform(get("/tanque/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("tanque/create"))
                .andExpect(model().attributeExists("tanque"));
    }

    @Test
    @WithMockUser
    @DisplayName("POST /tanque/save - Falha na validação e retorna para o formulário de criação")
    void testSaveTanqueValidationError() throws Exception {
        Tanque tanque = new Tanque(); // Tanque sem campos obrigatórios, causará erros de validação

        mockMvc.perform(post("/tanque/save")
                        .with(csrf())
                        .flashAttr("tanque", tanque))
                .andExpect(status().isOk())
                .andExpect(view().name("tanque/create"))
                .andExpect(model().attributeHasErrors("tanque"));

        verify(tanqueService, never()).saveTanque(any(Tanque.class));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("POST /tanque/save - Tanque válido é salvo com sucesso")
    void testSaveValidTanque() throws Exception {
        Tanque tanque = new Tanque();
        tanque.setNome("Tanque Salmão");
        tanque.setDescricao("Tanque de salmões do atlântico");
        tanque.setEspecie("Salmão");
        tanque.setTamanhoPeixes(40.0);
        tanque.setQuantidadePeixes(50);
        tanque.setPeso(2.5);

        mockMvc.perform(post("/tanque/save")
                        .with(csrf())
                        .flashAttr("tanque", tanque))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tanque"));

        verify(tanqueService).saveTanque(any(Tanque.class));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("GET /tanque/edit/{id} - Exibe formulário de edição com dados do tanque")
    void testEditFormAuthenticatedUser() throws Exception {
        Tanque tanque = testCreateTanqueList().get(0);
        when(tanqueService.getTanqueById(1L)).thenReturn(tanque);

        mockMvc.perform(get("/tanque/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tanque/edit"))
                .andExpect(model().attributeExists("tanque"));
    }

    @Test
    @WithMockUser(username = "aluno@iftm.edu.br", authorities = { "Admin" })
    @DisplayName("GET /tanque/delete/{id} - Deleta tanque e redireciona para listagem")
    void testDeleteTanque() throws Exception {
        mockMvc.perform(get("/tanque/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tanque"));

        verify(tanqueService).deleteTanqueById(1L);
    }

    @Test
    @WithMockUser
    @DisplayName("POST /tanque/save - Falha na validação ao editar e retorna para o formulário de edição")
    void testSaveTanqueValidationErrorOnEdit() throws Exception {
        Tanque tanque = new Tanque();
        tanque.setId(1L); // ID presente indica edição

        mockMvc.perform(post("/tanque/save")
                        .with(csrf())
                        .flashAttr("tanque", tanque))
                .andExpect(status().isOk())
                .andExpect(view().name("tanque/edit"))
                .andExpect(model().attributeHasErrors("tanque"));

        verify(tanqueService, never()).saveTanque(any(Tanque.class));
    }
}