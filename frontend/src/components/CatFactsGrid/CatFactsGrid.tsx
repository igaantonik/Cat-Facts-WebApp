import React, { useEffect, useState } from 'react';
import { fromEventPattern } from 'rxjs';
import { map } from 'rxjs/operators';
import CatFactCard from '../CatFactCard/CatFactCard';
import './CatFactsGrid.css';

interface FactWithUser {
  user: string;
  fact: string;
}

const CatFactsGrid: React.FC = () => {
  const [catFacts, setCatFacts] = useState<FactWithUser[]>([]);

  useEffect(() => {
    const eventSource = new EventSource('http://localhost:8080/cat-facts');
    const sseObservable = fromEventPattern<MessageEvent>(
      (handler) => {
        eventSource.onmessage = handler;
        return () => eventSource.close();
      },
      (handler) => eventSource.removeEventListener('message', handler)
    ).pipe(
      map((event: MessageEvent) => {
        try {
          const newFact: FactWithUser = JSON.parse(event.data); 
          return newFact; 
        } catch (error) {
          console.error('Error parsing data:', error);
          return null;
        }
      })
    );

    const subscription = sseObservable.subscribe((newFact) => {
      if (newFact) { 
        setCatFacts((prevFacts) => [...prevFacts, newFact]); 
      }
    });

    return () => subscription.unsubscribe();
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