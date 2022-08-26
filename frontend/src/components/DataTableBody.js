import {Table} from "react-bootstrap";
import axios from "../api/axios";
import {useNavigate} from "react-router-dom";

const DataTableBody = ({data, userLoggedIn, currentPage, refreshFunc}) => {

    let navigate = useNavigate();

    const deleteItem= (item)=>{
        axios.delete('advert/code/'+item.code).then(() => {
            refreshFunc()
        })
    }

    const updateItem= (item)=>{
        navigate('update/'+item.code)
    }

    const renderData = () => {
        return data.slice((currentPage - 1) * 20, currentPage * 20)
            .map((item, index) => {
                return <tr key={index}>
                    <td>{index + (currentPage - 1) * 20 + 1}</td>
                    <td style={{maxWidth:"260px"}}><img alt="img" style={{maxHeight: "200px", maxWidth:"250px", width:"230px"}}
                             src={item.imageUrl}/>
                    </td>
                    <td style={{maxWidth:"200px"}}>{item.name} {item.creationDate}</td>
                    <td>{item.price}</td>
                    <td style={{maxWidth:"200px"}}>{item.city}</td>
                    <td>{item.category}</td>
                    {localStorage.getItem('UserName')===item.ownerUsername ?
                        <td>
                            <table>
                                <tbody>
                                <tr>
                                    <td style={{textAlign: "center"}}>
                                        <button style={{marginTop: "65%", minWidth: "150%"}}
                                               onClick={()=>updateItem(item)} className="btn btn-dark">Edit
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td style={{textAlign: "center"}}>
                                        <button style={{marginTop: "20%", minWidth: "150%"}} className="btn btn-danger"
                                                onClick={()=>deleteItem(item)}>Delete
                                        </button>
                                    </td>
                                </tr>
                                </tbody>

                            </table>

                        </td>
                        : null}
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
                {userLoggedIn ? <th>Actions</th> : null}
            </tr>
            </thead>
            <tbody>
            {renderData()}
            </tbody>
        </Table>)
}
export default DataTableBody

