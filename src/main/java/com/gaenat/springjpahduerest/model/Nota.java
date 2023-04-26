package com.gaenat.springjpahduerest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "NOTE")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@ToString
public class Nota {
    @Id
    @Column(name = "nota_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull private String type;

    @NonNull private String content;

}