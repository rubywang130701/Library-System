import React from 'react';
import { Redirect, Route, Switch, useHistory } from 'react-router-dom';
import './App.css';
import { BookCheckoutPage } from './layouts/BookCheckoutPage/BookCheckoutPage';
import { HomePage } from './layouts/HomePage/HomePage';
import { Footer } from './layouts/NavbarAndFooter/Footer';
import { Navbar } from './layouts/NavbarAndFooter/Navbar';
import { SearchBooksPage } from './layouts/SearchBooksPage/SearchBooksPage';
import { oktaConfig } from './lib/oktaConfig';
import { OktaAuth, toRelativeUrl } from '@okta/okta-auth-js';
import { Security, LoginCallback, SecureRoute } from '@okta/okta-react';
import LoginWidget from './Auth/LoginWidget';
import { ReviewListPage } from './layouts/BookCheckoutPage/ReviewListPage/ReviewListPage';
import { ShelfPage } from './layouts/ShelfPage/ShelfPage';
import { MessagesPage } from './layouts/MessagesPage/MessagesPage';
import { ManageLibraryPage } from './layouts/ManageLibraryPage/ManageLibraryPage';

const oktaAuth = new OktaAuth(oktaConfig);
// TODO This code defines a React component called App which implements the routing for a web application.The component uses the useHistory hook from react-router-dom to get access to the history object, which is used to programmatically navigate the user to different routes.The component wraps the Navbar, Footer and multiple routes in a Security component from the @okta/okta-react library, which provides an Okta authentication flow for the application. The onAuthRequired property is set to a custom function customAuthHandler which navigates the user to the /login route when authentication is required.The restoreOriginalUri function is used to redirect the user back to the original URL before authentication.The routes include:/ which redirects to /home/home which renders the HomePage component/search which renders the SearchBooksPage component/reviewlist/:bookId which renders the ReviewListPage component/checkout/:bookId which renders the BookCheckoutPage component/login which renders the LoginWidget component with the Okta configuration oktaConfig/login/callback which renders the LoginCallback componen/shelf which is a secure route that renders the ShelfPage component when the user is authenticated/messages which is a secure route that renders the MessagesPage component when the user is authenticated/admin which is a secure route that renders the ManageLibraryPage component when the user is authenticatedThe secure routes (/shelf, /messages, /admin) are wrapped in the SecureRoute component from the @okta/okta-react library, which only allows access to the route if the user is authenticated.
export const App = () => {

  const customAuthHandler = () => {
    history.push('/login');
  }

  const history = useHistory();

  const restoreOriginalUri = async (_oktaAuth: any, originalUri: any) => {
    history.replace(toRelativeUrl(originalUri || '/', window.location.origin));
  };


  return (
    <div className='d-flex flex-column min-vh-100'>
      <Security oktaAuth={oktaAuth} restoreOriginalUri={restoreOriginalUri} onAuthRequired={customAuthHandler}>
      <Navbar />
      <div className='flex-grow-1'>
        <Switch>
          <Route path='/' exact>
            <Redirect to='/home' />
          </Route>
          <Route path='/home'>
            <HomePage />
          </Route>
          <Route path='/search'>
            <SearchBooksPage />
          </Route>
          <Route path='/reviewlist/:bookId'>
            <ReviewListPage/>
          </Route>
          <Route path='/checkout/:bookId'>
            <BookCheckoutPage/>
          </Route>
          <Route path='/login' render={
            () => <LoginWidget config={oktaConfig} /> 
            } 
          />
          <Route path='/login/callback' component={LoginCallback} />
          <SecureRoute path='/shelf'> <ShelfPage/> </SecureRoute>
          <SecureRoute path='/messages'> <MessagesPage/> </SecureRoute>
          <SecureRoute path='/admin'> <ManageLibraryPage/> </SecureRoute>
        </Switch>
      </div>
      <Footer />
      </Security>
    </div>
  );
}
