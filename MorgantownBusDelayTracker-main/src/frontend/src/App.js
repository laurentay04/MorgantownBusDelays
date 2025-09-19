
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/homepage/homepage';
import Submit from './delays/submit';
import View from './delays/view';
import About from './pages/homepage/about';


function App() {

    //initialize authenticated state from local storage
     const [authenticated, setAuthenticated] = useState(
      //9-10-2025 might not need this due to no login system anymore. will see -grace
    () => JSON.parse(localStorage.getItem('authenticated')) || false
  );

  //update loca storage whenever 'authenticated' state changes
    useEffect(() => {
        localStorage.setItem('authenticated', JSON.stringify(authenticated));
    }, [authenticated]);

    return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="*" element={<Navigate to="/" replace />} />
        <Route path="/submit" element={<Submit />} />
        <Route path="/view" element={<View />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </Router>
    )
}

export default App;

