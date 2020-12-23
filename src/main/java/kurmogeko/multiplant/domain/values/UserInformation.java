package kurmogeko.multiplant.domain.values;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.Map;

@With
@AllArgsConstructor
@NoArgsConstructor
public class UserInformation {
    private String username;
    private Map<String, Object> data;
}
