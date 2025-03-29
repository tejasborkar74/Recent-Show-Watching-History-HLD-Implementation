package com.example.show_history.dto;

import java.util.List;

public class SeriesInputDTO {
    private String showName;
    private int totalEpisodes;
    private List<Integer> episodeRunningTimeInMin;

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public List<Integer> getEpisodeRunningTimeInMin() {
        return episodeRunningTimeInMin;
    }

    public String getShowName() {
        return showName;
    }
}
