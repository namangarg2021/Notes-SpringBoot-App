package com.notesstore.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.notesstore.services.NotesService;

@Component
public class ExpiredSchedular {
	@Autowired
	NotesService notesService;

	@Scheduled(cron = "0 0 * * * *")
	public void markExpired() {
		notesService.deleteRecordsOlderThanOneHour();
	}
}
