package entities;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import static Validation.Regexp.PHONE_NUMBER;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table( name = "customers" )
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    private Long customerId;

    @NonNull
    @NotBlank(message = "Name is mandatory")
    @Column(name = "firstName")
    private String firstName;

    @NonNull
    @NotBlank(message = "Second name is mandatory")
    @Column(name = "lastName")
    private String lastName;

    @Email
    @NonNull
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @NonNull
    @NumberFormat
    @Pattern(regexp = PHONE_NUMBER, message = "Invalid phone format, must be \"Country code +370-00000000 \"")
    @NotBlank(message = "Phone number is mandatory")
    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    /*@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Registration> registrations;*/
}
