package bd.edu.seu.seustreamz.interfaces;

import bd.edu.seu.seustreamz.models.User;

public interface LoginRegistrationInterface {
    void insertUser(User user);
    User getUserByEmail(String email);
}
