package com.ester.TarefasAPIRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ester.TarefasAPIRest.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository <Tarefa, Long>{

}
