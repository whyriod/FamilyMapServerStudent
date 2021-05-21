package dao;

import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class userDAOTest {

    private Database db;
    private User actual;
    private userDAO uDAO;
    //5 Test Cases

    @BeforeEach
    public void setUp() throws DataAccessException, ClassNotFoundException {
        final String driver = "org.sqlite.JDBC";
        Class.forName(driver);
        db = new Database();
        actual = null;
        Connection conn = db.getConnection();
        uDAO = new userDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    void fetchPass() throws DataAccessException {
        User expected = new User("2","Eve","I <3 Adam","null@null.com",
                "Eve","FirstWoman","F");
        actual = uDAO.fetch("Eve","I <3 Adam");
        assertEquals(expected.getPersonID(),actual.getPersonID());
        assertEquals(expected.getUsername(),actual.getUsername());
        assertEquals(expected.getPassword(),actual.getPassword());
        assertEquals(expected.getEmail(),actual.getEmail());
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getGender(),actual.getGender());
    }

    @Test
    void fetchFail() throws DataAccessException {
        assertNull(uDAO.fetch("Nope","Not this one"));
    }

    @Test
    void insertPass() throws DataAccessException {
        User expected = new User("1","Adam","I <3 Eve","null@null.com",
                "Adam","FirstMan","M");
        uDAO.insert(expected);
        actual = uDAO.fetch("Adam","I <3 Eve");
        assertEquals(expected.getPersonID(),actual.getPersonID());
        assertEquals(expected.getUsername(),actual.getUsername());
        assertEquals(expected.getPassword(),actual.getPassword());
        assertEquals(expected.getEmail(),actual.getEmail());
        assertEquals(expected.getFirstName(),actual.getFirstName());
        assertEquals(expected.getLastName(),actual.getLastName());
        assertEquals(expected.getGender(),actual.getGender());
    }

    @Test
    void insertFail(){
        User actual = new User("","","","","","","");
        assertThrows(DataAccessException.class, () ->{uDAO.insert(actual);});
    }

    @Test
    void clear() throws DataAccessException {
        User expected = new User("3","ME","I <3 ME","null@null.com",
                "ME","ME","M");
        uDAO.insert(expected);
        uDAO.clear();
        User actual = uDAO.fetch("Eve","I <3 Adam");
        assertNull(actual);
    }
}

