package com.gaenat.springjpahduerest.controller;

import com.gaenat.springjpahduerest.model.Note;
import com.gaenat.springjpahduerest.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class NoteController {
    public final NoteService noteService;
    public final WebClient webClient;

    @GetMapping("/notes")
    public Iterable<Note> getNotes(){
        return noteService.getNotes();
    }

    @GetMapping("/notes/{id}")
    public Optional<Note> getNote(@PathVariable("id") Long id){
        return noteService.getNoteById(id);
    }

    @GetMapping("/notesbycontent/{content}")
    public Optional<Note> getNoteByContent(@PathVariable("content") String content){
        return noteService.getNoteByContent(content);
    }

    @GetMapping("/string")
    public String getString() {
        return String.valueOf(noteService.getNoteByIdSync("1").getContent());
    }

    @GetMapping("/call")
    public String getCall() {
        Note note8 = webClient.get()
                .uri("http://localhost:8082/notes/1")
                .retrieve()
                .bodyToMono(Note.class)
                .block();

        return String.valueOf(note8.getContent());
    }

//    @GetMapping("/call2")
//    public String getCall2() {
//        Note note8 = client.get()
//                .uri("http://localhost:8081/notes/1")
//                .retrieve()
//                .bodyToMono(Note.class)
//                .block();
//        return String.valueOf(note8);
//    }

//    @GetMapping("/noteadd/{content}")
//    public Iterable<Note> addNote(@PathVariable("content") String content){
//        noteService.addNote(content);
//        return noteService.getNotes();
//    }

}
