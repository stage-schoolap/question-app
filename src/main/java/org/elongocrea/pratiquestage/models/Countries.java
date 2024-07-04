package org.elongocrea.pratiquestage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Countries implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 1, max = 45)
    @Column(name = "name_fr")
    private String nameFr;

    @NotEmpty
    @Column(nullable = true, name = "name_en")
    @Size(min = 1, max = 45)
    @Builder.Default
    private String nameEn = null;

    @NotEmpty
    @Column(nullable = true)
    @Size(min = 1, max = 6)
    @Builder.Default
    private String prefix = null;

}
