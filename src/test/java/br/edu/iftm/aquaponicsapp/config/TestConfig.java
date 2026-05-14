package br.edu.iftm.aquaponicsapp.config;


import br.edu.iftm.aquaponicsapp.service.TanqueService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public TanqueService tanqueService() {
        return Mockito.mock(TanqueService.class);
    }

} 