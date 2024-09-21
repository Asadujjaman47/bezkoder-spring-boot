package com.bezkoder.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Lob
    @Getter
    @Setter
    private String content;
}
