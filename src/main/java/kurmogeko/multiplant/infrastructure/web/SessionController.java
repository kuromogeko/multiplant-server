package kurmogeko.multiplant.infrastructure.web;

import kurmogeko.multiplant.application.HttpSessionService;
import kurmogeko.multiplant.application.exceptions.NotFoundException;
import kurmogeko.multiplant.domain.entities.Session;
import kurmogeko.multiplant.domain.exceptions.SessionExistsException;
import kurmogeko.multiplant.domain.values.UserInformation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class SessionController {

    private final HttpSessionService sessionService;

    @GetMapping("/sessions/{name}")
    public Mono<ResponseEntity<Session>> getSessionByName(@PathVariable String name) {
        return sessionService.getSession(name).map(ResponseEntity::ok).switchIfEmpty(Mono.error(new NotFoundException()));
    }

    @PostMapping("/sessions")
    public Mono<ResponseEntity<Session>> addSession(@RequestBody SessionRequest request) {
        return sessionService.newSessionByName(request.getName())
                .map(session -> ResponseEntity.status(HttpStatus.CREATED).body(session));
    }

    @ExceptionHandler(SessionExistsException.class)
    public Mono<ResponseEntity<WebError>> handleExistsException(SessionExistsException exception) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(WebError.builder()
                        .title("Creation failed")
                        .description("Session exists").build()));
    }

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<WebError>> handleNotFound(NotFoundException exception) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(WebError.builder()
                        .title("Request error")
                        .description("Not found")
                        .build()));
    }

    @PostMapping("/sessions/{name}/info")
    public Mono<ResponseEntity<Session>> sendUserInformation(@PathVariable String name, @RequestBody UserInformation information){
        return sessionService.addUpdateUser(information, name).map(ResponseEntity::ok);
    }


}
