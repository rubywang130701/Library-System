import { useEffect, useRef } from 'react';
import OktaSignIn from '@okta/okta-signin-widget';
import '@okta/okta-signin-widget/dist/css/okta-sign-in.min.css';
import { oktaConfig } from '../lib/oktaConfig';
// TODO This code defines a React component called OktaSignInWidget which displays an Okta sign-in widget to the user. The component takes in two callbacks, onSuccess and onError, as props. The component uses the useRef hook to create a reference to a DOM element that will be used to render the sign-in widget. The useEffect hook is used to initialize and remove the sign-in widget.In the useEffect function, it checks if the widgetRef is falsy, and if it is, the function returns false. If widgetRef is truthy, the code creates an instance of OktaSignIn using the oktaConfig object and then calls showSignInToGetTokens on the instance of OktaSignIn. showSignInToGetTokens takes an object with a single property el which is set to widgetRef.current and is used to specify the DOM element where the sign-in widget will be rendered. The showSignInToGetTokens method returns a promise that is either resolved with the onSuccess callback or rejected with the onError callback. The useEffect cleanup function calls the remove method on the OktaSignIn instance, which is used to remove the widget from the DOM.The component returns a div element with a ref prop set to widgetRef and a class of container mt-5 mb-5. This element will be used as the container for the sign-in widget.
const OktaSignInWidget = ({ onSuccess, onError }) => {
    const widgetRef = useRef();

    useEffect(() => {

        if (!widgetRef.current) {
            return false;
        }

        const widget = new OktaSignIn(oktaConfig);

        widget.showSignInToGetTokens({
            el: widgetRef.current,
        }).then(onSuccess).catch(onError);

        return () => widget.remove();
    }, [onSuccess, onError]);

    return (
        <div className='container mt-5 mb-5'>
            <div ref={widgetRef}></div>
        </div>
    );
};

export default OktaSignInWidget