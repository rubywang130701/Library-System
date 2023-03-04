import { useState } from 'react';
import { StarsReview } from './StarsReview';
// TODO The code defines a functional component called LeaveAReview. It takes in a prop submitReview, which is a function that is called when the user submits their review. The component makes use of React Hooks useState to manage the state of the star rating and the review description that the user inputs.The component displays a dropdown with a list of star ratings that the user can choose from. When the user selects a rating, the starValue function is called and sets the state of starInput to the value of the selected star rating. The displayInput state is also set to true, which will display a form for the user to input a description for their review.The form includes a textarea for the user to input a review description and a submit button. When the submit button is clicked, the props.submitReview function is called and passed in the starInput and reviewDescription state values as arguments.
export const LeaveAReview: React.FC<{ submitReview: any }> = (props) => {

    const [starInput, setStarInput] = useState(0);
    const [displayInput, setDisplayInput] = useState(false);
    const [reviewDescription, setReviewDescription] = useState('');

    function starValue(value: number) {
        setStarInput(value);
        setDisplayInput(true);
    }

    return (
        <div className='dropdown' style={{ cursor: 'pointer' }}>
            <h5 className='dropdown-toggle' id='dropdownMenuButton1' data-bs-toggle='dropdown'>
                Leave a review?
            </h5>
            <ul id='submitReviewRating' className='dropdown-menu' aria-labelledby='dropdownMenuButton1'>
                <li><button onClick={() => starValue(0)} className='dropdown-item'>0 star</button></li>
                <li><button onClick={() => starValue(.5)} className='dropdown-item'>.5 star</button></li>
                <li><button onClick={() => starValue(1)} className='dropdown-item'>1 star</button></li>
                <li><button onClick={() => starValue(1.5)} className='dropdown-item'>1.5 star</button></li>
                <li><button onClick={() => starValue(2)} className='dropdown-item'>2 star</button></li>
                <li><button onClick={() => starValue(2.5)} className='dropdown-item'>2.5 star</button></li>
                <li><button onClick={() => starValue(3)} className='dropdown-item'>3 star</button></li>
                <li><button onClick={() => starValue(3.5)} className='dropdown-item'>3.5 star</button></li>
                <li><button onClick={() => starValue(4)} className='dropdown-item'>4 star</button></li>
                <li><button onClick={() => starValue(4.5)} className='dropdown-item'>4.5 star</button></li>
                <li><button onClick={() => starValue(5)} className='dropdown-item'>5 star</button></li>
            </ul>
            <StarsReview rating={starInput} size={32}/>

            {displayInput && 
                <form method='POST' action='#'>
                    <hr/>

                    <div className='mb-3'>
                        <label className='form-label'>
                            Description
                        </label>
                        <textarea className='form-control' id='submitReviewDescription' placeholder='Optional'
                            rows={3} onChange={e => setReviewDescription(e.target.value)}>
                        </textarea>
                    </div>

                    <div>
                        <button type='button' onClick={() => props.submitReview(starInput, reviewDescription)} className='btn btn-primary mt-3'>Submit Review</button>
                    </div>
                </form>
            }

        </div>
    );
}