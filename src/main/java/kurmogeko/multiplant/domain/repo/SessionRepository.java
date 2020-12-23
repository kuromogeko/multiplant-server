package kurmogeko.multiplant.domain.repo;

import kurmogeko.multiplant.domain.entities.Session;
import kurmogeko.multiplant.infrastructure.repo.ReactiveRepository;

public interface SessionRepository extends ReactiveRepository<String, Session> {
}
