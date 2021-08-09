package org.exam.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Rank {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @ManyToOne
    private Item item;

    @NotNull
    @Max(50)
    private Integer score;

    @Size(min = 1, max = 5000)
    private String comment;

    @NotNull
    private LocalDate dateCommented;



    public Rank(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(LocalDate date) {
        this.dateCommented = date;
    }
}
