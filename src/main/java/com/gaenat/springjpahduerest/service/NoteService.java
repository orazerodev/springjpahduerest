package com.gaenat.springjpahduerest.service;

import com.gaenat.springjpahduerest.model.Note;
import com.gaenat.springjpahduerest.repository.NoteRepository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public interface NoteService {
    Iterable<Note> getNotes();
    public Note getNoteByIdSync(final String id);
    Optional<Note> getNoteById(Long id);
    Optional<Note> getNoteByContent(String content);
    Iterable<Note> getNotesByType(String type);
//    public Iterable<Note> addNote(String content);
}
