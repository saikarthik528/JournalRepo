import React from "react";

const JournalList = ({ notes, editNote, deleteNote }) => {
  return (
    <table className="table table-hover mt-3" align="center">
      <thead className="thead-light">
        <tr>
          <th scope="col">Nº</th>
          <th scope="col">Note Heading</th>
          <th scope="col">Note Body</th>
          <th scope="col">Note Date</th>

          <th scope="col">Options</th>
        </tr>
      </thead>
      {notes.map((note, index) => {
        return (
          <tbody key={note.id}>
            <tr>
              <th scope="row">{index + 1} </th>
              <td>{note.noteHead}</td>
              <td>{note.noteBody}</td>
              <td>{note.noteDate}</td>
              <td>
                <button
                  type="button"
                  className="btn btn-warning"
                  onClick={() => editNote(note)}
                >
                  Edit
                </button>
                <button
                  type="button"
                  className="btn btn-danger mx-2"
                  onClick={() => deleteNote(note.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          </tbody>
        );
      })}
    </table>
  );
};

export default JournalList;