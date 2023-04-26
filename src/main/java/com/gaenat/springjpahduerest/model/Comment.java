package com.gaenat.springjpahduerest.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull private String content;
}