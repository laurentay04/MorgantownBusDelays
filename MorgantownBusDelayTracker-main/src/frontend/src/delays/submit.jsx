import React from 'react';
import {useEffect, useState} from 'react';
import './submit.css';


/**
 * to do: 
 * submit button on click will add the delay of the line and the time it was submitted
 * to the database
 * an alert will pop up that a delay has been submitted
 */

function Submit() {

    const [currentTime, setCurrentTime] = useState(new Date());
    const [selectedRoute, setSelectedRoute] = useState('');
    const [delayDescription, setDelayDescription] = useState('');
    const [submitStatus, setSubmitStatus] = useState(null); // 'success', 'error', or null
    const [isSubmitting, setIsSubmitting] = useState(false);

    // All MLTA bus routes from backend data
    const routes = [
        { routeId: "46387", routeShortName: "RT 1", routeLongName: "Campus PM" },
        { routeId: "46388", routeShortName: "RT 2", routeLongName: "Downtown Mall PM" },
        { routeId: "45954", routeShortName: "RT 3", routeLongName: "Green Line" },
        { routeId: "46421", routeShortName: "RT 6", routeLongName: "Gold Line" },
        { routeId: "46422", routeShortName: "RT 7", routeLongName: "Red Line" },
        { routeId: "46423", routeShortName: "RT 8", routeLongName: "Brookhaven" },
        { routeId: "46424", routeShortName: "RT 9", routeLongName: "Purple Line" },
        { routeId: "46425", routeShortName: "RT 11", routeLongName: "Cassville" },
        { routeId: "46426", routeShortName: "RT 12", routeLongName: "Blue Line" },
        { routeId: "46427", routeShortName: "RT 13", routeLongName: "Crown" },
        { routeId: "46428", routeShortName: "RT 14", routeLongName: "Mt. Heights" },
        { routeId: "46429", routeShortName: "RT 15", routeLongName: "Grafton Rd" },
        { routeId: "46430", routeShortName: "RT 16", routeLongName: "Pink Line" },
        { routeId: "46420", routeShortName: "RT 4", routeLongName: "Orange Line" },
        { routeId: "46432", routeShortName: "RT 30", routeLongName: "West Run" },
        { routeId: "46433", routeShortName: "RT 38", routeLongName: "Blue & Gold Express" },
        { routeId: "46434", routeShortName: "RT 39", routeLongName: "Beechurst Express" },
        { routeId: "46435", routeShortName: "RT 44", routeLongName: "Valley View" },
        { routeId: "46436", routeShortName: "RT 46", routeLongName: "Eastern Circulator" },
        { routeId: "46437", routeShortName: "RT 47", routeLongName: "Northern Circulator" },
        { routeId: "46438", routeShortName: "RT 50", routeLongName: "Don Knotts" },
        { routeId: "46439", routeShortName: "RT 51", routeLongName: "Westridge Mylan" },
        { routeId: "46440", routeShortName: "RT 52", routeLongName: "Wadestown" },
        { routeId: "46431", routeShortName: "RT 29", routeLongName: "Grey Line" }
    ];

    //clock
    useEffect(() => {
        const timer = setInterval(() => setCurrentTime(new Date()), 1000);

        return () => {
            clearInterval(timer);
        };
    }, []);

    const handleRouteChange = (event) => {
        setSelectedRoute(event.target.value);
    }

    const handleDescriptionChange = (event) => {
        const text = event.target.value;
        //limit to 50 words
        const words = text.trim().split(/\s+/).filter(word => word.length > 0);
        if (words.length <= 50) {
            setDelayDescription(text);
        }
    }

    const getWordCount = () => {
        const words = delayDescription.trim().split(/\s+/).filter(word => word.length > 0);
        return words.length;
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        
        if (!selectedRoute) {
            setSubmitStatus('error');
            return;
        }

        setIsSubmitting(true);
        setSubmitStatus(null);

        try {
            //find the route name for the selected route ID
            const selectedRouteData = routes.find(route => route.routeId === selectedRoute);
            const routeName = selectedRouteData ? 
                `${selectedRouteData.routeShortName} - ${selectedRouteData.routeLongName}` : 
                'Other';

            const delayData = {
                routeId: selectedRoute,
                routeName: routeName,
                description: delayDescription || null
            };

            console.log('Submitting delay data:', delayData);
            
            const response = await fetch('http://localhost:8080/api/delays', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(delayData)
            });

            console.log('Submit response status:', response.status);
            console.log('Submit response headers:', response.headers);

            if (response.ok) {
                const result = await response.json();
                console.log('Delay submitted successfully:', result);
                setSubmitStatus('success');
            
                setSelectedRoute('');
                setDelayDescription('');
                //clear  message after 3 seconds
                setTimeout(() => setSubmitStatus(null), 3000);
            } else {
                const errorText = await response.text();
                console.error('Submit failed. Status:', response.status, 'Error text:', errorText);
                throw new Error(`HTTP ${response.status}: ${errorText || 'Failed to submit delay'}`);
            }
        } catch (error) {
            console.error('Detailed error submitting delay:', {
                message: error.message,
                stack: error.stack,
                name: error.name
            });
            setSubmitStatus('error');
            //clear  message after 5 seconds
            //to add: better error handling-  grace
            setTimeout(() => setSubmitStatus(null), 5000);
        } finally {
            setIsSubmitting(false);
        }
    }

    return (
        <div className="submit">

            <nav className="navbar">
                <ul>
                    <li><a href="/">Homepage</a></li>
                    <li><a href="/about">About</a></li>
                    <li><a href="/view">View Delays</a></li>
                </ul>
            </nav>

            <div className="submitDelay">
                <h1>Submit a Delay</h1>
            </div>
            
            {submitStatus === 'success' && (
                <div className="status-message success">
                    Successfully Submitted
                </div>
            )}
            
            {submitStatus === 'error' && (
                <div className="status-message error">
                    Error. Please Try Again
                </div>
            )}
        
            <div className="form-container">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="route">Select Bus Route:</label>
                        <select
                            id="route"
                            value={selectedRoute}
                            onChange={handleRouteChange}
                            required
                        >
                            <option value="">-- Select a route --</option>
                            {routes.map((route) => (
                                <option key={route.routeId} value={route.routeId}>
                                    {route.routeShortName} - {route.routeLongName}
                                </option>
                            ))}
                            <option value="other">Other</option>
                        </select>
                    </div>
                    
                    <div className="form-group">
                        <label htmlFor="description">Describe Delay:</label>
                        <textarea
                            id="description"
                            value={delayDescription}
                            onChange={handleDescriptionChange}
                            placeholder="Describe the delay (up to 50 words)..."
                            rows="3"
                            maxLength="500"
                        />
                        <div className="word-count">
                            {getWordCount()}/50 words
                        </div>
                    </div>
                    
                    <div className='button'>
                        <button type="submit" disabled={isSubmitting}>
                            {isSubmitting ? 'Submitting...' : `Submit Delay at ${currentTime.toLocaleTimeString()}`}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Submit;