package com.bezkoder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    private boolean published;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Tutorial(String title, String description, int level, boolean published, Date createdAt) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
        this.createdAt = createdAt;
    }
}
