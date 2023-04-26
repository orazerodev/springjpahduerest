package com.gaenat.springjpahduerest.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NOTES")
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Getter @Setter
//@ToString
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull private String type;

    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "NOTE_ID")
    private List<Comment> listComment = new ArrayList<>();
}