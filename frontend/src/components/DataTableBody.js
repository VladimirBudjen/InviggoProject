import {Table} from "react-bootstrap";

const DataTableBody = ({data, userLoggedIn, currentPage}) => {

    const renderData = () => {
            return data.slice((currentPage - 1) * 20, currentPage * 20)
            .sort((a, b) => {
                return a.creationDate < b.creationDate;
            })
            .map((item, index) => {
                return <tr key={index}>
                    <td>{index + (currentPage - 1) * 20 + 1}</td>
                    <td><img alt="img" style={{maxHeight: "200px"}}
                             src={item.imageUrl}/>
                    </td>
                    <td>{item.name}</td>
                    <td>{item.price}</td>
                    <td>{item.city}</td>
                    <td>{item.category}</td>
                </tr>
            })
    }
    return (
        <Table style={{backgroundColor: 'white'}} striped bordered hover>
            <thead>
            <tr>
                <th>#</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>City</th>
                <th>Category</th>
                {userLoggedIn ? <th>Buttonsss</th> : null}
            </tr>
            </thead>
            <tbody>
            {renderData()}
            </tbody>
        </Table>)
}
export default DataTableBody

