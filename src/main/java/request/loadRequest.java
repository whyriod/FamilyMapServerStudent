package request;

import model.event;
import model.person;
import model.user;

public class loadRequest {
    private user[] users;
    private person[] persons;
    private event[] events ;

    public loadRequest(user[] users, person[] persons, event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    public user[] getUsers() {
        return users;
    }

    public void setUsers(user[] users) {
        this.users = users;
    }

    public person[] getPersons() {
        return persons;
    }

    public void setPersons(person[] persons) {
        this.persons = persons;
    }

    public event[] getEvents() {
        return events;
    }

    public void setEvents(event[] events) {
        this.events = events;
    }
}
