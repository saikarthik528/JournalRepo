package com.kworks.journal.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="notes")
public class Note {

    private Long id;
    private String noteHeading;
    private String noteBody;
    private Date noteDate;
    private Timestamp createDate;
    private Timestamp modifyDate;

    protected Note() {}

    public Note(String noteHeading, String noteBody, Date noteDate) {
        this.noteHeading = noteHeading;
        this.noteBody = noteBody;
        this.noteDate = noteDate;
    }

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "note_heading", nullable = false)
    public String getNoteHeading() {
        return noteHeading;
    }

    public void setNoteHeading(String noteHeading) {
        this.noteHeading = noteHeading;
    }

    @Column(name = "note_body", nullable = false)
    public String getNoteBody() {
        return noteBody;
    }

    public void setNoteBody(String noteBody) {
        this.noteBody = noteBody;
    }

    @Column(name = "note_date", nullable = false)
    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", nullable = false)
    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteHeading='" + noteHeading + '\'' +
                ", noteBody='" + noteBody + '\'' +
                ", noteDate=" + noteDate +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
