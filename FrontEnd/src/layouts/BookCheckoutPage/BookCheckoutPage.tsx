import { useEffect, useState } from "react";
import BookModel from "../../models/BookModel";
import ReviewModel from "../../models/ReviewModel";
import { SpinnerLoading } from "../Utils/SpinnerLoading";
import { StarsReview } from "../Utils/StarsReview";
import { CheckoutAndReviewBox } from "./CheckoutAndReviewBox";
import { LatestReviews } from "./LatestReviews";
import { useOktaAuth } from "@okta/okta-react";
import ReviewRequestModel from "../../models/ReviewRequestModel";

export const BookCheckoutPage = () => {

    const { authState } = useOktaAuth();

    const [book, setBook] = useState<BookModel>();
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);

// TODO Review State
    const [reviews, setReviews] = useState<ReviewModel[]>([])
    const [totalStars, setTotalStars] = useState(0);
    const [isLoadingReview, setIsLoadingReview] = useState(true);

    const [isReviewLeft, setIsReviewLeft] = useState(false);
    const [isLoadingUserReview, setIsLoadingUserReview] = useState(true);

// TODO Loans Count State
    const [currentLoansCount, setCurrentLoansCount] = useState(0);
    const [isLoadingCurrentLoansCount, setIsLoadingCurrentLoansCount] = useState(true);

// TODO Is Book Check Out?
    const [isCheckedOut, setIsCheckedOut] = useState(false);
    const [isLoadingBookCheckedOut, setIsLoadingBookCheckedOut] = useState(true);

    const bookId = (window.location.pathname).split('/')[2];
// TODO This is a React hook using the useEffect hook to fetch book data from an API endpoint. The hook sets up a function fetchBook which sends a GET request to an API endpoint with the URL http://localhost:8088/api/books/{bookId} where bookId is the ID of the book to be fetched. If the response is not okay (status code 200), an error is thrown with the message "Something went wrong!". If the response is okay, the hook parses the JSON data into a BookModel object and sets the state of book to the loaded book and isLoading to false. If an error occurs while fetching the book, isLoading is set to false and an error message is set to httpError. The hook will re-run whenever the isCheckedOut state changes.
    useEffect(() => {
        const fetchBook = async () => {
            const baseUrl: string = `http://localhost:8088/api/books/${bookId}`;

            const response = await fetch(baseUrl);

            if (!response.ok) {
                throw new Error('Something went wrong!');
            }

            const responseJson = await response.json();

            const loadedBook: BookModel = {
                id: responseJson.id,
                title: responseJson.title,
                author: responseJson.author,
                description: responseJson.description,
                copies: responseJson.copies,
                copiesAvailable: responseJson.copiesAvailable,
                category: responseJson.category,
                img: responseJson.img,
            };

            setBook(loadedBook);
            setIsLoading(false);
        };
        fetchBook().catch((error: any) => {
            setIsLoading(false);
            setHttpError(error.message);
        })
    }, [isCheckedOut]);
// TODO This is a React hook using the useEffect hook to fetch book review data from an API endpoint. The hook sets up a function fetchBookReviews which sends a GET request to an API endpoint with the URL http://localhost:8088/api/reviews/search/findByBookId?bookId=${bookId} where bookId is the ID of the book whose reviews are to be fetched. If the response is not okay (status code 200), an error is thrown with the message "Something went wrong!". If the response is okay, the hook parses the JSON data and gets the array of reviews from the _embedded.reviews property. Then it maps the response data to ReviewModel objects and pushes them into an array loadedReviews. The hook also calculates the weighted average rating of the reviews by summing up all the ratings and dividing by the number of reviews. Finally, it sets the state of reviews to the loaded reviews, totalStars to the weighted average rating rounded to the nearest half, and isLoadingReview to false. If an error occurs while fetching the reviews, isLoadingReview is set to false and an error message is set to httpError. The hook will re-run whenever the isReviewLeft state changes.
    useEffect(() => {
        const fetchBookReviews = async () => {
            const reviewUrl: string = `http://localhost:8088/api/reviews/search/findByBookId?bookId=${bookId}`;

            const responseReviews = await fetch(reviewUrl);

            if (!responseReviews.ok) {
                throw new Error('Something went wrong!');
            }

            const responseJsonReviews = await responseReviews.json();

            const responseData = responseJsonReviews._embedded.reviews;

            const loadedReviews: ReviewModel[] = [];

            let weightedStarReviews: number = 0;

            for (const key in responseData) {
                loadedReviews.push({
                    id: responseData[key].id,
                    userEmail: responseData[key].userEmail,
                    date: responseData[key].date,
                    rating: responseData[key].rating,
                    book_id: responseData[key].bookId,
                    reviewDescription: responseData[key].reviewDescription,
                });
                weightedStarReviews = weightedStarReviews + responseData[key].rating;
            }

            if (loadedReviews) {
                const round = (Math.round((weightedStarReviews / loadedReviews.length) * 2) / 2).toFixed(1);
                setTotalStars(Number(round));
            }

            setReviews(loadedReviews);
            setIsLoadingReview(false);
        };

        fetchBookReviews().catch((error: any) => {
            setIsLoadingReview(false);
            setHttpError(error.message);
        })
    }, [isReviewLeft]);
