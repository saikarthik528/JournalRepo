import "bootstrap/dist/css/bootstrap.css";
import api from "./api/axiosConfig";
import { useEffect, useState } from "react";
import "./App.css";
import JournalCrud from "./components/JournalCrud";

function App() {
  const [notes, setNotes] = useState([]);

  /* manage side effects */
  useEffect(() => {
    (async () => await load())();
  }, []);

  async function load() {
    const result = await api.get("/note/getAll");
    setNotes(result.data);
  }

  return (
    <div>
      <h1 className="text-center">List Of Journal notes</h1>
      <JournalCrud load={load} notes={notes} />
    </div>
  );
}

export default App;