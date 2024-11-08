package com.mercado.demo.service;

import com.mercado.demo.model.Tarefa;
import com.mercado.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Tarefa> findByStatus(int n_stat) {
        List<Tarefa> listaTarefa = tarefaRepository.findAll();
        List<Tarefa> TarefaPorStatus = new ArrayList<>();

        String stat = switch (n_stat) {
            case 1 -> "A fazer";
            case 2 -> "Em progresso";
            case 3 -> "Concluido";
            default -> "";
        };

        String[] prioridade = {"Baixa", "Media", "Alta"};
        int i = 0;

        while(i <= 2){

            for (Tarefa tarefa : listaTarefa) {
                if(tarefa.getStatus().equals(stat) && tarefa.getPrioridade().equals(prioridade[i])) {
                    TarefaPorStatus.add(tarefa);
                }
            }
            i++;
        }

        if(TarefaPorStatus.isEmpty()) {
            return null;
        }

        return TarefaPorStatus;
    }

    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void delete(int id) {
        tarefaRepository.deleteById(id);
        System.out.println("Tarefa "+ id +" deletada");
    }

    public void moverColuna(int id){
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null){
            throw new RuntimeException("Tarefa não encontrada");
        }
        else{
            switch (tarefa.getStatus()) {
                case "A fazer" -> {
                    tarefa.setStatus("Em progresso");
                    System.out.println("Status da tarefa "+id+" alterado para: " + tarefa.getStatus());
                    tarefaRepository.save(tarefa);
                }
                case "Em progresso" -> {
                    tarefa.setStatus("Concluido");
                    System.out.println("Status da tarefa "+id+" alterado para: " + tarefa.getStatus());
                    tarefaRepository.save(tarefa);
                }
                default -> System.out.println("Status da tarefa "+id+" já está Concluido");
            }
        }
    }

    public Tarefa editarTarefa(int id, Tarefa newStuff){
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null){
            throw new RuntimeException("Tarefa não encontrada");
        }
        else{
            if(newStuff.getTitulo() != null){
                tarefa.setTitulo(newStuff.getTitulo());
            }
            if(newStuff.getDescricao() != null){
                tarefa.setDescricao(newStuff.getDescricao());
            }
            if(newStuff.getPrioridade() != null){
                tarefa.setPrioridade(newStuff.getPrioridade());
            }
            if(newStuff.getData_criacao() != null) {
                tarefa.setData_criacao(newStuff.getData_criacao());
            }
            return tarefaRepository.save(tarefa);
        }
    }

}
