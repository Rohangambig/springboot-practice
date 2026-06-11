package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class userModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;
}
