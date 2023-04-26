package com.gaenat.springjpahduerest.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Component
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentResponse {
    @NonNull private String content;
}