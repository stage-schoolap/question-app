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
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @Column(name = "is_active")
    private boolean isActive = false;

    @Column(name = "is_block")
    private boolean isBlock = false;

    @Column(name = "is_connected")
    private boolean isConnected = false;

    @Column(name = "last_connected")
    private Timestamp lastConnected = null;
    private String status;

    @NotEmpty
    private String phone;

    @Column(name = "google_id")
    private String googleId;
}
