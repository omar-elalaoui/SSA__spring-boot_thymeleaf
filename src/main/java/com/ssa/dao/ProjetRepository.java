package com.ssa.dao;

import com.ssa.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, String > {
}
