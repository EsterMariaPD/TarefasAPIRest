package com.ester.TarefasAPIRest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ester.TarefasAPIRest.model.Tarefa;
import com.ester.TarefasAPIRest.repository.TarefaRepository;

@RestController
@RequestMapping({"/tarefas"})
public class TarefaController {

	 private TarefaRepository tarefaRepository;
	 
	 TarefaController(TarefaRepository tarefaRepository) {
	        this.tarefaRepository = tarefaRepository;
	    }
	 
	 @GetMapping
	 public List<Tarefa> findAll() {
	     return tarefaRepository.findAll();
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Tarefa> findById(@PathVariable Long id) {
	     return tarefaRepository.findById(id)
	         .map(tarefa -> ResponseEntity.ok().body(tarefa))
	         .orElse(ResponseEntity.notFound().build());
	 }
	 
	 @PostMapping
	 public Tarefa create(@RequestBody Tarefa tarefa) {
	     return tarefaRepository.save(tarefa);
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
	     return tarefaRepository.findById(id).map(existingTarefa -> {
	         existingTarefa.setNome(tarefa.getNome());
	         existingTarefa.setDataEntrega(tarefa.getDataEntrega());
	         existingTarefa.setResponsavel(tarefa.getResponsavel());
	         Tarefa updated = tarefaRepository.save(existingTarefa);
	         return ResponseEntity.ok().body(updated);
	     }).orElse(ResponseEntity.notFound().build());
	 }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable Long id) {
	     return tarefaRepository.findById(id).map(tarefa -> {
	    	tarefaRepository.deleteById(id);
	    	return ResponseEntity.ok().build();
	     }).orElse(ResponseEntity.notFound().build());
	 }

}
