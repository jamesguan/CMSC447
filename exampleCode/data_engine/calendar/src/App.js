import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Calendar from './Calendar';

class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Stockpilr</h2>
        </div>
        <p className="App-intro">
        Calendar Below
        </p>
        <Calendar name="Dates">DatesThings</Calendar>
      </div>
    );
  }
}

export default App;
