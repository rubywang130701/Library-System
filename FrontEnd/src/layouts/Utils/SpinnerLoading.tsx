// TODO This is a React functional component that returns a loading spinner. It has a className 'spinner-border text-primary' which applies Bootstrap styling to the spinner to make it blue. The component has a parent div with className 'container m-5 d-flex justify-content-center' which sets its height to 550 pixels and centers it both horizontally and vertically within its parent container. The text "Loading..." is visually hidden but accessible to screen readers.
export const SpinnerLoading = () => {
    return (
        <div className='container m-5 d-flex justify-content-center' 
            style={{ height: 550 }}>
                <div className='spinner-border text-primary' role='status'>
                    <span className='visually-hidden'>
                        Loading...
                    </span>
                </div>
        </div>
    );
}