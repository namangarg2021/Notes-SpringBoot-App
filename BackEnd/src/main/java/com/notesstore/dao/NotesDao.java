package com.notesstore.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notesstore.entites.Notes;

public interface NotesDao extends JpaRepository<Notes, Long> {
	List<Notes> findByCreationTimeBefore(LocalDateTime time);

	List<Notes> findByUserId(long userId);

	List<Notes> findTop10ByUserIdOrderByCreationTimeDesc(long userId);
}
