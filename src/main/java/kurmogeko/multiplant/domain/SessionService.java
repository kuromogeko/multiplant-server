package kurmogeko.multiplant.domain;

import kurmogeko.multiplant.domain.entities.Session;
import kurmogeko.multiplant.domain.exceptions.SessionExistsException;
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

    public Mono<Session> createSaveEmptySession(String name) {
        return sessionRepository.findById(name)
                .flatMap(e -> Mono.<Session>error(new SessionExistsException()))
                .switchIfEmpty(Mono.just(getDefaultSession(name)))
                .flatMap(sessionRepository::save);
    }

    private Session getDefaultSession(String name) {
        return Session.builder().key(name).userInformations(new ArrayList<>()).build();
    }

    public Mono<Session> updateUserInformation(Session s, UserInformation information) {
        s.updateUserInformation(information);
        return sessionRepository.save(s);
    }

}
