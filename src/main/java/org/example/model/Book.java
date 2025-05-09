package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Book {
    private Integer id;
    private String name;
    private double price;
    private String writer;
    private Integer year;
}
