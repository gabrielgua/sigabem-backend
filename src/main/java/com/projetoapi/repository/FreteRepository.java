package com.projetoapi.repository;

import com.projetoapi.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreteRepository extends JpaRepository<Long, Frete> {
}
