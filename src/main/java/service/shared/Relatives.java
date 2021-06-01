package service.shared;

import dao.*;
import model.Event;
import model.Person;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/***
 * A group of shared functions that relate to personServices and eventServices
 * dealing with relatives.
 */
public class Relatives {



    /***
     * Grabs all related persons above you in the family tree by calling
     * getRelativesHelper.
     *
     * @param person - The current person object
     * @return relatives - A set of all relatives.
     * @throws DataAccessException - Database get errors
     */
    public Set<Person> getRelatives(Person person, PersonDAO pDAO) throws DataAccessException {
        Set<Person> relatives = new HashSet<>();
        Relatives service = new Relatives();
        service.getRelativesHelper(relatives, person, pDAO);
        return relatives;
    }



    /***
     * Grabs all related persons above you in the family tree.
     *
     * @param relatives - A set of relatives to add found relatives to
     * @param person - The current person object
     *
     * @throws DataAccessException - Database get errors
     */
    public void getRelativesHelper(Set<Person> relatives, Person person, PersonDAO pDAO) throws DataAccessException {

        //Check to make sure that the person exists.
        if(person != null && !contains(relatives,person)){
            relatives.add(person);
            Person father = pDAO.fetchPerson(person.getFatherID());
            Person mother = pDAO.fetchPerson(person.getMotherID());
            Person spouse = pDAO.fetchPerson(person.getSpouseID());

            //Check for other relatives.
            if(father != null){
                getRelativesHelper(relatives, father, pDAO);
            }
            if(mother != null){
                getRelativesHelper(relatives, mother, pDAO);
            }
            if(spouse != null){
                getRelativesHelper(relatives, spouse, pDAO);
            }
        }
    }



    /***
     * Checks to see if a set of persons contains a specific person. If
     * it does, true. Otherwise false.
     * @param relatives - The set of relatives person objects
     * @param person - The person whe are checking as a relative
     * @return - True for related, false for not.
     */
    public boolean contains(Set<Person> relatives, Person person){

        //If they are the same pointer, true
        for(Person p: relatives){
            if(p.equals(person)){
                return true;
            }
        }
        //Otherwise false
        return false;
    }



    /***
     * Loops through a set of relatives. Grabs all events for each relative, and adds them to
     * an a new set and returns.
     * @param relatives - Relatives to get events for.
     * @return - A set of all events for a family.
     * @throws DataAccessException - Throws if a DB issue. Caught in Service classes.
     */
    public Set<Event> getFamilyEvents(Set<Person> relatives, EventDAO eDAO) throws DataAccessException {

        Set<Event> familyEvents = new HashSet<>();
        ArrayList<Event> personalEvents;

        for(Person p: relatives){
            personalEvents = eDAO.fetchEvents(p.getPersonID());
            for(Event e: personalEvents){
                familyEvents.add(e);
            }
        }
        return familyEvents;
    }
}
