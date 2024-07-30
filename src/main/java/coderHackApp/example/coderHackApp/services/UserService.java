package coderHackApp.example.coderHackApp.services;

import coderHackApp.example.coderHackApp.model.User;
import coderHackApp.example.coderHackApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()));
        return users;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setScore(0);
        user.setBadges(Set.of());
        return userRepository.save(user);
    }

    public User updateScore(String id, int score) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Invalid score");
        }
        user.setScore(score);
        updateBadges(user);
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private void updateBadges(User user) {
        Set<String> badges = user.getBadges();
        badges.clear();
        if (user.getScore() >= 60) {
            badges.add("Code Master");
        }
        if (user.getScore() >= 30) {
            badges.add("Code Champ");
        }
        if (user.getScore() > 0) {
            badges.add("Code Ninja");
        }
        user.setBadges(badges);
    }
}