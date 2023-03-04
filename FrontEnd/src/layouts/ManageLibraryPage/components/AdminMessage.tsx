import { useState } from "react";
import MessageModel from "../../../models/MessageModel";
// This is a functional component in React that represents an admin message. It takes in a message prop of type MessageModel and a submitResponseToQuestion prop of type any, which is a callback function. The component uses the state hook useState to manage the display state of a warning message, and the value of the response. The component displays the content of the message and a form to submit a response. When the submit button is clicked, it checks if both the message.id and response are filled. If they are, it calls the submitResponseToQuestion callback function and passes the message.id and response as arguments. If either message.id or the response is empty, it sets the displayWarning state to true and displays a warning message.
export const AdminMessage: React.FC<{ message: MessageModel, 
    submitResponseToQuestion: any }> = (props, key) => {

    const [displayWarning, setDisplayWarning] = useState(false);
    const [response, setResponse] = useState('');

    function submitBtn() {
        if (props.message.id !== null && response !== '') {
            props.submitResponseToQuestion(props.message.id, response);
            setDisplayWarning(false);
        } else {
            setDisplayWarning(true);
        }
    }

    return (
        <div key={props.message.id}>
            <div className='card mt-2 shadow p-3 bg-body rounded'>
                <h5>Case #{props.message.id}: {props.message.title}</h5>
                <h6>{props.message.userEmail}</h6>
                <p>{props.message.question}</p>
                <hr/>
                <div>
                    <h5>Response: </h5>
                    <form action="PUT">
                        {displayWarning && 
                            <div className='alert alert-danger' role='alert'>
                                All fields must be filled out.
                            </div>
                        }
                        <div className='col-md-12 mb-3'>
                            <label className='form-label'> Description </label>
                            <textarea className='form-control' id='exampleFormControlTextarea1' rows={3} 
                                onChange={e => setResponse(e.target.value)} value={response}></textarea>
                        </div>
                        <div>
                            <button type='button' className='btn btn-primary mt-3' onClick={submitBtn}>
                                Submit Response
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}