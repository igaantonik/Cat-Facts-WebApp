import React, { useEffect, useState } from 'react';
import CatFactCard from '../CatFactCard/CatFactCard';
import './CatFactsGrid.css';

interface FactWithUser {
  user: string;
  fact: string;
}

const CatFactsGrid: React.FC = () => {
  const [catFacts, setCatFacts] = useState<FactWithUser[]>([]);

  useEffect(() => {
    const eventSource = new EventSource("http://localhost:8080/cat-facts");

    eventSource.onmessage = (event) => {
      console.log('Received event:', event); 
      const data = JSON.parse(event.data); 
      console.log('Parsed data:', data); 
      setCatFacts(data);
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div className="grid-container">
      {catFacts.map((catFact, index) => (
        <CatFactCard key={index} author={catFact.user} fact={catFact.fact} />
      ))}
    </div>
  );
};

export default CatFactsGrid;
