package org.elongocrea.pratiquestage.utils.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CountriesDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "{form.id}")
    @PositiveOrZero(message = "{form.id}")
    private int id;

    @Size(min = 1, max = 45)
    private String nameFr;

    @NotEmpty
    @Column(nullable = true)
    @Size(min = 1, max = 45)
    @Builder.Default
    private String nameEn = null;

    @NotEmpty
    @Column(nullable = true)
    @Size(message = "{form.prefix}")
    @Builder.Default
    private String prefix = null;
}
