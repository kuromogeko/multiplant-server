package kurmogeko.multiplant.domain

import kurmogeko.multiplant.ObjectMother
import kurmogeko.multiplant.domain.exceptions.SessionExistsException
import kurmogeko.multiplant.infrastructure.repo.ReactiveSessionRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import spock.lang.Specification

@SpringBootTest
class SessionServiceTest extends Specification {

    @Autowired
    SessionService subject;

    @SpringBean
    ReactiveSessionRepository repo = Mock()

    def "Repo is injected"() {
        expect:
        subject.sessionRepository != null
    }

    def "Throws if session with given name already exists"() {
        given: "name"
        def name ="soß"

        when:
        subject.createSaveEmptySession(name).block()

        then:
        thrown(SessionExistsException)
        0 * repo.save(_)
        1 * repo.findById(name) >> Mono.just(ObjectMother.getSession(name))
    }

    def "If find returns empty, saves new session"() {
        given: "name"
        def name ="hi"
        def session = ObjectMother.getSession(name)

        when:
        def result = subject.createSaveEmptySession(name).block()

        then:
        result == session
        1 * repo.findById(name) >> Mono.empty()
        1 * repo.save(_) >> Mono.just(session)
    }
}
