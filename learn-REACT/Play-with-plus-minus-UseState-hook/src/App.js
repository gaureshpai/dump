import './App.css';
import {  useState } from 'react';

function App() {
  const [counter, setcounter] = useState(0);

  return (
    <div className="App">
        <button onClick={() => setcounter(()=> counter + 1)}>+</button>
        <h1>{counter}</h1>
        <button onClick={() => setcounter(() => counter - 1)}>-</button>
    </div>
  );
}

export default App;
