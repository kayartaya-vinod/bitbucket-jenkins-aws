import React from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          This is the homepage of something new...
        </p>
        <a
          className="App-link"
          href="https://vinod.co/"
          target="_blank"
          rel="noopener noreferrer"
        >
          Vinod Kumar Kayartaya
        </a>
      </header>
    </div>
  );
}

export default App;
