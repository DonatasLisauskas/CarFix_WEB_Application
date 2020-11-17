package entities;
import Validation.Regexp.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static Validation.Regexp.WORK_TIME;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "carServices")
public class CarService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carServiceId")
    private Long serviceId;

    @NonNull
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NonNull
    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    private String address;

    @NonNull
    @Pattern(regexp = WORK_TIME, message = "Invalid work time format, must be \"Day HH:MM-HH:MM\"")
    @NotBlank(message = "Address is mandatory")
    @Column(name = "workTime")
    private String workTime;

    @NonNull
    @NumberFormat
    @NotBlank(message = "Employee number is mandatory")
    @Column(name = "employeesNum")
    private Long employeesNum;

  /*  @OneToMany(mappedBy = "carService")
    private List<Work> works;
*/
}

