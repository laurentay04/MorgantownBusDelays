import React from 'react';
import {useEffect, useState} from 'react';
import './view.css';

function View() {

    const [currentTime, setCurrentTime] = useState(new Date());
    const [delays, setDelays] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch delays from backend
    const fetchDelays = async () => {
        try {
            setLoading(true);
            console.log('Attempting to fetch delays from: http://localhost:8080/api/delays');
            
            const response = await fetch('http://localhost:8080/api/delays');
            console.log('Response status:', response.status);
            console.log('Response headers:', response.headers);
            
            if (!response.ok) {
                const errorText = await response.text();
                console.error('Response not OK. Status:', response.status, 'Error text:', errorText);
                throw new Error(`HTTP ${response.status}: ${errorText || 'Failed to fetch delays'}`);
            }
            
            const delaysData = await response.json();
            console.log('Successfully fetched delays:', delaysData);
            setDelays(delaysData);
            setError(null);
        } catch (err) {
            console.error('Detailed error fetching delays:', {
                message: err.message,
                stack: err.stack,
                name: err.name
            });
            setError(`Failed to load delays: ${err.message}`);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchDelays();
    }, []);

    //clock
    useEffect(() => {
        const timer = setInterval(() => setCurrentTime(new Date()), 1000);

        return () => {
            clearInterval(timer);
        };
    }, []);

    const formatDateTime = (dateTimeString) => {
        const date = new Date(dateTimeString);
        return date.toLocaleString();
    };
        
    return (
        <div className="view">

            <nav className="navbar">
                <ul>
                    <li><a href="/">Homepage</a></li>
                    <li><a href="/about">About</a></li>
                    <li><a href="/submit">Submit a Delay</a></li>
                </ul>
            </nav>

            <div className="viewDelay">
                <h1>View Active Delays</h1>
            </div>

            <div className="clock">
                <p>{currentTime.toLocaleTimeString()}</p>
            </div>

            <div className="delays-container">
                {loading ? (
                    <div className="loading">Loading delays...</div>
                ) : error ? (
                    <div className="error">{error}</div>
                ) : delays.length === 0 ? (
                    <div className="no-delays">No delays reported yet.</div>
                ) : (
                    <div className="delays-list">
                        {delays.slice(0, 5).map((delay) => (
                            <div key={delay.id} className="delay-card">
                                <div className="delay-header">
                                    <h3>{delay.routeName}</h3>
                                    <span className="delay-time">{formatDateTime(delay.reportTime)}</span>
                                </div>
                                {delay.description && (
                                    <div className="delay-description">
                                        <p>{delay.description}</p>
                                    </div>
                                )}
                            </div>
                        ))}
                        {delays.length > 5 && (
                            <div className="more-delays-info">
                                Showing 5 most recent delays. {delays.length - 5} older delays not displayed.
                            </div>
                        )}
                    </div>
                )}
            </div>

        </div>
    );
}

export default View;