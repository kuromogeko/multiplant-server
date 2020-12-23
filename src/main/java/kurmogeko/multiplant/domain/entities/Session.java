package kurmogeko.multiplant.domain.entities;


import kurmogeko.multiplant.domain.values.UserInformation;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String key;
    private List<UserInformation> userInformations;

    public void updateUserInformation(UserInformation information) {
        this.userInformations = this.userInformations
                .stream()
                .filter(info -> !info.getUsername().equals(information.getUsername()))
                .collect(Collectors.toList());
        this.userInformations.add(information);
    }


}
