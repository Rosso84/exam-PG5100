package org.exam.backend.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 100)
    private String category;

    @NotNull
    @Size(min = 5, max = 250)
    private String title;

    @NotNull
    @Size(max = 5000)
    private String description;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Rank> rankings;



    public Item(){
        if (rankings == null){
            rankings = new ArrayList<>();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Rank> getRankings() {
        return rankings;
    }

    public void setRankings(List<Rank> rankings) {
        this.rankings = rankings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
