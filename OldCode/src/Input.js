import React, {Component} from 'react';
import PropTypes from 'prop-types';
class Login extends React.Component{
  constructor(props){
    super (props);
  }

  render(){
    const styles = {
      outside: {
        margin: "0"
      },
      section: {
        backgroundColor: "white"
      },
      labels: {
        color: "#FFFFFF"
      },
      ul: {
        listStyle: "none"
      }
    }
    return (
      <div style={styles.outside}>
      <section style={styles.section} class="loginform cf">
        <form name="login" action="index_submit" method="get" accept-charset="utf-8">
          <ul style={styles.ul}>
            <li>
              <label style={styles.labels} for="username">Username</label>
              <input type="username" name="Username" placeholder="Enter username here"></input>
            </li>
            <li>
              <label style={styles.labels} for="password">Password</label>
              <input type="password" name="Password" placeholder="Enter password here"></input>
            </li>
            <li>
              <input type="submit" value="Login"></input>
            </li>
          </ul>
        </form>
        </section>
      </div>
    );
  }
}

export default Login;