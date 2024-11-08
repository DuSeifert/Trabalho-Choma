package com.mercado.demo.controller;

import com.mercado.demo.model.Tarefa;
import com.mercado.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
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

    @GetMapping("/filter/{stat}")
    public List<Tarefa> listarPorStatus(@PathVariable int stat){
        return tarefaService.findByStatus(stat);
    }

    @PostMapping
    public Tarefa add(@RequestBody Tarefa tarefa){
        return tarefaService.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable int id){
        Tarefa tarefa = tarefaService.findById(id);
        tarefaService.delete(id);
        return "Tarefa deletada com sucesso: \n\n" + tarefa.toString();
    }

    @PutMapping("/{id}/move")
    public Tarefa update(@PathVariable int id){
        tarefaService.moverColuna(id);
        return tarefaService.findById(id);
    }

    @PutMapping("/{id}")
    public Tarefa edit(@PathVariable int id, @RequestBody Tarefa tarefa){
        return tarefaService.editarTarefa(id, tarefa);
    }

}
