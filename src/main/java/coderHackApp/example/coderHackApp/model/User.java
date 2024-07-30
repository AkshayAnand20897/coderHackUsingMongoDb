package coderHackApp.example.coderHackApp.model;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NonNull;


import java.util.HashSet;
import java.util.Set;
@Document(collection = "users")
public class User {
        @Id
        private String id;

        @NonNull
        private String username;

        @Min(0)
        @Max(100)
        private int score;

        public User() {

        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public @NonNull String getUsername() {
                return username;
        }

        public void setUsername(@NonNull String username) {
                this.username = username;
        }

        public int getScore() {
                return score;
        }

        public void setScore(int score) {
                this.score = score;
        }

        public Set<String> getBadges() {
                return badges;
        }

        public void setBadges(Set<String> badges) {
                this.badges = badges;
        }

        private Set<String> badges = new HashSet<>();

        public User(String id, @NonNull String username, int score, Set<String> badges) {
                this.id = id;
                this.username = username;
                this.score = score;
                this.badges = badges;
        }
    }

