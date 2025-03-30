package com.example.show_history.repository;

import com.example.show_history.entity.RecentlyWatchingShows;
import com.example.show_history.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentlyWatchingShowsRepository extends JpaRepository<RecentlyWatchingShows, Long> {
    List<RecentlyWatchingShows> findByUserOrderByUpdatedTimeDesc(User user);
    Optional<RecentlyWatchingShows> findByUserAndShowShowName(User user, String showName);;
}