package org.elongocrea.pratiquestage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "people")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class People implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 1, max = 45)
    private String firstname;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Builder.Default
    private String middlename = null;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Builder.Default
    private String lastname = null;

    @NotEmpty
    @Size(min = 1, max = 8)
    @Builder.Default
    private String gender = null;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Builder.Default
    private String birth_place = null;

    @NotEmpty
    @Builder.Default
    private String birth_date = null;

    @NotNull
    @Min(1)
    private int user_id;

    @NotEmpty
    @Lob
    @Builder.Default
    private byte[] photo = null;

    @NotEmpty
    @Size(min = 1, max = 25)
    @Builder.Default
    private String nationality = null;

    @NotEmpty
    @Min(1)
    @Column(nullable = true)
    private int country_id;

    @NotEmpty
    @Min(1)
    @Column(nullable = true)
    private int city_id;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Builder.Default
    private String address = null;
}
