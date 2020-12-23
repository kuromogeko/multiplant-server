package kurmogeko.multiplant.infrastructure;

import kurmogeko.multiplant.domain.entities.Session;
import lombok.AllArgsConstructor;
import org.ehcache.Cache;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@AllArgsConstructor
public class ReactiveSessionRepository implements ReactiveRepository<String, Session> {

    private final Cache<String, Session> cache;

    @Override
    public Mono<Session> findById(String key) {
        return Mono.justOrEmpty(cache.get(key));
    }

    @Override
    public Mono<Session> save(Session elem) {
        Objects.requireNonNull(elem);
        Objects.requireNonNull(elem.getKey());

        cache.put(elem.getKey(), elem);
        return Mono.just(elem);
    }

}
