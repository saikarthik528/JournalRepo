package com.kworks.journal.rest;

import jakarta.validation.Valid;
import com.kworks.journal.persistence.entity.Note;
import com.kworks.journal.persistence.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/journal/v1")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/note/getAll")
    public List<Note> getAllNotes() {
        return noteRepository.findAll(Sort.by(Sort.Direction.DESC, "noteDate", "id"));
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId)
            throws Exception {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new Exception("Note not found for this id :: " + noteId));
        return ResponseEntity.ok().body(note);
    }

    @PostMapping("/note")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/note/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
                                                   @Valid @RequestBody Note noteDetails) throws Exception {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new Exception("Note not found for this id :: " + noteId));

        note.setNoteHeading(noteDetails.getNoteHeading());
        note.setNoteBody(noteDetails.getNoteBody());
        note.setNoteDate(noteDetails.getNoteDate());
        final Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/note/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "id") Long noteId)
            throws Exception {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new Exception("Note not found for this id :: " + noteId));

        noteRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
