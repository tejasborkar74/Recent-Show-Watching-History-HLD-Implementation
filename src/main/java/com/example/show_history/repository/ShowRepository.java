package com.example.show_history.repository;

import com.example.show_history.entity.Show;
import com.example.show_history.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
