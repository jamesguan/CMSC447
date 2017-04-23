import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter as Router, Route, hashHistory} from 'react-router-dom';
import App from './App';
import Home from './pages/home/Home';
import Login from './pages/login/Login';
import './index.css';

ReactDOM.render(
  <Router history={hashHistory}>
    <div>
      <Route path="/" component={App}></Route>
      <Route path="/home" component={Home}></Route>
      <Route path="/login" component={Login}></Route>
    </div>
  </Router>,
  document.getElementById('root')
);

/*
<ReactRouter.Router history={ReactRouter.hashHistory}>
  <ReactRouter.Route path="/" component={App}>
  </ReactRouter.Route>
</ReactRouter.Router>,
document.getElementById('root')
 */
