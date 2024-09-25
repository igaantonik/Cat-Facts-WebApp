import React from 'react';
import './CatFactCard.css';

interface CatFactCardProps {
  author: string;
  fact: string;
}

const CatFactCard: React.FC<CatFactCardProps> = ({ author, fact }) => {
  return (
    <div className="card">
      <h4>by {author}</h4>
      <p>{fact}</p>
    </div>
  );
};

export default CatFactCard;
