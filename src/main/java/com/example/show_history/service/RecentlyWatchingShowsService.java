package com.example.show_history.service;

import com.example.show_history.dto.UpdateUserShowProgressInputDto;
import com.example.show_history.entity.RecentlyWatchingShows;
import com.example.show_history.entity.Show;
import com.example.show_history.entity.User;
import com.example.show_history.repository.RecentlyWatchingShowsRepository;
import com.example.show_history.repository.ShowRepository;
import com.example.show_history.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecentlyWatchingShowsService {
    private static final Logger log = LoggerFactory.getLogger(RecentlyWatchingShowsService.class);
    private final RecentlyWatchingShowsRepository recentlyWatchingShowsRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    @Autowired
    public RecentlyWatchingShowsService(RecentlyWatchingShowsRepository recentlyWatchingShowsRepository,
                                        UserRepository userRepository,
                                        ShowRepository showRepository) {
        this.recentlyWatchingShowsRepository = recentlyWatchingShowsRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    public List<RecentlyWatchingShows> getAllRecentShowsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return recentlyWatchingShowsRepository.findByUserOrderByUpdatedTimeDesc(user);
    }

    @Transactional
    public void updateUserShowProgress(UpdateUserShowProgressInputDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));


        // Fetch an existing entry based on userId and showName
        Optional<RecentlyWatchingShows> existingEntry = recentlyWatchingShowsRepository
                .findByUserAndShowShowName(user, show.getShowName());


        if (existingEntry.isPresent()) {
            RecentlyWatchingShows existingShow = existingEntry.get();
            if(!Objects.equals(request.getShowId(), existingShow.getShow().getShowId())){
                System.out.println(">> CASE 1 <<");
                // case: same series different episode
                // update the existing entry with this episode

                // Remove current entry
                recentlyWatchingShowsRepository.delete(existingShow);
                Show currShow = showRepository.findById(request.getShowId())
                        .orElseThrow(() -> new RuntimeException("Show not found"));

                // Insert the new entry for this show
                RecentlyWatchingShows newEntry = new RecentlyWatchingShows(
                        user,
                        currShow,
                        request.getCurrentTimeInMinutes(),
                        currShow.getTotalRunningMinutes()
                );

                recentlyWatchingShowsRepository.save(newEntry);
                return;
            }

            System.out.println(request.getShowId());
            System.out.println(existingShow.getShow().getShowId());


            if (request.getCurrentTimeInMinutes() >= existingShow.getTotalRunningMinutes()) {
                // Remove current entry
                recentlyWatchingShowsRepository.delete(existingShow);

                // Find next episode
                Optional<Show> nextEpisode = showRepository.findAll()
                        .stream()
                        .filter(s -> s.getShowName().equals(show.getShowName()) && s.getEpisodeNo() == show.getEpisodeNo() + 1)
                        .findFirst();

                // Insert next episode if exists
                nextEpisode.ifPresent(s -> {
                    RecentlyWatchingShows newEntry = new RecentlyWatchingShows(user, s, 0, s.getTotalRunningMinutes());
                    recentlyWatchingShowsRepository.save(newEntry);
                });
            } else {
                System.out.println(request.getCurrentTimeInMinutes());
                System.out.println(request.getShowId());
                System.out.println(request.getUserId());

                existingShow.setLastRegisteredTimeInMinutes(request.getCurrentTimeInMinutes());
                existingShow.setUpdatedTime(LocalDateTime.now());
                recentlyWatchingShowsRepository.save(existingShow);
            }
        } else {
            RecentlyWatchingShows newEntry = new RecentlyWatchingShows(
                    user, show, request.getCurrentTimeInMinutes(), show.getTotalRunningMinutes());
            recentlyWatchingShowsRepository.save(newEntry);
        }
    }

}
