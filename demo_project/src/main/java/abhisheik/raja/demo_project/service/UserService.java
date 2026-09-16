package abhisheik.raja.demo_project.service;

import abhisheik.raja.demo_project.model.BankUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, BankUser> userMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(100);

    public UserService() {
        // Pre-populate with initial bank accounts/users for demonstration
        Long id1 = idGenerator.incrementAndGet();
        userMap.put(id1, new BankUser(id1, "Alice Smith", "alice@example.com", "ACC1001", 5000.00, "SAVINGS"));

        Long id2 = idGenerator.incrementAndGet();
        userMap.put(id2, new BankUser(id2, "Bob Jones", "bob@example.com", "ACC1002", 12500.50, "CHECKING"));
    }

    // 1. GET ALL USERS
    public List<BankUser> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    // 2. GET USER BY ID
    public Optional<BankUser> getUserById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    // 3. POST - ADD USER
    public BankUser addUser(BankUser user) {
        if (user.getId() == null) {
            user.setId(idGenerator.incrementAndGet());
        }
        if (user.getAccountNumber() == null || user.getAccountNumber().isEmpty()) {
            user.setAccountNumber("ACC" + user.getId());
        }
        if (user.getBalance() == null) {
            user.setBalance(0.0);
        }
        userMap.put(user.getId(), user);
        return user;
    }

    // 4. PUT - UPDATE USER
    public BankUser updateUser(Long id, BankUser updatedDetails) {
        BankUser existingUser = userMap.get(id);
        if (existingUser != null) {
            if (updatedDetails.getName() != null) {
                existingUser.setName(updatedDetails.getName());
            }
            if (updatedDetails.getEmail() != null) {
                existingUser.setEmail(updatedDetails.getEmail());
            }
            if (updatedDetails.getAccountNumber() != null) {
                existingUser.setAccountNumber(updatedDetails.getAccountNumber());
            }
            if (updatedDetails.getBalance() != null) {
                existingUser.setBalance(updatedDetails.getBalance());
            }
            if (updatedDetails.getAccountType() != null) {
                existingUser.setAccountType(updatedDetails.getAccountType());
            }
            userMap.put(id, existingUser);
            return existingUser;
        }
        return null;
    }

    // 5. DELETE - REMOVE USER
    public boolean deleteUser(Long id) {
        return userMap.remove(id) != null;
    }
}
