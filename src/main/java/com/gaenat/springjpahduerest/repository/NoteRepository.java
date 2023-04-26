package com.gaenat.springjpahduerest.repository;

import com.gaenat.springjpahduerest.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NoteRepository extends CrudRepository<Note, Long> {
    Optional<Note> findNoteByContent(String content);

    Optional<Note> findNoteByContentAndTypeIsNull(String content);
    Optional<Note> findNoteByContentAndTypeIsNotNull(String content);
    Optional<Note> findNoteByIdAndTypeIsNotNull(Long id);
    Iterable<Note> findNotesByType(String type);
    Iterable<Note> findAll();
    Iterable<Note> findNotesByTypeOrderByContent(String type);
    Iterable<Note> findNoteByTypeAndContent(String type, String content);

    Iterable<Note> findNoteByTypeAndContentOrderByContent(String type, String content);

}
