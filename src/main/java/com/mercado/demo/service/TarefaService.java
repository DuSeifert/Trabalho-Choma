package com.mercado.demo.service;

import com.mercado.demo.model.Tarefa;
import com.mercado.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

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
                    tarefa.setStatus("Concluído");
                    System.out.println("Status da tarefa "+id+" alterado para: " + tarefa.getStatus());
                    tarefaRepository.save(tarefa);
                }
                default -> {
                    System.out.println("Status da tarefa "+id+" já está Concluído");
                }
            }
        }
    }

    public void editarTarefa(int id){
        Scanner sc = new Scanner(System.in);
        Tarefa tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null){
            throw new RuntimeException("Tarefa não encontrada");
        }
        else{
            System.out.println(tarefa.toString());

            System.out.println("\nNovo Título: ");
            tarefa.setTitulo(sc.nextLine());

            System.out.println("\nNova Descrição: ");
            tarefa.setDescricao(sc.nextLine());

            int op;
            System.out.println("\nNova Prioridade:\n  1 -- Baixa\n  2 -- Media\n  3 -- Alta");
            do{
                op = sc.nextInt();
                sc.nextLine();
            }while(op < 1 || op > 3);

            switch (op){
                case 1:
                    tarefa.setPrioridade("Baixa");
                    break;
                case 2:
                    tarefa.setPrioridade("Media");
                    break;
                case 3:
                    tarefa.setPrioridade("Alta");
            }

            System.out.println("\nNova tarefa editada: \n" + tarefa.toString());
            tarefaRepository.save(tarefa);
        }
    }

}
