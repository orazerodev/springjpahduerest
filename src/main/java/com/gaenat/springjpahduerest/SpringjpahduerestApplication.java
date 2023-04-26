package com.gaenat.springjpahduerest;

import com.gaenat.springjpahduerest.dto.CommentResponse;
import com.gaenat.springjpahduerest.model.Nota;
import com.gaenat.springjpahduerest.model.Note;
import com.gaenat.springjpahduerest.model.Comment;
import com.gaenat.springjpahduerest.repository.NotaRepository;
import com.gaenat.springjpahduerest.repository.NoteRepository;
import com.gaenat.springjpahduerest.repository.CommentRepository;
import com.gaenat.springjpahduerest.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import lombok.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class SpringjpahduerestApplication {

    @Value("${spring.stringa}")
    public String username;

    public WebClient webClient;

    public NoteService noteService;

    public static void main(String[] args) {
        SpringApplication.run(SpringjpahduerestApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(NoteRepository noteRepository, CommentRepository commentRepository, CommentResponse commentResponse, WebClient webClient) {
        return args -> {

//            noteRepository.save(new Note("todo", "tode"));
//            noteRepository.save(new Note("todo", "toda"));
//            notaRepository.save(new Nota("todo", "todi"));

//            Note note1 = new Note("note", "nota", null);

            Comment comment1 = new Comment("comment1");
            Comment comment2 = new Comment("comment2");
            Comment comment3 = new Comment("comment3");
            Comment comment4 = new Comment("comment4");

            Note note1 = Note.builder()
                    .type("note")
                    .content("nota")
                    .listComment(Arrays.asList(comment3, comment4))
                    .build();

            Note note2 = Note.builder()
                    .type("note")
                    .content("noti")
                    .listComment(Arrays.asList(comment3, comment4))
                    .build();

            Note note3 = Note.builder()
                    .type("note")
                    .content("noti")
                    .listComment(Arrays.asList(comment3, comment4))
                    .build();

//            note1.getListComment().add(comment1);
//            note1.getListComment().add(comment2);

            log.info(String.valueOf(note1));
            log.info(String.valueOf(note2));
            log.info(String.valueOf(note3));

            noteRepository.save(note1);
//            noteRepository.save(note2);
//            noteRepository.save(note3);

            log.info(String.valueOf(note1));
            log.info(String.valueOf(note2));
            log.info(String.valueOf(note3));

            log.info("Nota {} inserita", note1.getContent());

            List<Comment> comments = note1.getListComment();

            log.info(String.valueOf(comments));

            List<CommentResponse> commentResponses = comments.stream().map(
                    comment -> CommentResponse
                            .builder()
                            .content(comment.getContent())
                            .build()
            ).collect(Collectors.toList());

            log.info(String.valueOf(commentResponses));

            Note note9 = noteRepository.findNoteByContentAndTypeIsNotNull("nota").get();

            log.info(
                    String.valueOf(
                            note9.getContent()
                    )
            );

            log.info(
                    String.valueOf(
                            noteRepository.findNoteByIdAndTypeIsNotNull(1L).get().getContent()
                    )
            );

            String stringa = webClient.get()
                    .uri("http://localhost:8081/string")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info(String.valueOf(stringa));

            Note note8 = webClient.get()
                    .uri("http://localhost:8081/notes/1")
                    .retrieve()
                    .bodyToMono(Note.class)
                    .block();

            log.info(String.valueOf(note8.getContent()));

            log.info(String.valueOf(username));

//            for(var note: noteRepository.findNoteByTypeAndContent("todo", "toda")) {
//                log.info(note.toString());
//                //log.info(note.getContent());
//            }
//            for(var note: noteRepository.findNotesByType("note")) {
//                log.info(note.toString());
//            }
//            for(var note: noteRepository.findNotesByType("todo")) {
//                log.info(note.toString());
//            }
//            for(var note: noteRepository.findNotesByTypeOrderByContent("todo")) {
//                log.info(note.toString());
//            }
//            for(var note: noteRepository.findNotesByType("note")) {
//                log.info(note.toString());
//            }
//            for(var commentPrint: commentRepository.findAll()) {
//                log.info(commentPrint.getContent());
//            }
//
//           for(var notePrint: noteRepository.findAll()) {
//                for (var comment3 : notePrint.getListComment()) {
//                    System.out.println(comment3.getContent());
//                }
//            }

        };
    }



}
