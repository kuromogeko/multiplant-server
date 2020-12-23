package kurmogeko.multiplant.infrastructure.repo

import kurmogeko.multiplant.ObjectMother
import kurmogeko.multiplant.infrastructure.repo.SizeCacheConfiguration
import spock.lang.Specification

class SizeCacheConfigurationTest extends Specification {

    def "Cache can store and get elements correctly"() {
        given: "A session"
        def key = "Key"
        def session = ObjectMother.getSession(key)

        and: "a cache"
        def cache = new SizeCacheConfiguration().sizeLimitedSessionCache()

        when:
        cache.put(session.getKey(), session)
        def found = cache.get(key)

        then:
        found == session
    }

    def "Cache will return null on unknown or expired key"() {
        given: "a cache"
        def cache = new SizeCacheConfiguration().sizeLimitedSessionCache()

        when:
        def result = cache.get("nothing")

        then:
        result == null
        noExceptionThrown()
    }

    def "Cache has a limited memory of 20 elements"() {
        given: "a cache"
        def cache = new SizeCacheConfiguration().sizeLimitedSessionCache()
        when: "Cache is overflown"
        for(int i =0; i<21; i++){
            cache.put("a"+i, ObjectMother.getSession("a"+i))
        }
        def result = cache.get("a0")

        then: "first result has expired"
        result == null

    }
}
