package booknut.repositories.repositoriesImpl;

import booknut.entities.User;
import booknut.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void createUser(User user) {
        user.setId(this.users.size() + 1);
        this.users.add(user);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.users.stream().
                filter(user ->
                        user.getName().equals(username) &&
                                user.getPassword().equals(password)).
                findAny().orElse(null);
    }
}
