import { Redirect } from 'react-router-dom';
import { useOktaAuth } from '@okta/okta-react';
import { SpinnerLoading } from '../layouts/Utils/SpinnerLoading';
import OktaSignInWidget from './OktaSignInWidget';
// TODO  This code defines a React component called LoginWidget. The component uses the useOktaAuth hook to manage authentication state and returns either a loading spinner or an OktaSignInWidget component based on the value of authState.isAuthenticated. If authState is falsy, the component returns a SpinnerLoading component. If authState.isAuthenticated is truthy, the component returns a Redirect component that redirects the user to the path /. The OktaSignInWidget component is provided with the config prop, as well as onSuccess and onError callbacks that are triggered based on the outcome of the authentication process. onSuccess calls the handleLoginRedirect method with the tokens argument, and onError logs an error message to the console.
    const LoginWidget = ({ config }) => {
    const { oktaAuth, authState } = useOktaAuth();
    const onSuccess = (tokens) => {
        oktaAuth.handleLoginRedirect(tokens);
    };

    const onError = (err) => {
        console.log('Sign in error: ', err);
    }

    if (!authState) {
        return (
            <SpinnerLoading/>
        );
    }

    return authState.isAuthenticated ?
    <Redirect to={{ pathname: '/' }}/>
    :
    <OktaSignInWidget config={config} onSuccess={onSuccess} onError={onError}/>;
};

export default LoginWidget;