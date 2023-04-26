package com.gaenat.springjpahduerest.repository;

import com.gaenat.springjpahduerest.model.Nota;
import com.gaenat.springjpahduerest.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotaRepository extends CrudRepository<Nota, Long> {
    Iterable<Nota> findByTypeOrderByContent(String type);

}
