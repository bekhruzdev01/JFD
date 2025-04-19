package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private String message;
    private boolean success;
}
