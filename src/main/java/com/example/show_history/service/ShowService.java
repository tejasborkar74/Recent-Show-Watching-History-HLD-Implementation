package com.example.show_history.service;

import com.example.show_history.dto.SeriesInputDTO;
import com.example.show_history.entity.Show;
import com.example.show_history.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show createMovie(Show movie){
        movie.setEpisodeNo(1);
        movie.setTotalEpisodes(1);
        return showRepository.save(movie);
    }

    public List<Show> createSeries(SeriesInputDTO series) {
        List<Show> episodes = new ArrayList<>();
        List<Integer> episodeTime = series.getEpisodeRunningTimeInMin();
        for (int i = 1; i <= series.getTotalEpisodes(); i++) {
            episodes.add(new Show(series.getShowName(), episodeTime.get(i-1), i, series.getTotalEpisodes()));
        }
        return showRepository.saveAll(episodes);
    }
}
