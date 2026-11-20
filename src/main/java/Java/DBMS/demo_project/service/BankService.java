package Bankmanagement.app.service;
import org.springframework.stereotype.Service;
import Bankmanagement.app.model.User;
import java.util.List;
import java.util.ArrayList;

@Service
public class BankService {

    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;

    }
}