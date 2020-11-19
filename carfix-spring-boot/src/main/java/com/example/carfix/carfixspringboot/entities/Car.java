package com.example.carfix.carfixspringboot.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carId")
    private Long carId;

    @NonNull
    @NotBlank(message = "Series Name is mandatory")
    @Column(name = "seriesName")
    private String seriesName;

    @NonNull
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    @NotBlank(message = "Manufacture years are mandatory")
    @Column(name = "manufactureYear")
    private Date manufactureYear;

    @NonNull
    @NumberFormat
    @NotBlank(message = "Engine displacement is mandatory")
    @Column(name = "engineDisplacement_L")
    private Integer engineDisplacement;

    @NonNull
    @NumberFormat
    @NotBlank(message = "Engine power is mandatory")
    @Column(name = "enginePower_KW")
    private Integer enginePower;

    /*@OneToMany(mappedBy = "car")
    private List<Work> works;

    @OneToMany(mappedBy = "car")
    private List<Detail> details;*/
}
