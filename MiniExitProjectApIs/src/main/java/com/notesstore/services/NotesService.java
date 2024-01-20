package com.notesstore.services;

import java.util.List;

import com.notesstore.entites.Notes;

public interface NotesService {

	List<Notes> getAllNotes();

	boolean addNote(Notes note);

	void deleteRecordsOlderThanOneHour();

	List<Notes> getNotesByUserId(long userId);

	public List<Notes> getTop10ByUserId(long userId);
	
	public boolean deletebyNoteId(long noteId);
}
