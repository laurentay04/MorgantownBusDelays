
  //need to do:
    /* check if user is already logged in or not
    *  if not, remove some buttons and show login button in top right corner
    *  if logged in, show logout button and user info
    **/

    /* Home page layout ideas
    * green wv state logo in middle
    *  below OR above it, show title "Morgantown Bus Delays", with a small description under it
    *
    * https://www.google.com/url?sa=i&url=https%3A%2F%2Fwvtourism.com%2Fcompany%2Fmountain-line-bus-service%2F&psig=AOvVaw0vXvvPmoFedU3pNGeXyYdu&ust=1756001587926000&source=images&cd=vfe&opi=89978449&ved=0CBYQjRxqFwoTCJj7peztn48DFQAAAAAdAAAAABAE
    * 
    * Maybe a map of motown as the main image, or the mountainline bus, remember to make it dark
    * Green and white theme idea
    * Maybe a small gif animation of a bus moving across the screen? Will have to find video or gif tho :()
    *  */

import React from "react";
import "./homepage.css";

function Home() {
  return (
    <div className="home">
   
      <nav className="navbar">
        <ul>
          <li><a href="/about">About</a></li>
          <li><a href="/submit">Submit a Delay</a></li>
          <li><a href="/view">View Delays</a></li>
        </ul>
      </nav>

      <div className="busdelayshome">
        <h1>Morgantown Bus Delays</h1>
      </div>

      <div className="map">
        <h3>Open the sidebar on the top left corner of the map to view different MLTA routes.</h3>
        <iframe src="https://www.google.com/maps/d/embed?mid=1BwcbWin078YjBZt_oJOwIft6eFsJrwwn&ehbc=2E312F"></iframe>
      </div>
    </div>
  );
}

export default Home;
