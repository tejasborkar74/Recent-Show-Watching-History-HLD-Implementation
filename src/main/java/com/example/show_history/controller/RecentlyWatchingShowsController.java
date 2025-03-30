package com.example.show_history.controller;

import com.example.show_history.dto.UpdateUserShowProgressInputDto;
import com.example.show_history.entity.RecentlyWatchingShows;
import com.example.show_history.service.RecentlyWatchingShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/recently_watching")
public class RecentlyWatchingShowsController {

    @Autowired
    private RecentlyWatchingShowsService recentlyWatchingShowsService;

    @GetMapping("/getAllRecentShowsForUser/{userId}")
    public ResponseEntity<?> getAllRecentShowsForUser(@PathVariable Long userId) {
        try {
            List<RecentlyWatchingShows> recentShows = recentlyWatchingShowsService.getAllRecentShowsForUser(userId);
            return ResponseEntity.ok(recentShows);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/updateUserShowProgress")
    public ResponseEntity<?> updateUserShowProgress(
            @RequestBody UpdateUserShowProgressInputDto request) {
        try {
            recentlyWatchingShowsService.updateUserShowProgress(request);
            return ResponseEntity.ok(Map.of("message", "Progress updated successfully."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
