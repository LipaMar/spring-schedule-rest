package lipamar.schedule.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "meetings", schema = "public")
@JsonIgnoreProperties("signedUpStudents")
public class Meeting extends BaseEntity {
    @Column
    @NotBlank
    private String title;
    @Column
    private String description;
    @Column(name = "lecturer_info")
    private String lecturerInfo;
    @Column
    @Future
    private Date date;
    @OneToMany(mappedBy = "meeting")
    private final Set<Presence> signedUpStudents = new HashSet<>();

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

    public Set<Student> getSignedUpStudents() {
        return signedUpStudents.stream().map(Presence::getStudent).collect(Collectors.toSet());
    }
}
