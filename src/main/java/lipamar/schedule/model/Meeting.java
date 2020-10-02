package lipamar.schedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.Set;

@Entity
public class Meeting extends BaseEntity{
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String lecturerInfo;
    @Column
    private Date date;
    @ManyToMany(mappedBy = "meetings")
    private Set<Student> presentStudents;

    public Meeting() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getLecturerInfo() {
        return lecturerInfo;
    }

    public void setLecturerInfo(String lecturerInfo) {
        this.lecturerInfo = lecturerInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
