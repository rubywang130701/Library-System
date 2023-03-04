import { useEffect, useState } from "react";
import BookModel from "../../../models/BookModel";
import { useOktaAuth } from '@okta/okta-react';

export const ChangeQuantityOfBook: React.FC<{ book: BookModel, deleteBook: any }> = (props, key) => {
    
    const { authState } = useOktaAuth();
    const [quantity, setQuantity] = useState<number>(0);
    const [remaining, setRemaining] = useState<number>(0);
// TODO This is a React Hook that is using useEffect to manage component state. The hook is fetching the copies and copiesAvailable properties from the props.book object, and setting the component's quantity and remaining state values accordingly. If the properties are undefined, the hook sets quantity and remaining to 0. The second argument [] in the useEffect function tells React to only run this effect once when the component is first mounted, and not on subsequent render updates.
    useEffect(() => {
        const fetchBookInState = () => {
            props.book.copies ? setQuantity(props.book.copies) : setQuantity(0);
            props.book.copiesAvailable ? setRemaining(props.book.copiesAvailable) : setRemaining(0);
        };
        fetchBookInState();
    }, []);
// TODO This is a JavaScript function that performs an API call to increase the quantity of a book. The function first defines a URL http://localhost:8088/api/admin/secure/increase/book/quantity/?bookId=${props.book?.id} using the bookId from the props.book object. It also defines a requestOptions object which includes the method type PUT, headers such as Authorization and Content-Type.The function then uses fetch to send a request to the API with the URL and request options. If the API returns a non-ok response, the function throws an error. If the API call is successful, the function updates the component's quantity and remaining state using setQuantity and setRemaining. The function is async meaning it returns a promise and can be await-ed.
    async function increaseQuantity() {
        const url = `http://localhost:8088/api/admin/secure/increase/book/quantity/?bookId=${props.book?.id}`;
        const requestOptions = {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                'Content-Type': 'application/json'
            }
        };

        const quantityUpdateResponse = await fetch(url, requestOptions);
        if (!quantityUpdateResponse.ok) {
            throw new Error('Something went wrong!');
        }
        setQuantity(quantity + 1);
        setRemaining(remaining + 1);
    }
// TODO This is a JavaScript function that performs an API call to decrease the quantity of a book. The function works similarly to the increaseQuantity function described previously, with the main difference being that it sends a PUT request to http://localhost:8088/api/admin/secure/decrease/book/quantity/?bookId=${props.book?.id} to decrease the quantity of a book. If the API call is successful, the function updates the component's quantity and remaining state using setQuantity and setRemaining, with the values decremented by 1. If the API returns a non-ok response, the function throws an error.
    async function decreaseQuantity() {
        const url = `http://localhost:8088/api/admin/secure/decrease/book/quantity/?bookId=${props.book?.id}`;
        const requestOptions = {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                'Content-Type': 'application/json'
            }
        };

        const quantityUpdateResponse = await fetch(url, requestOptions);
        if (!quantityUpdateResponse.ok) {
            throw new Error('Something went wrong!');
        }
        setQuantity(quantity - 1);
        setRemaining(remaining - 1);
    }
// TODO This is a functional component that displays details of a book and provides options to delete, increase or decrease the quantity of the book. The component displays information such as the author, title, description, total quantity and remaining books of the selected book. The component uses the Fetch API to make PUT and DELETE requests to the server to update and delete the book respectively. The component is used in the React library and uses a CSS framework to style the component.
    async function deleteBook() {
        const url = `http://localhost:8088/api/admin/secure/delete/book/?bookId=${props.book?.id}`;
        const requestOptions = {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${authState?.accessToken?.accessToken}`,
                'Content-Type': 'application/json'
            }
        };

        const updateResponse = await fetch(url, requestOptions);
        if (!updateResponse.ok) {
            throw new Error('Something went wrong!');
        }
        props.deleteBook();
    }
    
    return (
        <div className='card mt-3 shadow p-3 mb-3 bg-body rounded'>
            <div className='row g-0'>
                <div className='col-md-2'>
                    <div className='d-none d-lg-block'>
                        {props.book.img ?
                            <img src={props.book.img} width='123' height='196' alt='Book' />
                            :
                            <img src={require('./../../../Images/BooksImages/book-luv2code-1000.png')} 
                                width='123' height='196' alt='Book' />
                        }
                    </div>
                    <div className='d-lg-none d-flex justify-content-center align-items-center'>
                        {props.book.img ?
                            <img src={props.book.img} width='123' height='196' alt='Book' />
                            :
                            <img src={require('./../../../Images/BooksImages/book-luv2code-1000.png')} 
                                width='123' height='196' alt='Book' />
                        }
                    </div>
                </div>
                <div className='col-md-6'>
                    <div className='card-body'>
                        <h5 className='card-title'>{props.book.author}</h5>
                        <h4>{props.book.title}</h4>
                        <p className='card-text'> {props.book.description} </p>
                    </div>
                </div>
                <div className='mt-3 col-md-4'>
                    <div className='d-flex justify-content-center algin-items-center'>
                        <p>Total Quantity: <b>{quantity}</b></p>
                    </div>
                    <div className='d-flex justify-content-center align-items-center'>
                        <p>Books Remaining: <b>{remaining}</b></p>
                    </div>
                </div>
                <div className='mt-3 col-md-1'>
                    <div className='d-flex justify-content-start'>
                        <button className='m-1 btn btn-md btn-danger' onClick={deleteBook}>Delete</button>
                    </div>
                </div>
                <button className='m1 btn btn-md main-color text-white' onClick={increaseQuantity}>Add Quantity</button>
                <button className='m1 btn btn-md btn-warning' onClick={decreaseQuantity}>Decrease Quantity</button>
            </div>
        </div>
    );
}