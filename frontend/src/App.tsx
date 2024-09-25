import CatFactsGrid from './components/CatFactsGrid';
import Navbar from './components/Navbar';
import Welcome from './components/Welcome';
import Footer from './components/Footer';
import './App.css';

function App() {
  return (
    <div className="App">
      <Navbar />
      <div className='content-wrap'>
      <Welcome /> 
      <CatFactsGrid />
      </div>
      <Footer />
    </div>
  );
}

export default App;
