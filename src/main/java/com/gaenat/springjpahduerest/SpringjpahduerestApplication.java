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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static sun.util.logging.LoggingSupport.log;

@Slf4j
@SpringBootApplication
public class SpringjpahduerestApplication {

    @Value("${spring.stringa}")
    public String username;

    @Value("${server.port}")
    public Integer port;

    public WebClient webClient;

    public NoteService noteService;

//    public URIBuilder builder;

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

            SpringjpahduerestApplication.log.info(String.valueOf(note1));
            SpringjpahduerestApplication.log.info(String.valueOf(note2));
            SpringjpahduerestApplication.log.info(String.valueOf(note3));

            noteRepository.save(note1);
//            noteRepository.save(note2);
//            noteRepository.save(note3);

            SpringjpahduerestApplication.log.info(String.valueOf(note1));
            SpringjpahduerestApplication.log.info(String.valueOf(note2));
            SpringjpahduerestApplication.log.info(String.valueOf(note3));

            SpringjpahduerestApplication.log.info("Nota {} inserita", note1.getContent());

            List<Comment> comments = note1.getListComment();

            SpringjpahduerestApplication.log.info(String.valueOf(comments));

            List<CommentResponse> commentResponses = comments.stream().map(
                    comment -> CommentResponse
                            .builder()
                            .content(comment.getContent())
                            .build()
            ).collect(Collectors.toList());

            SpringjpahduerestApplication.log.info(String.valueOf(commentResponses));

            Note note9 = noteRepository.findNoteByContentAndTypeIsNotNull("nota").get();

            SpringjpahduerestApplication.log.info(
                    String.valueOf(
                            note9.getContent()
                    )
            );

            SpringjpahduerestApplication.log.info(
                    String.valueOf(
                            noteRepository.findNoteByIdAndTypeIsNotNull(1L).get().getContent()
                    )
            );


//            URI uri2 = UriComponentsBuilder.fromUriString("http://localhost:{port}")
//                    .path("string")
//                    .build(port);

//            URI uri9 = new URI("");

            URI uri1 = UriComponentsBuilder.fromUri(new URI(""))
                    .scheme("http")
                    .host("localhost")
                    .path("string")
                    .port(port)
                    .build(port);

            String stringa = webClient.get()
                    .uri(uri1)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            SpringjpahduerestApplication.log.info(String.valueOf(stringa));

            Note note8 = webClient.get()
//                    .uri("http://localhost:8082/notes/1")
                    .uri(UriComponentsBuilder.fromUri(new URI(""))
                            .scheme("http")
                            .host("localhost")
                            .path("notes/1")
                            .port(port)
                            .build(port))
                    .retrieve()
                    .bodyToMono(Note.class)
                    .block();

            SpringjpahduerestApplication.log.info(String.valueOf(note8.getContent()));

            SpringjpahduerestApplication.log.info(String.valueOf(username));

            Document doc1 = Jsoup.connect("https://en.wikipedia.org/").get();

            SpringjpahduerestApplication.log.info(String.valueOf(doc1.title()));
            Elements newsHeadlines = doc1.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                SpringjpahduerestApplication.log.info(headline.attr("title"));
                SpringjpahduerestApplication.log.info(headline.absUrl("href"));
            }

            Document doc2 = Jsoup.parse("<a target=\"_blank\" href=\"1\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g1\"></div><span><strong>Product-</strong>URANIA FE-LS 5W30<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>29.6L \"12.01 cm\"<br><strong>Ullage-</strong>176.4<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"2\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g2\"></div><span><strong>Product-</strong>URANIA NEXT 0W20<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>66.4L \"26.95 cm\"<br><strong>Ullage-</strong>139.6<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"3\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g3\"></div><span><strong>Product-</strong>URANIA LD7 15W40<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>38.7L \"15.7 cm\"<br><strong>Ullage-</strong>167.3<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"4\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g4\"></div><span><strong>Product-</strong>TUTELA GEAR 75W80<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>170.1L \"69.09 cm\"<br><strong>Ullage-</strong>35.9<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"5\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g5\"></div><span><strong>Product-</strong>TUTELA FE AXLE 75W90<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>157.2L \"63.85 cm\"<br><strong>Ullage-</strong>48.8<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"6\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g6\"></div><span><strong>Product-</strong>PARAFLU UP<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>216.6L \"97.31 cm\"<br><strong>Ullage-</strong>0<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"7\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g7\"></div><span><strong>Product-</strong>LAVAVETRO 40<br><strong>Capacity-</strong>216.6L<br><strong>Contents-</strong>94.8L \"55.07 cm\"<br><strong>Ullage-</strong>111.2<br><strong>High Level Shutoff-</strong>206.0L<br><strong>High Level Warning-</strong>195.0L<br><strong>Re order Level-</strong>43.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a><a target=\"_blank\" href=\"8\" class=\"\" id=\"tooltip\"><div class=\"tank_img\" id=\"g8\"></div><span><strong>Product-</strong>OLIO ESAUSTO<br><strong>Capacity-</strong>1562.4L<br><strong>Contents-</strong>1401.1L \"137.75 cm\"<br><strong>Ullage-</strong>98.9<br><strong>High Level Shutoff-</strong>1,500.0L<br><strong>High Level Warning-</strong>1,100.0L<br><strong>Re order Level-</strong>200.0L<br><strong>Low Level Shutoff-</strong>11.0L<br></span></a>");

            SpringjpahduerestApplication.log.info(String.valueOf(doc2.title()));

            newsHeadlines = doc2.select("a");
            for (Element headline : newsHeadlines) {
//                SpringjpahduerestApplication.log.info(headline.attr("title"));
                SpringjpahduerestApplication.log.info(headline.attr("target") + " " + headline.attr("href") + " " + headline.className()  + " " + headline.id());
//                SpringjpahduerestApplication.log.info(headline.text());

//                SpringjpahduerestApplication.log.info(String.valueOf(headline.getElementsByTag("span")
//                        .get(0)
//                        .getElementsByTag("strong").val()
//                        ) + "\n"
//                );
//                SpringjpahduerestApplication.log.info(headline.absUrl("href"));

                for (Element element: headline.getElementsByTag("span")) {
                    for (Element strong: element.getElementsByTag("strong")) {
                        SpringjpahduerestApplication.log.info("Strong: " + strong.text() + " " + strong.nextSibling());
                    }
                }
            }

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
