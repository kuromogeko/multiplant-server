package kurmogeko.multiplant.infrastructure.web;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WebError {
    private String title;
    private String description;
}
