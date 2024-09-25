import React from 'react';
import './Navbar.css';
import logo from '../assets/cat.png';

const Navbar: React.FC = () => {
  return (
    <nav className="navbar">

      <div className="logo">
       <img src={logo} alt="Cat Facts Logo" />
     
      <h1 className="navbar-title">Cat Facts</h1>
      </div>
      <ul className="navbar-links">
        <li><a href="#home">Home</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>

    </nav>
  );
};

export default Navbar;
