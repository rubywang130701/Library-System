import { useOktaAuth } from "@okta/okta-react";
import { Link } from "react-router-dom";
// TODO This is a functional component in React named "LibraryServices". It uses the "useOktaAuth" hook from the Okta library to access the "authState" object. The component renders a message asking the user to find what they are looking for in the library, with a button that either takes them to the "messages" page if they are authenticated, or to the "login" page if they are not authenticated. The component is made up of two columns, with the first column containing text and the second column containing an image with the class name "lost-image".
export const LibraryServices = () => {

    const { authState } = useOktaAuth();

    return(
        <div className='container my-5'>
            <div className='row p-4 align-items-center border shadow-lg'>
                <div className='col-lg-7 p-3'>
                    <h1 className='display-4 fw-bold'>
                        Can't find what you are looking for?
                    </h1>
                    <p className='lead'>
                        If you cannot find what you are looking for, 
                        send our library admin's a personal message!
                    </p>
                    <div className='d-grid gap-2 justify-content-md-start mb-4 mb-lg-3'>
                        {authState?.isAuthenticated ? 
                        <Link to='/messages' type='button' className='btn main-color btn-lg px-4 me-md-2 fw-bold text-white'>
                            Library Services
                        </Link>   
                        :
                        <Link className='btn main-color btn-lg text-white' to='/login'>
                            Sign up
                        </Link> 
                    }

                    </div>
                </div>
                <div className='col-lg-4 offset-lg-1 shadow-lg lost-image'></div>
            </div>
        </div>
    );
}