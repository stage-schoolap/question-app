package org.elongocrea.pratiquestage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Users implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 1, max = 45)
    private String username;

    @NotEmpty
    @Email
    @Size(min = 1, max = 50)
    private String email;

    @NotEmpty
    @Size(min = 1, max = 45)
    private String password;

    @NotEmpty
    @Builder.Default
    private boolean is_active = false;

    @NotEmpty
    @Builder.Default
    private boolean is_block = false;

    @NotEmpty
    @Builder.Default
    private boolean is_connected = false;

    @NotEmpty
    @Builder.Default
    private Timestamp last_connected = null;

    @Size(min = 1, max = 45)
    private String status;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String phone;

    @NotEmpty
    @Size(min = 1, max = 45)
    private String google_id;
}
