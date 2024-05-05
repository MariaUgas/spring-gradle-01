package maun.eval.demo.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    @Email(message = "El correo ingresado no es valido")
    private String email;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "La clave solo debe contener letras y numeros")
    private String password;

    @CreatedDate
    private LocalDateTime created;

    @LastModifiedDate
    private LocalDateTime modified;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    private String userToken;

    @OneToMany(mappedBy = "user", targetEntity=Phone.class)
    private List<Phone> phones=new ArrayList<Phone>();
    public User() {
    }

    // Constructor con todos los campos
    public User(UUID id, String name, String email, String password, LocalDateTime created,
                LocalDateTime modified, Boolean isActive, String userToken, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.modified = modified;
        this.isActive = isActive;
        this.userToken = userToken;
        this.phones = phones;
    }

    // Constructor sin el campo 'id'
    public User(String name, String email, String password, String userToken, List<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.isActive = true;
        this.userToken = userToken;
        this.phones = phones;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Boolean getIsactive() {
        return isActive;
    }

    public void setIsactive(Boolean isactive) {
        this.isActive = isactive;
    }
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getUsertoken() {
        return userToken;
    }

    public void setUsertoken(String usertoken) {
        this.userToken = usertoken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", isactive=" + isActive +
                ", phones=" + phones +
                '}';
    }
}
