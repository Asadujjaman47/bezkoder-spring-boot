package com.bezkoder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "published")
    @Getter
    @Setter
    private boolean published;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "tutorial_id")
    @Getter
    @Setter
    private Set<Comment> comments = new HashSet<>();

    public void removeComments() {
        this.comments.clear();
    }

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

}
