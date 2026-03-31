package br.edu.iftm.aquaponicsapp.service;

import java.util.List;

import br.edu.iftm.aquaponicsapp.model.Tanque;

public interface TanqueService {

    List <Tanque> getAllTanque();
    void saveTanque(Tanque taqnue);
    Tanque getTanqueById(long id);
    void deleteTanqueById(long id);
}