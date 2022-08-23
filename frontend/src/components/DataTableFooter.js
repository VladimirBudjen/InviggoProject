import Pagination from "react-bootstrap/Pagination";

const DataTableFooter = ({numberOfPages, currentPage, setCurrentPage}) => {

    const previousIndex = (() => currentPage - 1)();
    const nextIndex = (() => currentPage + 1)();

    const stepBack = () => {
        if (currentPage > 1) {
            window.scrollTo(0, 0);
            setCurrentPage(currentPage - 1);
        }
    }

    const stepForward = () => {
        if (currentPage < numberOfPages) {
            window.scrollTo(0, 0);
            setCurrentPage(currentPage + 1);
        }
    }

    const jumpToFist = () => {
        if(currentPage!==1){
            window.scrollTo(0, 0);
            setCurrentPage(1)
        }
    }

    const jumpToLast = () => {
        if (currentPage < numberOfPages) {
            window.scrollTo(0, 0);
            setCurrentPage(numberOfPages)
        }
    }

    return (

        <Pagination>
            <Pagination.First onClick={jumpToFist}/>
            <Pagination.Prev onClick={stepBack}/>


            {previousIndex >= 1 ? <Pagination.Item onClick={stepBack}>{previousIndex}</Pagination.Item> : null}
            <Pagination.Item active>{currentPage}</Pagination.Item>
            {nextIndex <= numberOfPages ? <Pagination.Item onClick={stepForward}>{nextIndex}</Pagination.Item> : null}


            <Pagination.Next onClick={stepForward}/>
            <Pagination.Last onClick={jumpToLast}/>
        </Pagination>
    );
}

export default DataTableFooter;
