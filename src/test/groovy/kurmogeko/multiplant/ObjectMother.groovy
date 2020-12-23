package kurmogeko.multiplant

import kurmogeko.multiplant.domain.entities.Session
import kurmogeko.multiplant.domain.values.UserInformation

class ObjectMother {
    static UserInformation getUserInformation(){
        new UserInformation().withUsername("Usaa").withData(Map.of("a", "b"))
    }

    static List<UserInformation> getUserInformationOneList(){
        List.of(getUserInformation())
    }

    static Session getSession(String key="key"){
        Session.builder().key(key).userInformations(getUserInformationOneList()).build()
    }
}
