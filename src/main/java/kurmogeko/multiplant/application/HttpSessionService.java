package kurmogeko.multiplant.application;

import kurmogeko.multiplant.application.exceptions.NotFoundException;
import kurmogeko.multiplant.domain.SessionService;
import kurmogeko.multiplant.domain.entities.Session;
import kurmogeko.multiplant.domain.repo.SessionRepository;
import kurmogeko.multiplant.domain.values.UserInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class HttpSessionService {
    private final SessionService sessionService;
    private final SessionRepository repository;

    public Mono<Session> newSessionByName(String name){
        return sessionService.createSaveEmptySession(name);
    }

    public Mono<Session> addUpdateUser(UserInformation information, String sessionName){
        return repository.findById(sessionName)
                .switchIfEmpty(Mono.error(new NotFoundException()))
                .flatMap(session -> sessionService.updateUserInformation(session, information));
    }

    public Mono<Session> getSession(String name){
        return repository.findById(name);
    }
}
