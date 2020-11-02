package lipamar.schedule.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students_meetings", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = {"m_id", "s_id"}))
public class Presence extends BaseEntity {

    @ManyToOne( optional = false )
    @JoinColumn(name = "s_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "m_id")
    private Meeting meeting;

    @Column
    private Boolean present = false;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Presence presence = (Presence) o;
        return Objects.equals(getId(), presence.getId()) &&
                Objects.equals(student, presence.student) &&
                Objects.equals(meeting, presence.meeting) &&
                Objects.equals(present, presence.present);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, meeting, present);
    }
}
