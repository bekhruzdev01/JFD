package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Result {
    private String message;
    private boolean success;
}
