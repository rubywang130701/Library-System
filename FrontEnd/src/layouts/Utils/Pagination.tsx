// TODO The component calculates the page numbers to display based on the currentPage and totalPages props. If currentPage is 1, it shows the first page, and the next two pages if they exist. If currentPage is greater than 1, it shows the previous two pages and the next two pages if they exist, with the current page in the center.The component returns a navigation element with a list of buttons representing the page numbers, and two buttons for the first and last pages. When a button is clicked, the paginate callback is invoked with the corresponding page number. The active button has a class of active to highlight the current page.
export const Pagination: React.FC<{currentPage: number,
     totalPages: number,
      paginate: any}> = (props) => {

        const pageNumbers = [];

        if (props.currentPage === 1 ) {
            pageNumbers.push(props.currentPage);
            if (props.totalPages >= props.currentPage + 1) {
                pageNumbers.push(props.currentPage + 1);
            }
            if (props.totalPages >= props.currentPage + 2) {
                pageNumbers.push(props.currentPage + 2);
            }
        } else if (props.currentPage > 1) {
            if (props.currentPage >= 3 ) {
                pageNumbers.push(props.currentPage - 2);
                pageNumbers.push(props.currentPage - 1);
            } else {
                pageNumbers.push(props.currentPage - 1);
            }

           pageNumbers.push(props.currentPage);
           
           if (props.totalPages >= props.currentPage + 1) {
            pageNumbers.push(props.currentPage + 1);
           }
           if (props.totalPages >= props.currentPage + 2) {
            pageNumbers.push(props.currentPage + 2);
           }
        }

        return (
            <nav aria-label="...">
                <ul className='pagination'>
                    <li className='page-item' onClick={() => props.paginate(1)}>
                        <button className='page-link'>
                            First Page
                        </button>
                    </li>
                    {pageNumbers.map(number => (
                        <li key={number} onClick={() => props.paginate(number)} 
                            className={'page-item ' + (props.currentPage === number ? 'active' : '')}>
                                <button className='page-link'>
                                    {number}
                                </button>
                        </li>
                    ))}
                    <li className='page-item' onClick={() => props.paginate(props.totalPages)}>
                        <button className='page-link'>
                            Last Page
                        </button>
                    </li>
                </ul>
            </nav>
        );
      }