package kurmogeko.multiplant.domain.values;

import lombok.*;

import java.util.Map;

@With
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class UserInformation {
    private String username;
    private Map<String, Object> data;
}
