package com.mercado.demo.repository;

import com.mercado.demo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {


}
