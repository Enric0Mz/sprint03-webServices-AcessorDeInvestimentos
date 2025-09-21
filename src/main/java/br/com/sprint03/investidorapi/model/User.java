package br.com.sprint03.investidorapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    @Column(nullable = false)
    private String name;

    @Email(message = "Formato de email inválido.")
    @NotBlank(message = "O email não pode estar em branco.")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "O perfil de risco não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Profile profile;

    public User() {
    }

    public User(String name, String email, LocalDate birthDate, Profile profile) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.profile = profile;
    }
}