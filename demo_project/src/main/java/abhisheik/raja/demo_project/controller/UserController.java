package abhisheik.raja.demo_project.controller;

import abhisheik.raja.demo_project.model.BankUser;
import abhisheik.raja.demo_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. GET API - Retrieve all bank users
    @GetMapping
    public ResponseEntity<List<BankUser>> getAllUsers() {
        List<BankUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 1b. GET API - Retrieve bank user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<BankUser> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + id);
        }
    }

    // 2. POST API - Add User (adduser)
    @PostMapping
    public ResponseEntity<BankUser> addUser(@RequestBody BankUser user) {
        BankUser createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // 3. PUT API - Update User Details (update)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody BankUser userDetails) {
        BankUser updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + id);
        }
    }

    // 4. DELETE API - Remove User (remove)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Long id) {
        boolean removed = userService.deleteUser(id);
        if (removed) {
            return ResponseEntity.ok("User with ID " + id + " has been successfully removed.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with ID: " + id);
        }
    }
}
