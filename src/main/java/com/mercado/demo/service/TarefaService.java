package com.mercado.demo.service;

import com.mercado.demo.model.Tarefa;
import com.mercado.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Tarefa findById(int id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void delete(int id) {
        tarefaRepository.deleteById(id);
    }



}
