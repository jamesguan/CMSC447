import React, { Component } from 'react';
//import { Link } from "react-router";
import PropTypes from 'prop-types';
import NavBar from "../../components/NavBar";

class Login extends React.Component {
  navigate(){
    console.log(this.props);
  }
  render(){
    return (
      <div>
        <h1>Home</h1>
      </div>
    );
  }
}

export default Login;
