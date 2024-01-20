package com.notesstore.entites;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "notes")
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteId;

	@Column(name = "note")
	private String note;

	@CreationTimestamp
	@Column(name = "creationTime", updatable = false)
	private LocalDateTime creationTime;

	@Column(name = "userId")
	private long userId;

	@ManyToOne
//	@JoinColumn(name = "user_id")
	private Users user;

	public Notes() {

	}

	public Notes(long noteId, String note, LocalDateTime creationTime, long userId, Users user) {
		super();
		this.noteId = noteId;
		this.note = note;
		this.creationTime = creationTime;
		this.userId = userId;
		this.user = user;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Notes [noteId=" + noteId + ", note=" + note + ", creationTime=" + creationTime + ", userId=" + userId
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTime, note, noteId, user, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notes other = (Notes) obj;
		return Objects.equals(creationTime, other.creationTime) && Objects.equals(note, other.note)
				&& noteId == other.noteId && Objects.equals(user, other.user) && userId == other.userId;
	}

}
