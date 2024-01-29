package entities;

import javax.persistence.*;

@Entity
@Table(name = "students")
@PersistenceContext(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String fullname;
    @Column(name = "email", nullable = false, unique = true)
    public String email;
    public String telephone;
    @Column(columnDefinition = "TEXT")
    public String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    public Classes classes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
