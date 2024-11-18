package com.mercado.demo.service;

import com.mercado.demo.model.Prioridade;
import com.mercado.demo.model.Status;
import com.mercado.demo.model.Tarefa;
import com.mercado.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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


    public List<Tarefa> listarPorStatus(int n_stat) {
        List<Tarefa> listaTarefa = tarefaRepository.findAll();
        List<Tarefa> TarefaPorStatus = new ArrayList<>();

        Status stat = switch (n_stat) {
            case 1 -> Status.A_FAZER;
            case 2 -> Status.EM_PROGRESSO;
            case 3 -> Status.CONCLUIDO;
            default -> null;
        };

        for (Tarefa tarefa : listaTarefa) {
            if(tarefa.getStatus().equals(stat)) {
                TarefaPorStatus.add(tarefa);
            }
        }

        if(TarefaPorStatus.isEmpty()) {
            return null;
        }

        TarefaPorStatus.sort(Comparator.comparing(Tarefa::getPrioridade));
        return TarefaPorStatus;
    }


    public Tarefa add(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }


    public String delete(int id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if(tarefa != null) {
            tarefaRepository.delete(tarefa);
            return "Tarefa deletada:\n\n"+ tarefa;
        }
        return "Tarefa não encontrada";
    }


    public Tarefa moverStatus(int id){
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null){
            throw new RuntimeException("Tarefa não encontrada");
        }
        else{
            switch (tarefa.getStatus()) {
                case A_FAZER -> tarefa.setStatus(Status.EM_PROGRESSO);
                case EM_PROGRESSO -> tarefa.setStatus(Status.CONCLUIDO);
                default -> tarefa.setStatus(Status.A_FAZER);
            }
            return tarefaRepository.save(tarefa);
        }
    }


    public Tarefa moverPrioridade(int id){
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null){
            throw new RuntimeException("Tarefa não encontrada");
        }
        else{
            switch (tarefa.getPrioridade()) {
                case BAIXA -> tarefa.setPrioridade(Prioridade.MEDIA);
                case MEDIA -> tarefa.setPrioridade(Prioridade.ALTA);
                default -> tarefa.setPrioridade(Prioridade.BAIXA);
            }
            return tarefaRepository.save(tarefa);
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

            return tarefaRepository.save(tarefa);
        }
    }

}
