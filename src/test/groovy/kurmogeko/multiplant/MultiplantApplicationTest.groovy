package kurmogeko.multiplant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MultiplantApplicationTest extends Specification {
    @Autowired
    MultiplantApplication app

    def "App can start"() {
        expect:
        app != null
    }
}
