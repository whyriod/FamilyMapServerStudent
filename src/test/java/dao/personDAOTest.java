package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class personDAOTest {

    private Database db;
    private Person actual;
    private personDAO pDAO;
    //5 Test Cases

    @BeforeAll
    public void loadDB(){
        //load
        //Be aware of order.

    }

    @BeforeEach
    public void setUp() throws DataAccessException, ClassNotFoundException {
        final String driver = "org.sqlite.JDBC";
        Class.forName(driver);
        db = new Database();
        actual = null;
        Connection conn = db.getConnection();
        pDAO = new personDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void fetchPersonPass() throws DataAccessException {
        Person expected = new Person("1","Adam","Adam","TheFirst",
                "M",null,null,null);
        actual = pDAO.fetch("1");
        assertEquals(expected.getPersonID(),actual.getPersonID());
        assertEquals(expected.getAssociatedUsername(),actual.getAssociatedUsername());
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getGender(),actual.getGender());
        assertEquals(expected.getFatherID(),actual.getFatherID());
        assertEquals(expected.getMotherID(),actual.getMotherID());
        assertEquals(expected.getSpouseID(),actual.getSpouseID());
    }

    @Test
    void fetchPersonFail() throws DataAccessException {
        Person actual = pDAO.fetch("Not this One");
        assertNull(actual);
    }

    @Test
    void insertPersonPass() throws DataAccessException {
        Person expected = new Person("2","Eve","Eve",
                "FirstWoman","F",null, null, null);
        pDAO.insert(expected);
        Person actual = pDAO.fetch("2");
        assertEquals(expected.getPersonID(),actual.getPersonID());
        assertEquals(expected.getAssociatedUsername(),actual.getAssociatedUsername());
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getGender(),actual.getGender());
        assertEquals(expected.getFatherID(),actual.getFatherID());
        assertEquals(expected.getMotherID(),actual.getMotherID());
        assertEquals(expected.getSpouseID(),actual.getSpouseID());
    }

    @Test
    void insertPersonFail() throws DataAccessException {
        actual = new Person("","","","","","","","");
        assertThrows(DataAccessException.class, () ->{pDAO.insert(actual);});
    }

    @Test
    void clear() throws DataAccessException {
        Person expected = new Person("3","ME","ME",
                "ME","M",null, null, null);
        pDAO.insert(expected);
        pDAO.clear();
        Person actual = pDAO.fetch("3");
        assertNull(actual);
    }
}
