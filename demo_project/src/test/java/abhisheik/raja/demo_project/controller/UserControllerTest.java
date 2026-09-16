package abhisheik.raja.demo_project.controller;

import abhisheik.raja.demo_project.model.BankUser;
import abhisheik.raja.demo_project.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userController = new UserController();
        // Inject service into controller
        try {
            var field = UserController.class.getDeclaredField("userService");
            field.setAccessible(true);
            field.set(userController, userService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 1. GET API TEST (Retrieve all bank users)
    @Test
    void testGetAllUsers() {
        ResponseEntity<List<BankUser>> response = userController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() >= 2);
    }

    // 1b. GET API TEST (Retrieve bank user by ID)
    @Test
    void testGetUserById() {
        ResponseEntity<?> response = userController.getUserById(101L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof BankUser);
        BankUser user = (BankUser) response.getBody();
        assertEquals("Alice Smith", user.getName());
    }

    // 2. POST API TEST (Add User - adduser)
    @Test
    void testAddUser() {
        BankUser newUser = new BankUser(null, "Charlie Brown", "charlie@bank.com", "ACC1003", 3000.00, "SAVINGS");
        ResponseEntity<BankUser> response = userController.addUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Charlie Brown", response.getBody().getName());
    }

    // 3. PUT API TEST (Update User - update)
    @Test
    void testUpdateUser() {
        BankUser updateInfo = new BankUser();
        updateInfo.setName("Alice Updated");
        updateInfo.setBalance(9999.99);

        ResponseEntity<?> response = userController.updateUser(101L, updateInfo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof BankUser);

        BankUser updatedUser = (BankUser) response.getBody();
        assertEquals("Alice Updated", updatedUser.getName());
        assertEquals(9999.99, updatedUser.getBalance());
    }

    // 4. DELETE API TEST (Remove User - remove)
    @Test
    void testRemoveUser() {
        ResponseEntity<String> response = userController.removeUser(101L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("successfully removed"));

        // Verify user no longer exists
        ResponseEntity<?> getResponse = userController.getUserById(101L);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}
