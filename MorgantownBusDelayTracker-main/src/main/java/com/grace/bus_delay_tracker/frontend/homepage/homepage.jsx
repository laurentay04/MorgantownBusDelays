import { useEffect, useState } from 'react';
import './homepage.css'


function Home() {
    //need to do:
    /* check if user is already logged in or not
    *  if not, remove some buttons and show login button in top right corner
    *  if logged in, show logout button and user info
    **/

    /* Home page layout ideas
    * green wv state logo in middle
    *  below OR above it, show title "Morgantown Bus Delays", with a small description under it
    *
    * Maybe a map of motown as the main image, or the mountainline bus, remember to make it dark
    * Green and white theme idea
    * Maybe a small gif animation of a bus moving across the screen? Will have to find video or gif tho :(
    *  */

    //states to manage visibility of elements and user login status
        const [showElement, setShowElement] = useState(true);
        const [userLoggedIn, setUserLoggedIn] = useState(false);

        //useEffect to check local storage for authentication status, might need to change?
        const checkLocalStorage = () => {
        const myVar = localStorage.getItem('authenticated');
        setShowElement(myVar !== 'true');
    };

      useEffect(() => {
        checkLocalStorage();
    }, []);

     // return ()

}