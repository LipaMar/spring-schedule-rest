package lipamar.schedule.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student extends BaseEntity{
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Integer semester;
    @Column
    private String studyCourse;
    @Column
    private String index;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_MEETING",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "meeting_id")})
    private Set<Meeting> meetings;

    public Student(){

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) &&
                lastName.equals(student.lastName) &&
                Objects.equals(email, student.email) &&
                password.equals(student.password) &&
                semester.equals(student.semester) &&
                studyCourse.equals(student.studyCourse) &&
                index.equals(student.index) &&
                Objects.equals(meetings, student.meetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, email, password, semester, studyCourse, index, meetings);
    }
}
