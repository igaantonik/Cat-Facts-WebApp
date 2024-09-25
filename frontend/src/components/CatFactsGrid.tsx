import React from 'react';
import CatFactCard from './CatFactCard';
import './CatFactsGrid.css';

const CatFactsGrid: React.FC = () => {
  const catFacts = [
    { author: 'John Doe', fact: 'Cats sleep 16-20 hours a day.' },
    { author: 'Alice Jones', fact: 'A cat can jump up to six times its length. WOW!!!' },
    { author: 'Jane Doe', fact: 'A group of cats is called a clowder.' },
    { author: 'Tom Smith', fact: 'Cats can rotate their ears 180 degrees.' },
    { author: 'David Clark', fact: 'Cats have over 20 muscles that control their ears.' },
    { author: 'Emily White', fact: 'Each cat’s nose print is unique.' },
    { author: 'Tom Smith', fact: 'Cats can rotate their ears 180 degrees.' },
    { author: 'Alice Jones', fact: 'A cat can jump up to six times its length.' },
    { author: 'Jane Doe', fact: 'A group of cats is called a clowder.' },
    { author: 'Tom Smith', fact: 'Cats can rotate their ears 180 degrees.' },
    { author: 'David Clark', fact: 'Cats have over 20 muscles that control their ears.' },
    { author: 'Emily White', fact: 'Each cat’s nose print is unique.' },
    { author: 'Tom Smith', fact: 'Cats can rotate their ears 180 degrees.' },
    { author: 'Alice Jones', fact: 'A cat can jump up to six times its length.' },


  ];

  return (
    <div className="grid-container">
      {catFacts.map((catFact, index) => (
        <CatFactCard key={index} author={catFact.author} fact={catFact.fact} />
      ))}
    </div>
  );
};

export default CatFactsGrid;
