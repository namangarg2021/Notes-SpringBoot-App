package com.notesstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notesstore.dao.NotesDao;
import com.notesstore.entites.Notes;
import com.notesstore.entites.Users;

@Service
public class NotesServiceImpl implements NotesService {
	@Autowired
	private NotesDao notesDao;
	@Autowired
	private UsersService usersService;

	@Override
	public boolean addNote(Notes note) {
		notesDao.save(note);
		return true;
	}

	@Override
	public List<Notes> getNotesByUserId(long userId) {
		return notesDao.findByUserId(userId);
	}

	@Override
	public List<Notes> getAllNotes() {
		return notesDao.findAll();
	}

	@Override
	public void deleteRecordsOlderThanOneHour() {
		List<Users> users = usersService.getAllUsers();
		for (Users user : users) {
			List<Notes> notes = notesDao.findByUserId(user.getUserId());

			if (notes.size() > 10) {
				List<Notes> notesToKeep = notesDao.findTop10ByUserIdOrderByCreationTimeDesc(user.getUserId());
				for (Notes note : notes)
					if (!notesToKeep.contains(note))
						notesDao.delete(note);
			}
		}
	}

	@Override
	public List<Notes> getTop10ByUserId(long userId) {
		List<Notes> list = notesDao.findTop10ByUserIdOrderByCreationTimeDesc(userId);
		return list;
	}

	@Override
	public boolean deletebyNoteId(long noteId) {
		notesDao.deleteById(noteId);
		return true;
	}
}
