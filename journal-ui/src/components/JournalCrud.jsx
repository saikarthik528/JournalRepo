import { useState } from "react";
import api from "../api/axiosConfig";
import JournalList from "./JournalList";

const JournalCrud = ({ load, notes }) => {
/* state definition  */
  const [id, setId] = useState("");
  const [noteHeading, setNoteHeading] = useState("");
  const [noteBody, setNoteBody] = useState("");
  const [noteDate, setNoteDate] = useState("");

  /* being handlers */
  async function save(event) {
    event.preventDefault();
    await api.post("/note", {
      noteHeading: noteHeading,
      noteBody: noteBody,
      noteDate: noteDate,
    });
    alert("Note Record Saved");
    // reset state
    setId("");
    setNoteHeading("");
    setNoteBody("");
    setNoteDate("");
    load();
  }
  async function editNote(notes) {
    setNoteHeading(notes.noteHeading)
    setNoteBody(notes.noteBody);
    setNoteDate(notes.noteDate);
    setId(notes.id);
  }

  async function deleteNote(id) {
    await api.delete("/note/" + id);
    alert("Note Details Deleted Successfully");
    load();
  }

  async function update(event) {
    event.preventDefault();
    if (!id) return alert("Note Details Not Found");
    await api.put("/note/" + id, {
      id: id,
      noteHeading: noteHeading,
      noteBody: noteBody,
      noteDate: noteDate,
    });
    alert("Note Details Updated");
    // reset state
    setId("");
    setNoteHeading("");
    setNoteBody("");
    setNoteDate("");
    load();
  }
  /* end handlers */

/* jsx */
  return (
    <div className="container mt-4">
      <form>
        <div className="form-group my-2">
          <input
            type="text"
            className="form-control"
            hidden
            value={id}
            onChange={e => setId(e.target.value)}
          />
          <label>Note Heading</label>
          <input
            type="text"
            className="form-control"
            value={noteHeading}
            onChange={e => setNoteHeading(e.target.value)}
          />
        </div>

        <div className="form-group mb-2">
          <label>Note Body</label>
          <textarea value={noteBody} 
            className="form-control"
            onChange={e => setNoteBody(e.target.value)} />
        </div>

        <div className="row">
          <div className="col-4">
            <label>Note Date</label>
            <input
              type="date"
              className="form-control"
              value={noteDate}
              onChange={e => setNoteDate(e.target.value)}
            />
          </div>
        </div>

        <div>
          <button className="btn btn-primary m-4" onClick={save}>
            Save
          </button>
          <button className="btn btn-warning m-4" onClick={update}>
            Update
          </button>
        </div>
      </form>
      <JournalList
        notes={notes}
        editNote={editNote}
        deleteNote={deleteNote}
      />
    </div>
  );
};

export default JournalCrud;