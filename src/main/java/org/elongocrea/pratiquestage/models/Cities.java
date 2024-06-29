package org.elongocrea.pratiquestage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cities implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Min(1)
    @Column(nullable = true)
    private int country_id;

    @NotEmpty
    @Size(min = 1, max = 45)
    private String name_fr;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Builder.Default
    private String name_en = null;


}
