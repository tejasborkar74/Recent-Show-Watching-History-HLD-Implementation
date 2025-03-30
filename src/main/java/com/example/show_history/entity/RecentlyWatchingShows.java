package com.example.show_history.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recently_watching_shows")
public class RecentlyWatchingShows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showId", nullable = false)
    private Show show;

    @Column(nullable = false)
    private int lastRegisteredTimeInMinutes;

    @Column(nullable = false)
    private int totalRunningMinutes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(nullable = false)
    private LocalDateTime updatedTime;

    public RecentlyWatchingShows(){}

    public RecentlyWatchingShows(User user, Show show, int lastRegistedTimeInMinutes, int totalRunningMinutes) {
        this.user = user;
        this.show = show;
        this.lastRegisteredTimeInMinutes = lastRegistedTimeInMinutes;
        this.totalRunningMinutes = totalRunningMinutes;
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() { return id; }

    public User getUser() { return user; }

    public Show getShow() { return show; }

    public int getLastRegisteredTimeInMinutes() { return lastRegisteredTimeInMinutes; }

    public int getTotalRunningMinutes() { return totalRunningMinutes; }

    public LocalDateTime getCreatedTime() { return createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }

    public void setLastRegisteredTimeInMinutes(int lastRegisteredTimeInMinutes) {
        this.lastRegisteredTimeInMinutes = lastRegisteredTimeInMinutes;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
}
