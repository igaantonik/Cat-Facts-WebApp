import React from 'react';
import './Welcome.css';

const Welcome: React.FC = () => {
  return (
    <div className="welcome-message">
      <h1>Welcome to the Cat Facts Page!</h1>
      <p>
        Here you'll find interesting information about cats, their habits, and fun facts. 
        Join us in exploring the fascinating world of cats!
      </p>
    </div>
  );
};

export default Welcome;
