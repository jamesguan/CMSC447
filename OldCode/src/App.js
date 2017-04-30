import React, { Component } from 'react';
//import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';
import Login from './Input.js';
import RandImage from '../public/icon.jpg'
//import { Router, Route, IndexRoute, hashHistory} from 'react-router';
import PropTypes from 'prop-types';
import NavBar from "./components/NavBar";
import SearchBar from "./components/SearchBar";
import Home from "./pages/home/Home";
import Table from "./components/Tables";

class App extends Component {
  //<img src={logo} className="App-logo" alt="logo" />

  render() {
    const styles = {
      App: {
        textAlign: "center",
        backgroundColor: "#999",
        height: "100vh",
        margin: "0"
      },
      AppLogo: {
        animation: "App-logo-spin infinite 1000s linear"
      },
      AppHeader: {
        backgroundColor: "#222",
        height: "150px",
        padding: "20px",
        margin: "0",
        color: "white"
      },
      AppBottom: {
        padding: "0",
        margin: "0"
      }
    }
    return (
      <div style={styles.App} className="App">
        <NavBar>
        </NavBar>
        <SearchBar>
        </SearchBar>
        <h2>Malware Repository</h2>
        <Table>
        </Table>
        {this.props.children}
      </div>

    );
  }
}


/*
<div style={styles.App} className="App">
  <NavBar>
  </NavBar>
  <div style={styles.AppHeader} className="App-header">
    <img style={styles.AppLogo} src={RandImage} className="App-logo" alt="logo" width="100" height="100"></img>
    <h2>Malware Repository</h2>
  </div>
  <div style={styles.AppBottom}>
    <Login></Login>
    <p className="App-intro">
      This is where you will deposit your malware
    </p>
  </div>
</div>*/
export default App;
