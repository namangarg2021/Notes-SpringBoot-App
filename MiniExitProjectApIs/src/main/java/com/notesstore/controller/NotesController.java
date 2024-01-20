package com.notesstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.notesstore.entites.Notes;
import com.notesstore.services.NotesService;

@RestController
@CrossOrigin
public class NotesController {
	@Autowired
	NotesService notesService;

	@PostMapping(path = "/notes", consumes = { "application/json" })
	public ResponseEntity<String> addNote(@RequestBody Notes note) {
		notesService.addNote(note);
		return ResponseEntity.ok("Note added Successfully!");
	}

	@GetMapping("/notes/id/{id}")
	public List<Notes> getNotesByUserId(@PathVariable("id") Long userId) {
		return notesService.getTop10ByUserId(userId);
	}

	@GetMapping(path = "/notes")
	public List<Notes> allNotes() {
		return notesService.getAllNotes();
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<String> deleteNoteByNoteId(@PathVariable("id") Long noteId) {
		notesService.deletebyNoteId(noteId);
		return ResponseEntity.ok("Note deleted Successfully!");
	}
}