// TODO This code defines a React Hook, useEffect, which is used to fetch a user's book review from the server. The hook makes an API call to http://localhost:8088/api/reviews/secure/user/book/ and passes the bookId as a query parameter. The API call is authorized using an access token, which is passed in the Authorization header. If the API call is successful, the response is saved in the userReviewResponseJson variable and the isReviewLeft state is updated with this value. If the API call fails, the isLoadingUserReview state is set to false and the error message is saved in the httpError state. The hook will re-run whenever the authState changes.
    useEffect(() => {
        const fetchUserReviewBook = async () => {
            if (authState && authState.isAuthenticated) {
                const url = `http://localhost:8088/api/reviews/secure/user/book/?bookId=${bookId}`;
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${authState.accessToken?.accessToken}`,
                        'Content-Type': 'application/json'
                    }
                };
                const userReview = await fetch(url, requestOptions);
                if (!userReview.ok) {
                    throw new Error('Something went wrong');
                }
                const userReviewResponseJson = await userReview.json();
                setIsReviewLeft(userReviewResponseJson);
            }
            setIsLoadingUserReview(false);
        }
        fetchUserReviewBook().catch((error: any) => {
            setIsLoadingUserReview(false);
            setHttpError(error.message);
        })
    }, [authState]);
// TODO This code defines another React Hook, useEffect, which is used to fetch the current count of books loaned by the user. Like the previous hook, it makes an API call to http://localhost:8088/api/books/secure/currentloans/count with the access token passed in the Authorization header for authentication. If the API call is successful, the response is saved in the currentLoansCountResponseJson variable, and the currentLoansCount state is updated with this value. If the API call fails, the isLoadingCurrentLoansCount state is set to false and the error message is saved in the httpError state. The hook will re-run whenever the authState or isCheckedOut changes.
    useEffect(() => {
        const fetchUserCurrentLoansCount = async () => {
            if (authState && authState.isAuthenticated) {
                const url = `http://localhost:8088/api/books/secure/currentloans/count`;
                const requestOptions = {
                    method: 'GET',
                    headers: { 
                        Authorization: `Bearer ${authState.accessToken?.accessToken}`,
                        'Content-Type': 'application/json'
                     }
                };
                const currentLoansCountResponse = await fetch(url, requestOptions);
                if (!currentLoansCountResponse.ok)  {
                    throw new Error('Something went wrong!');
                }
                const currentLoansCountResponseJson = await currentLoansCountResponse.json();
                setCurrentLoansCount(currentLoansCountResponseJson);
            }
            setIsLoadingCurrentLoansCount(false);
        }
        fetchUserCurrentLoansCount().catch((error: any) => {
            setIsLoadingCurrentLoansCount(false);
            setHttpError(error.message);
        })
    }, [authState, isCheckedOut]);
