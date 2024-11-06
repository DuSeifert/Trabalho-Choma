package com.mercado.demo.controller;

import com.mercado.demo.model.Tarefa;
import com.mercado.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar(){
        return tarefaService.findAll();
    }

    @GetMapping("/{id}")
    public Tarefa listarById(@PathVariable int id){
        return tarefaService.findById(id);
    }

    @PostMapping
    public Tarefa add(@RequestBody Tarefa tarefa){
        return tarefaService.save(tarefa);
    }
}
