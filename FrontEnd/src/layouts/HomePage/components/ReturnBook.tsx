import React from 'react'
import { Link } from 'react-router-dom';
import BookModel from '../../../models/BookModel';
// TODO This is a functional component in React called ReturnBook which takes a book object as a prop and returns a div that contains information about the book. The information displayed is the book title, author, and an image of the book. The div contains a "Reserve" button that links to a page at the route checkout/{props.book.id}. If the book.img property is falsy, the component displays a default book image.
export const ReturnBook: React.FC<{book: BookModel}> = (props) => {
    return (
        <div className='col-xs-6 col-sm-6 col-md-4 col-lg-3 mb-3'>
            <div className='text-center'>
                {props.book.img ? 
                    <img
                        src={props.book.img}
                        width='151'
                        height='233'
                        alt="book"
                    />
                    :
                    <img
                        src={require('./../../../Images/BooksImages/book-luv2code-1000.png')}
                        width='151'
                        height='233'
                        alt="book"
                    />
                }
                <h6 className='mt-2'>{props.book.title}</h6>
                <p>{props.book.author}</p>
                <Link className='btn main-color text-white' to={`checkout/${props.book.id}`}>Reserve</Link>
            </div>
        </div>
    );
}