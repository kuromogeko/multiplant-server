package kurmogeko.multiplant.domain;

import kurmogeko.multiplant.domain.entities.Session;
import kurmogeko.multiplant.domain.repo.SessionRepository;
import kurmogeko.multiplant.domain.values.UserInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public Mono<Session> createEmptySession(String name){
        var defaultSession = Session.builder().key(name).userInformations(new ArrayList<>()).build();
        return sessionRepository.save(defaultSession);
    }

}
