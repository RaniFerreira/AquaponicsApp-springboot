package br.edu.iftm.aquaponicsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.aquaponicsapp.model.Tanque;

@Repository
public interface TanqueRepository extends JpaRepository<Tanque, Long> {

}