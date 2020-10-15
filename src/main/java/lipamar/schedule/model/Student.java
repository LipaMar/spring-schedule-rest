package lipamar.schedule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "students", schema = "public")
@JsonIgnoreProperties("meetings")
public class Student extends BaseEntity {
    @Column(name = "first_name")
    @NotNull
    private String name;
    @Column(name = "last_name")
    @NotNull
    private String lastName;
    @Column(unique = true)
    @NotBlank
    private String email;
    @Column
    @NotNull
    private String password;
    @Column
    @NotNull
    private Integer semester;
    @Column(name = "study_course")
    @NotNull
    private String studyCourse;
    @Column(unique = true)
    @NotNull
    private String index;
    @Column
    private String role;
    @OneToMany(mappedBy = "student")
    private final Set<Presence> meetings = new HashSet<>();

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getStudyCourse() {
        return studyCourse;
    }

    public void setStudyCourse(String studyCourse) {
        this.studyCourse = studyCourse;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Meeting> getMeetings() {
        return meetings.stream().map(Presence::getMeeting).collect(Collectors.toSet());
    }
}
