import ShelfCurrentLoans from "../../../models/ShelfCurrentLoans";
// TODO This is a React functional component named "LoansModal" that is used to display a modal for loan options for a particular book. The component receives three props: shelfCurrentLoan, mobile, and returnBook and renewLoan functions.The component starts by wrapping the modal content in a div element with various class names and attributes for styling the modal. The modal header displays the title "Loan Options" and a close button. The body of the modal contains information about the loaned book, such as the author, title, and cover image. The loan status (due in x days, due today, or past due by x days) is displayed as well.Finally, there are two buttons in the body: "Return Book" and "Renew loan for 7 days". The first button triggers the returnBook prop function, which takes in the book.id of the loaned book as an argument, and the second button triggers the renewLoan prop function, with the same argument, unless the loan is past due (indicated by shelfCurrentLoan.daysLeft < 0), in which case it is disabled and displays a message. The modal footer contains a close button to dismiss the modal.
export const LoansModal: React.FC<{ shelfCurrentLoan: ShelfCurrentLoans, mobile: boolean, returnBook: any,
    renewLoan: any }> = (props) => {
    return (
        <div className='modal fade' id={props.mobile ? `mobilemodal${props.shelfCurrentLoan.book.id}` : 
            `modal${props.shelfCurrentLoan.book.id}`} data-bs-backdrop='static' data-bs-keyboard='false' 
            aria-labelledby='staticBackdropLabel' aria-hidden='true' key={props.shelfCurrentLoan.book.id}>
                <div className='modal-dialog'>
                    <div className='modal-content'>
                        <div className='modal-header'>
                            <h5 className='modal-title' id='staticBackdropLabel'>
                                Loan Options
                            </h5>
                            <button type='button' className='btn-close' data-bs-dismiss='modal' aria-label='Close'>
                            </button>
                        </div>
                        <div className='modal-body'>
                            <div className='container'>
                                <div className='mt-3'>
                                    <div className='row'>
                                        <div className='col-2'>
                                            {props.shelfCurrentLoan.book?.img ?
                                                <img src={props.shelfCurrentLoan.book?.img} 
                                                    width='56' height='87' alt='Book'/>
                                                :
                                                <img src={require('./../../../Images/BooksImages/book-luv2code-1000.png')} 
                                                    width='56' height='87' alt='Book'/>
                                            }
                                        </div>
                                        <div className='col-10'>
                                            <h6>{props.shelfCurrentLoan.book.author}</h6>
                                            <h4>{props.shelfCurrentLoan.book.title}</h4>
                                        </div>
                                    </div>
                                    <hr/>
                                    {props.shelfCurrentLoan.daysLeft > 0 && 
                                        <p className='text-secondary'>
                                            Due in {props.shelfCurrentLoan.daysLeft} days.
                                        </p>
                                    }
                                    {props.shelfCurrentLoan.daysLeft === 0 && 
                                        <p className='text-success'>
                                             Due Today.
                                        </p>
                                    }
                                    {props.shelfCurrentLoan.daysLeft < 0 && 
                                        <p className='text-danger'>
                                            Past due by {props.shelfCurrentLoan.daysLeft} days.
                                        </p>
                                    }
                                    <div className='list-group mt-3'>
                                        <button onClick={() => props.returnBook(props.shelfCurrentLoan.book.id)} 
                                           data-bs-dismiss='modal' className='list-group-item list-group-item-action' 
                                           aria-current='true'>
                                            Return Book
                                        </button>
                                        <button onClick={
                                            props.shelfCurrentLoan.daysLeft < 0 ? 
                                            (event) => event.preventDefault() 
                                            :
                                            () => props.renewLoan(props.shelfCurrentLoan.book.id)
                                        } 
                                            data-bs-dismiss='modal' 
                                            className={
                                                props.shelfCurrentLoan.daysLeft < 0 ? 
                                                'list-group-item list-group-item-action inactiveLink' : 
                                                'list-group-item list-group-item-action'
                                            }>
                                            {props.shelfCurrentLoan.daysLeft < 0 ? 
                                            'Late dues cannot be renewed' : 'Renew loan for 7 days'    
                                            }
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className='modal-footer'>
                            <button type='button' className='btn btn-secondary' data-bs-dismiss='modal'>
                                Close
                            </button>
                        </div>
                    </div>
                </div>
        </div>
    );
}