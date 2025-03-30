package com.example.show_history.dto;

public class UpdateUserShowProgressInputDto {
    private Long userId;
    private Long showId;
    private int currentTimeInMinutes;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }

    public int getCurrentTimeInMinutes() { return currentTimeInMinutes; }
    public void setCurrentTimeInMinutes(int currentTimeInMinutes) { this.currentTimeInMinutes = currentTimeInMinutes; }
}
