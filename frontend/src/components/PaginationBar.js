import Pagination from "react-bootstrap/Pagination";

const PaginationBar = ({numberOfPages, currentPage, setCurrentPage}) => {

    const previousIndex = (() => currentPage - 1)();
    const nextIndex = (() => currentPage + 1)();

    const stepBack = () => {
        if (currentPage > 1)
            setCurrentPage(currentPage - 1);
    }

    const stepForward = () => {
        if (currentPage === numberOfPages) return
        else setCurrentPage(currentPage + 1);
    }

    const jumpToFist = () => {
        setCurrentPage(1)
    }

    const jumpToLast = () => {
        setCurrentPage(numberOfPages)
    }

    return (

        <Pagination>
            <Pagination.First onClick={jumpToFist}/>
            <Pagination.Prev onClick={stepBack}/>


            {previousIndex>=1 ? <Pagination.Item onClick={stepBack}>{previousIndex}</Pagination.Item> : null}
            <Pagination.Item active>{currentPage}</Pagination.Item>
            {nextIndex <= numberOfPages ? <Pagination.Item onClick={stepForward}>{nextIndex}</Pagination.Item> : null}


            <Pagination.Next onClick={stepForward}/>
            <Pagination.Last onClick={jumpToLast}/>
        </Pagination>
    );
}

export default PaginationBar;
