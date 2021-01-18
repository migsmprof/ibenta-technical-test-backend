package au.com.ibenta.template;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import au.com.ibenta.test.persistence.UserEntity;
import au.com.ibenta.test.service.UserService;

public class UserServiceTest {
	private UserEntity testUser = new UserEntity();
	private UserService userCRUD = new UserService();
	private Optional<UserEntity> nullableUser = Optional.of(null);
	
    @Test
    public void testCreateUser() {
        testUser = userCRUD.create("Jose Miguel", "Marbella", "migsmprof@gmail.com", "hahahaha");

        // Tests whether UserEntity was created through JPA and Hibernation
        assertNotNull(testUser, "UserEntity testUser is not null");
        // Tests whether UserEntity was created through auto-generated ID
        assertNotNull(testUser.getId(),"UnitEntity testUser recorded");
        // Tests whether firstName value is correct
        assertEquals("Jose Miguel", testUser.getFirstName(), "firstName value is correct");
        // Tests whether lastName value is correct
        assertEquals("Marbella", testUser.getLastName(), "lastName value is correct");
        // Tests whether email value is correct
        assertEquals("migsmprof@gmail.com", testUser.getEmail(), "email value is correct");
        // Tests whether password value is correct
        assertEquals("hahahaha", testUser.getPassword(), "password value is correct");
    }
    
    @Test
    public void testGetUser() {
    	nullableUser = userCRUD.get(0L);
    	testUser = nullableUser.orElse(null);
    	
    	// Tests whether UserEntity was created through JPA and Hibernation
    	assertNotNull(testUser, "UserEntity testUser exists in database");
    	// Tests whether firstName value is correct
        assertEquals("Jose Miguel", testUser.getFirstName(), "firstName value is correct");
        // Tests whether lastName value is correct
        assertEquals("Marbella", testUser.getLastName(), "lastName value is correct");
        // Tests whether email value is correct
        assertEquals("migsmprof@gmail.com", testUser.getEmail(), "email value is correct");
        // Tests whether password value is correct
        assertEquals("hahahaha", testUser.getPassword(), "password value is correct");
    }
    
    @Test
    public void testUpdateUser() {
    	HashMap <String, String> newValues = new HashMap<String, String>();
    	
    	newValues.put("firstName", "Miguel Jose");
    	nullableUser = userCRUD.update(0L, newValues);
    	testUser = nullableUser.orElse(null);
    	
    	assertNotNull(testUser, "UserEntity testUser exists in database");
        assertEquals("Miguel Jose", testUser.getFirstName(), "firstName value changed");
        
        newValues.remove("firstName");
        newValues.put("lastName", "Bolante");
        nullableUser = userCRUD.update(0L, newValues);
    	testUser = nullableUser.orElse(null);
    	
    	assertNotNull(testUser, "UserEntity testUser exists in database");
        assertEquals("Miguel Jose", testUser.getFirstName(), "firstName value still same");
        assertEquals("Bolante", testUser.getFirstName(), "lastName value changed");
        
        newValues.remove("lastName");
        newValues.put("email", "migs7792@gmail.com");
        nullableUser = userCRUD.update(0L, newValues);
    	testUser = nullableUser.orElse(null);
    	
    	assertNotNull(testUser, "UserEntity testUser exists in database");
        assertEquals("Miguel Jose", testUser.getFirstName(), "firstName value still same");
        assertEquals("Bolante", testUser.getLastName(), "lastName still same");
        assertEquals("migs7792@gmail.com", testUser.getEmail(), "email value changed");
        
        newValues.remove("email");
        newValues.put("password", "wkwkwkwk");
        nullableUser = userCRUD.update(0L, newValues);
    	testUser = nullableUser.orElse(null);
    	
    	assertNotNull(testUser, "UserEntity testUser exists in database");
        assertEquals("Miguel Jose", testUser.getFirstName(), "firstName value still same");
        assertEquals("Bolante", testUser.getLastName(), "lastName still same");
        assertEquals("migs7792@gmail.com", testUser.getEmail(), "email still same");
        assertEquals("wkwkwkwk", testUser.getPassword(), "password value changed");
    }
    
    @Test
    public void testDeleteUser() {
    	userCRUD.delete(0L);
    	nullableUser = userCRUD.get(0L);
    	testUser = nullableUser.orElse(null);
    	
    	assertNull(testUser, "UserEntity has been deleted.");
    }
    
    @Test
    public void testListAllUsers() {
    	List<UserEntity> newUsers = new ArrayList<UserEntity>();
    	Boolean letsProceed = false;
    	
    	testUser = userCRUD.create("Jose Miguel", "Marbella", "migsmprof@gmail.com", "hahahaha");
    	letsProceed = newUsers.add(testUser);
    	
    	if (letsProceed) {
    		testUser = userCRUD.create("Migs", "Bolante", "migs7792@gmail.com", "hehehehe");
    		newUsers.add(testUser);
    		letsProceed = true;
    	}
    	
    	if (letsProceed) {
    		testUser = userCRUD.create("Mikhail", "Merville", "mrbellamguel@gmail.com", "wkwkwkwk");
    		newUsers.add(testUser);
    	}
    	
    	ArrayList<UserEntity> queriedUsers = userCRUD.list();
    	
    	assertEquals(newUsers.size(), queriedUsers.size());
    	assertEquals(newUsers.get(0).getPassword(), queriedUsers.get(0).getPassword());
    	assertFalse(newUsers.get(1).getLastName().equals(queriedUsers.get(2).getLastName()));
    }

}