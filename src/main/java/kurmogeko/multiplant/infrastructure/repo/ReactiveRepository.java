package kurmogeko.multiplant.infrastructure.repo;

import reactor.core.publisher.Mono;

public interface ReactiveRepository<K, T> {
    public Mono<T> findById(K key);

    public Mono<T> save(T elem);

}
