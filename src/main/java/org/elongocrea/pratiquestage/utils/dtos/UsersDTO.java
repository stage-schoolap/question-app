package org.elongocrea.pratiquestage.utils.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{form.id}")
    @PositiveOrZero(message = "{form.id}")
    private int id;

    @NotEmpty(message = "{form.username}")
    @Size(min = 1, max = 45, message = "{form.username}")
    private String username;

    @NotEmpty(message = "{form.email}")
    @Email(message = "{form.email}")
    @Size(min = 1, max = 45, message = "{form.email}")
    private String email;

    @NotEmpty(message = "{form.password}")
    @Size(min = 1, max = 45, message = "{form.password}")
    private String password;

    @NotNull
    @Builder.Default
    private boolean is_active = false;

    @NotNull
    @Builder.Default
    private boolean is_block = false;

    @NotNull
    @Builder.Default
    private boolean is_connected = false;

    @NotEmpty
    @Builder.Default
    private LocalDateTime last_connected = null;

    @NotEmpty(message = "{form.status}")
    @Size(min = 1, max = 45, message = "{form.status}")
    private String status;

    @NotEmpty(message = "{form.phone}")
    @Size(min = 1, max = 45, message = "{form.phone}")
    private String phone;

    private String google_id;
}
