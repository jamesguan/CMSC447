import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import PropTypes from 'prop-types';
import SearchBar from './SearchBar';

class NavBar extends React.Component{
  render(){
    const styles = {
      bar: {
        display: "inline-block",
        backgroundColor: "black",
        width: "100vw",
        height: "calc(4vw)",
        minHeight: "22px",
        //padding: "0 0 2vw 0",
        margin: "0",
        align: "center"
      },
      button: {
        position: "relative",
        minHeight: "14px",
        minWidth: "30px",
        width: "15vw",
        margin: "1vw 2vw .5vw 2vw",
        padding: "0 calc(2vw - 10px) 0 calc(2vw - 10px)",
        backgroundColor: "white",
        border: "none",
        fontSize: "calc(2vw - 2px)"
      }
    }
    return (
      <div style={styles.bar}>
        <Link to="/home">
          <button style={styles.button}>Home</button>
        </Link>
        <Link to="/upload">
          <button style={styles.button}>Upload</button>
        </Link>
        <Link to="/myupload">
          <button style={styles.button}>My Uploads</button>
        </Link>
        <Link to="/favorites">
          <button style={styles.button}>Favorites</button>
        </Link>
        <Link to="/logout">
          <button style={styles.button}>Logout</button>
        </Link>
      </div>
    );
  }
}
export default NavBar;