package com.gaenat.springjpahduerest.service;

import com.gaenat.springjpahduerest.model.Note;
import com.gaenat.springjpahduerest.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    public final NoteRepository noteRepository;
    public final WebClient webClient;

    public NoteServiceImpl(NoteRepository noteRepository, WebClient webClient) {
        this.noteRepository = noteRepository;
        this.webClient = webClient;
    }

    public Note getNoteByIdSync(final String id) {
        return webClient
                .get()
                .uri(String.join("", "http://localhost:8081/notes/", id))
                .retrieve()
                .bodyToMono(Note.class)
                .block();
    }

    @Override
    public Iterable<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public Optional<Note> getNoteByContent(String content) {
        return noteRepository.findNoteByContent(content);
    }

    @Override
    public Iterable<Note> getNotesByType(String type) {
        return noteRepository.findNotesByType(type);
    }

//    @Override
//    public Iterable<Note> addNote(String content) {
//        noteRepository.save(new Note("note", content));
//        return noteRepository.findAll();
//    };
}
