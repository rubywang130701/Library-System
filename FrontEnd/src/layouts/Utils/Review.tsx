import ReviewModel from "../../models/ReviewModel";
import { StarsReview } from "./StarsReview";
// TODO This code defines a functional React component called Review. This component takes a single prop review which is of type ReviewModel and displays the information contained within.The component first converts the date stored in the review to a string and formats it to display the long form of the month, the day, and the year (e.g. "February 2, 2023").The component then returns a div with a column of width 8 for small screens and width 8 for medium screens. Inside the column, it displays the user email, the formatted date and the rating using the StarsReview component, and the review description. The component is separated from other components by a horizontal line.
export const Review: React.FC<{ review: ReviewModel }> = (props) => {
    
    const date = new Date(props.review.date);

    const longMonth = date.toLocaleString('en-us', { month: 'long' });
    const dateDay = date.getDate();
    const dateYear = date.getFullYear();

    const dateRender = longMonth + ' ' + dateDay + ', ' + dateYear;
    
    return (
        <div>
            <div className='col-sm-8 col-md-8'>
                <h5>{props.review.userEmail}</h5>
                <div className='row'>
                    <div className='col'>
                        {dateRender}
                    </div>
                    <div className='col'>
                        <StarsReview rating={props.review.rating} size={16} />
                    </div>
                </div>
                <div className='mt-2'>
                    <p>
                        {props.review.reviewDescription}
                    </p>
                </div>
            </div>
            <hr/>
        </div>
    );
}