// TODO This code defines another React Hook, useEffect, which is used to fetch whether the user has checked out a specific book. If the user is authenticated, the hook makes a GET request to http://localhost:8088/api/books/secure/ischeckedout/byuser/?bookId=${bookId} with the access token passed in the Authorization header for authentication. If the API call is successful, the response is saved in the bookCheckedOutResponseJson variable, and the isCheckedOut state is updated with this value. If the API call fails, the isLoadingBookCheckedOut state is set to false and the error message is saved in the httpError state. The hook will re-run whenever the authState changes.The code then has a conditional statement that checks if any of the loading states (isLoading, isLoadingReview, isLoadingCurrentLoansCount, isLoadingBookCheckedOut, or isLoadingUserReview) are true. If any of these are true, the component will return a SpinnerLoading component. If the httpError state is set, the component will return a div with the error message.
    useEffect(() => {
        const fetchUserCheckedOutBook = async () => {
            if (authState && authState.isAuthenticated) {
                const url = `http://localhost:8088/api/books/secure/ischeckedout/byuser/?bookId=${bookId}`;
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${authState.accessToken?.accessToken}`,
                        'Content-Type': 'application/json'
                    }
                };
                const bookCheckedOut = await fetch(url, requestOptions);

                if (!bookCheckedOut.ok) {
                    throw new Error('Something went wrong!');
                }

                const bookCheckedOutResponseJson = await bookCheckedOut.json();
                setIsCheckedOut(bookCheckedOutResponseJson);
            }
            setIsLoadingBookCheckedOut(false);
        }
        fetchUserCheckedOutBook().catch((error: any) => {
            setIsLoadingBookCheckedOut(false);
            setHttpError(error.message);
        })
    }, [authState]);

    if (isLoading || isLoadingReview || isLoadingCurrentLoansCount || isLoadingBookCheckedOut || isLoadingUserReview) {
        return (
            <SpinnerLoading />
        )
    }

    if (httpError) {
        return (
            <div className='container m-5'>
                <p>{httpError}</p>
            </div>
        )
    }
// TODO The code defines an async function named checkoutBook that sends a PUT request to the server to checkout a book. The book object and the authState object hold information that is required to build the URL and headers of the request. If the response from the server is not ok, it throws an error with message "Something went wrong!". If the response is ok, it sets isCheckedOut to true.
    async function checkoutBook() {
        const url = `http://localhost:8088/api/books/secure/checkout/?bookId=${book?.id}`;
        const requestOptions = {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                'Content-Type': 'application/json'
            }
        };
        const checkoutResponse = await fetch(url, requestOptions);
        if (!checkoutResponse.ok) {
            throw new Error('Something went wrong!');
        }
        setIsCheckedOut(true);
    }
// TODO This is a React functional component that displays a book's information, along with related details such as author, description, average rating, checkout status, and reviews. The component takes in the following parameters: book, authState, currentLoansCount, reviews, isCheckedOut, setIsCheckedOut, isReviewLeft, and setIsReviewLeft.The component uses the fetch API to make API calls to the backend to perform actions such as checking out a book and submitting a review. The checkoutBook function updates the checkout status of a book by sending a PUT request to the API endpoint /api/books/secure/checkout with the bookId as a query parameter. The submitReview function sends a POST request to the API endpoint /api/reviews/secure to submit a review, passing the review details in the request body as a ReviewRequestModel object.The component returns two sections, one for desktop view and another for mobile view. The two sections display the book details, checkout and review status, and reviews using the CheckoutAndReviewBox and LatestReviews components, respectively
    async function submitReview(starInput: number, reviewDescription: string) {
        let bookId: number = 0;
        if (book?.id) {
            bookId = book.id;
        }

        const reviewRequestModel = new ReviewRequestModel(starInput, bookId, reviewDescription);
        const url = `http://localhost:8088/api/reviews/secure`;
        const requestOptions = {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reviewRequestModel)
        };
        const returnResponse = await fetch(url, requestOptions);
        if (!returnResponse.ok) {
            throw new Error('Something went wrong!');
        }
        setIsReviewLeft(true);
    }

    return (
        <div>
            <div className='container d-none d-lg-block'>
                <div className='row mt-5'>
                    <div className='col-sm-2 col-md-2'>
                        {book?.img ?
                            <img src={book?.img} width='226' height='349' alt='Book' />
                            :
                            <img src={require('./../../Images/BooksImages/book-luv2code-1000.png')} width='226'
                                height='349' alt='Book' />
                        }
                    </div>
                    <div className='col-4 col-md-4 container'>
                        <div className='ml-2'>
                            <h2>{book?.title}</h2>
                            <h5 className='text-primary'>{book?.author}</h5>
                            <p className='lead'>{book?.description}</p>
                            <StarsReview rating={totalStars} size={32} />
                        </div>
                    </div>
                    <CheckoutAndReviewBox book={book} mobile={false} currentLoansCount={currentLoansCount} 
                        isAuthenticated={authState?.isAuthenticated} isCheckedOut={isCheckedOut} 
                        checkoutBook={checkoutBook} isReviewLeft={isReviewLeft} submitReview={submitReview}/>
                </div>
                <hr />
                <LatestReviews reviews={reviews} bookId={book?.id} mobile={false} />
            </div>
            <div className='container d-lg-none mt-5'>
                <div className='d-flex justify-content-center alighn-items-center'>
                    {book?.img ?
                        <img src={book?.img} width='226' height='349' alt='Book' />
                        :
                        <img src={require('./../../Images/BooksImages/book-luv2code-1000.png')} width='226'
                            height='349' alt='Book' />
                    }
                </div>
                <div className='mt-4'>
                    <div className='ml-2'>
                        <h2>{book?.title}</h2>
                        <h5 className='text-primary'>{book?.author}</h5>
                        <p className='lead'>{book?.description}</p>
                        <StarsReview rating={totalStars} size={32} />
                    </div>
                </div>
                <CheckoutAndReviewBox book={book} mobile={true} currentLoansCount={currentLoansCount} 
                    isAuthenticated={authState?.isAuthenticated} isCheckedOut={isCheckedOut} 
                    checkoutBook={checkoutBook} isReviewLeft={isReviewLeft} submitReview={submitReview}/>
                <hr />
                <LatestReviews reviews={reviews} bookId={book?.id} mobile={true} />
            </div>
        </div>
    );
}