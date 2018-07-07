import React, { Component } from 'react';
//import logo from './logo.svg';
import './App.css';
import SingUp from './components/component.signup';

class App extends Component { 
  
  render(){
    return (
      <div className="App">
        <p className="App-intro">
          <code>Welcome to my Social Network {this.record}</code>
        </p>
        <SingUp/>
      </div>
    );
  }
}

export default App;
