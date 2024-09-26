import CatFactsGrid from './components/CatFactsGrid/CatFactsGrid';
import Navbar from './components/Navbar/Navbar';
import WelcomeMessage from './components/WelcomeMessage/WelcomeMessage';
import Footer from './components/Footer/Footer';
import './App.css';

function App() {
  return (
    <div className="App">
      <Navbar />
      <div className='content-wrap'>
      <WelcomeMessage /> 
      <CatFactsGrid />
      </div>
      <Footer />
    </div>
  );
}

export default App;
