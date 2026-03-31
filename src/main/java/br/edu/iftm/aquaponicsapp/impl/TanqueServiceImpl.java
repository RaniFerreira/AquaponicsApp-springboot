package br.edu.iftm.aquaponicsapp.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.aquaponicsapp.model.Tanque;
import br.edu.iftm.aquaponicsapp.repository.TanqueRepository;
import br.edu.iftm.aquaponicsapp.service.TanqueService;


@Service
public class TanqueServiceImpl implements TanqueService {

    @Autowired
    private TanqueRepository tanqueRepository;

    @Override
    public List<Tanque> getAllTanque(){
        return tanqueRepository.findAll();
    }

    @Override
    public void saveTanque(Tanque tanque) {
        this.tanqueRepository.save(tanque);
    }

    @Override
    public Tanque getTanqueById(long id) {
        Optional<Tanque> optional = tanqueRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException("Tanque not found with id: " + id);
        }
    }

    @Override
    public void deleteTanqueById(long id) {
        this.tanqueRepository.deleteById(id);
    }
}