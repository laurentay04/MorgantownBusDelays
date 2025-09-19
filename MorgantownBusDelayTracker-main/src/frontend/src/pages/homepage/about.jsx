import React from 'react';
import './about.css';

function About() {
    return (
        <div className="aboutPage">

            <nav className="navbar">
                <ul>
                    <li><a href="/">Homepage</a></li>
                    <li><a href="/submit">Submit a Delay</a></li>
                    <li><a href="/view">View Delays</a></li>
                </ul>
            </nav>

            <div className="title">
                <h1>About</h1>
            </div>

            <div className="about-container">
                <p>The Morgantown Bus Delay Tracker was created for users of the Mountain Line Transit Authority (MLTA) 
                    to keep track of different transit line delays. Users are able to go on this website to submit and cancel
                    known delays. This way, users of the MLTA can stay up to date on the progress of all the lines so they are 
                    not out of the loop.
                </p>
<<<<<<< HEAD
                <p>Website created by Grace Hanson and Lauren Taylor</p>
=======
                <p>This website was created by Grace Hanson(backend) and Lauren Taylor (frontend).</p>
>>>>>>> c51b7e8daa283e35f97968cb70c2ff8a5c3247f6
            </div>

        </div>
    );
}

export default About;