package edu.matc.persistence;

import edu.matc.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;


public class UserDaoTest {

    UserDao dao;
    int initialRecordAmount;

    @Before
    public void setup() {
        dao = new UserDao();
        initialRecordAmount = dao.getAllUsers().size();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUser() throws Exception {
        User user = dao.getUser(1);
        assertTrue(user.getUserid() == 1);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setFirstName("Mark");
        user.setLastName("Grant");
        user.setEmailAddress("mark.grant@CDW.com");
        user.setInstrument("Triangle");
        int userID = dao.addUser(user);

        assertTrue(user.getUserid() == 2);
    }

    @Test
    public void deleteUser() throws Exception {
        User user = dao.getUser(1);
        dao.deleteUser(1);
        List<User> users = dao.getAllUsers();

        assertFalse(users.contains(user));
    }

    @Test
    public void updateUser() throws Exception {
        User user = dao.getUser(2);
        String newFirstName = "Bob";
        String newLastName = "Smith";
        String newEmail = "bob.smith@charter.net";
        String newInstrument = "French Horn";

        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setEmailAddress(newEmail);
        user.setInstrument(newInstrument);

        dao.updateUser(user);

        assertTrue(dao.getUser(2).getFirstName().equals(newFirstName));
        assertTrue(dao.getUser(2).getLastName().equals(newLastName));
        assertTrue(dao.getUser(2).getEmailAddress().equals(newEmail));
        assertTrue(dao.getUser(2).getInstrument().equals(newInstrument));

    }

}