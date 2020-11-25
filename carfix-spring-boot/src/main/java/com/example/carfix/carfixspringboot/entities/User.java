package com.example.carfix.carfixspringboot.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.example.carfix.carfixspringboot.Validation.Regexp.PHONE_NUMBER;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phoneNumber"),
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Firstname is mandatory!")
    private String firstname;

    @NonNull
    @NotBlank(message = "Lastname is mandatory!")
    private String lastname;

    @NonNull
    @NotBlank(message = "Email is mandatory!")
    @Email(message = "Invalid Email!")
    @Size(max = 50)
    private String email;

    @NonNull
    @NotBlank(message = "Phone number is mandatory!")
    @Pattern(regexp = PHONE_NUMBER, message = "Invalid phone format, must be +370-00000000")
    @Size(max = 20)
    private String phoneNumber;

    @NonNull
    @NotBlank(message = "Username is mandatory!")
    @Size(min = 3, max = 20)
    private String username;

    @NonNull
    @NotBlank(message = "Password is mandatory!")
    @Size(min = 6)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
