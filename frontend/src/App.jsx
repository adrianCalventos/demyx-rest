import React, {Component} from 'react';
import Users from './components/Users';
import Navbar from './components/Navbar';

export class App extends Component {



  render () {
    return (
      <>
      <Navbar/>
      <Users/>
      </>
    );    
  }
}

export default App;
