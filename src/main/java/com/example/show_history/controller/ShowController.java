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
    public ResponseEntity<?> getAllShows() {
        try {
            List<Show> allShows= showService.getAllShows();
            return ResponseEntity.ok(allShows);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{showId}")
    public ResponseEntity<?> getShowById(@PathVariable Long showId){
        try {
            return ResponseEntity.ok(showService.getShowById(showId));
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/create_movie")
    public ResponseEntity<?> createMovie(@RequestBody Show movie) {
        try{
            return ResponseEntity.ok(showService.createMovie(movie));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/create_series")
    public ResponseEntity<?> createSeries(@RequestBody SeriesInputDTO series) {
        try{
            if(series.getTotalEpisodes() != series.getEpisodeRunningTimeInMin().size()) {
                return ResponseEntity.badRequest().body(Map.of("error", "total episodes should be equal to episodeRunningTime array"));
            }
            return  ResponseEntity.ok(showService.createSeries(series));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

    }

}
