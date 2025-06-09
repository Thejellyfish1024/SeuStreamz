package bd.edu.seu.seustreamz.interfaces;

import bd.edu.seu.seustreamz.models.User;

import java.util.List;

public interface UserInterface {
    void insertUser(User user);
    User getUserByEmail(String email);
    List<User> getUsers();

}
