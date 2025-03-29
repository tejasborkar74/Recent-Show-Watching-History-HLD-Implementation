package com.example.show_history.controller;

import com.example.show_history.dto.SeriesInputDTO;
import com.example.show_history.entity.Show;
import com.example.show_history.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shows")
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping("/all_shows")
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @PostMapping("/create_movie")
    public ResponseEntity<?> createMovie(@RequestBody Show movie) {
        return ResponseEntity.ok(showService.createMovie(movie));
    }

    @PostMapping("/create_series")
    public ResponseEntity<?> createSeries(@RequestBody SeriesInputDTO series) {
        if(series.getTotalEpisodes() != series.getEpisodeRunningTimeInMin().size()) {
            return ResponseEntity.badRequest().body(Map.of("error", "total episodes should be equal to episodeRunningTime array"));
        }
        return  ResponseEntity.ok(showService.createSeries(series));
    }

}
