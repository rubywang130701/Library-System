import { Link } from "react-router-dom";
// TODO This is a functional React component named ExploreTopBooks. It returns a div element with a dark background and white text. The div contains a container-fluid that has a text-white class to set the text color to white. The container-fluid has the classes d-flex, justify-content-center, and align-items-center to center the contents both horizontally and vertically.The contents of the container-fluid include an h1 header with the text "Find your next adventure" and a class fw-bold to make the text bold. There is also a paragraph element with the text "Where would you like to go next?" and a class fs-4 to set the font size.Finally, there is a Link component that links to "/search". The Link component has the text "Explore top books" and classes btn, main-color, btn-lg, and text-white to style the button.
export const ExploreTopBooks = () => {
    return (
        <div className='p-5 mb-4 bg-dark header'>
            <div className='container-fluid py-5 text-white 
                d-flex justify-content-center align-items-center'>
                <div>
                    <h1 className='display-5 fw-bold'>Find your next adventure</h1>
                    <p className='col-md-8 fs-4'>Where would you like to go next?</p>
                    <Link type='button' className='btn main-color btn-lg text-white' to='/search'>
                        Explore top books</Link>
                </div>
            </div>
        </div>
    );
}