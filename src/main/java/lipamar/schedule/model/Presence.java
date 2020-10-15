package lipamar.schedule.model;


import javax.persistence.*;

@Entity
@Table(name = "students_meetings", schema = "public")
public class Presence extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "m_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "s_id")
    private Meeting meeting;

    @Column
    private Boolean present;

    public Presence() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public Boolean wasPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
