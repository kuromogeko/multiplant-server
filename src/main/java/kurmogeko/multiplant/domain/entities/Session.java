package kurmogeko.multiplant.domain.entities;


import kurmogeko.multiplant.domain.values.UserInformation;
import lombok.*;


import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String key;
    private List<UserInformation> userInformations;


}
