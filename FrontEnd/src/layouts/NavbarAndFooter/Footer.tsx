import { Link } from "react-router-dom";
// TODO This is the code for a footer component in a React-based web app. The component uses the Link component from the react-router-dom library to navigate between different pages in the application. The component includes two links: one to the "Home" page and one to the "Search Books" page. The component uses CSS classes for styling, including a main-color class for setting the background color of the footer and a text-white class for setting the text color.
export const Footer = () => {
    return (
        <div className='main-color'>
            <footer className='container d-flex flex-wrap 
                justify-content-between align-items-center py-5 main-color'>
                <p className='col-md-4 mb-0 text-white'>© Ruoqi Library App, Inc</p>
                <ul className='nav navbar-dark col-md-4 justify-content-end'>
                    <li className='nav-item'>
                        <Link to='/home' className='nav-link px-2 text-white'>
                            Home
                        </Link>
                    </li>
                    <li className='nav-item'>
                        <Link to='/search' className='nav-link px-2 text-white'>
                            Search Books
                        </Link>
                    </li>
                </ul>
            </footer>
        </div>
    );
}