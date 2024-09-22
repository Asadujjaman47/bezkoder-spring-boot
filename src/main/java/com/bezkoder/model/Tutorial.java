package com.bezkoder.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Tutorial {

    private long id;
    private String title;
    private String description;
    private boolean published;

}
