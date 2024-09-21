package com.bezkoder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutorial_generator")
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

    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

}
