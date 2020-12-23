package kurmogeko.multiplant.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SessionServiceTest extends Specification {

    @Autowired
    SessionService subject;

    def "Repo is injected"() {
        expect:
        subject.sessionRepository != null
    }


}
