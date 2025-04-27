package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SideBar {
    private String name;
    private String link;
    private String icon;
}
