package com.example.show_history.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @Column(nullable = false)
    private String showName;

    @Column(nullable = false)
    private int totalRunningMinutes;

    @Column(nullable = false)
    private int episodeNo;

    @Column(nullable = false)
    private int totalEpisodes;

    // constructors
    public Show(){}
    public Show(String showName, int totalRunningMinutes) {
        this.showName = showName;
        this.totalRunningMinutes = totalRunningMinutes;
    }
    public Show(String showName, int totalRunningMinutes, int episodeNo, int totalEpisodes) {
        this.showName = showName;
        this.totalRunningMinutes = totalRunningMinutes;
        this.episodeNo = episodeNo;
        this.totalEpisodes = totalEpisodes;
    }

    // getters and setters
    public Long getShowId(){
        return showId;
    }
    public String getShowName() {
        return showName;
    }
    public int getTotalRunningMinutes() {
        return totalRunningMinutes;
    }
    public int getTotalEpisodes(){
        return totalEpisodes;
    }
    public int getEpisodeNo(){
        return episodeNo;
    }
    public void setEpisodeNo(int episodeNo) {
        this.episodeNo = episodeNo;
    }
    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }
}