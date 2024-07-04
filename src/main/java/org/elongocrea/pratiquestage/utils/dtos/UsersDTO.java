package org.elongocrea.pratiquestage.utils.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "{form.id}")
    @PositiveOrZero(message = "{form.id}")
    private int id;

    @NotEmpty(message = "{form.username}")
    @Size(message = "{form.username}")
    private String username;

    @NotEmpty(message = "{form.email}")
    @Email(message = "{form.email}")
    @Size(message = "{form.email}")
    private String email;

    @NotEmpty(message = "{form.password}")
    @Size(message = "{form.password}")
    private String password;
    private boolean isActive = false;
    private boolean isBlock = false;
    private boolean isConnected = false;
    private LocalDateTime lastConnected = null;

    @NotEmpty(message = "{form.status}")
    @Size(message = "{form.status}")
    private String status;

    @NotEmpty(message = "{form.phone}")
    @Size(message = "{form.phone}")

    private String phone;
    private String googleId;

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsBlock() {
        return isBlock;
    }

    public boolean getIsConnected() {
        return isConnected;
    }
}
