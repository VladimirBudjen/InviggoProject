import {Col, Container, Row, Card} from "react-bootstrap";
import "../css/Table.css"
import DataTableFooter from "./DataTableFooter";
import {useEffect, useState} from "react";
import DataTableHeader from "./DataTableHeader";
import DataTableBody from "./DataTableBody";
import axios from "../api/axios";


function HomePage() {

    const [items, setItems] = useState([])
    const categories = ['All', 'Clothing', 'Tools', 'Sports', 'Accessories', 'Furniture', 'Pets', 'Games', 'Books', 'Technology']
    const [currentPage, setCurrentPage] = useState(1);
    const [numberOfPages, setNumberOfPages] = useState(1);
    const [nameFilterValue, nameFilterValueChange] = useState('');
    const [mineOnly, mineOnlyChange] = useState(false);
    const [price, priceChange] = useState("all");
    const [category, categoryChange] = useState(categories[0]);

    useEffect(() => {
        currentPage!==1?setCurrentPage(1):refreshData();
    }, [mineOnly, price, category, nameFilterValue])

    useEffect(()=>{
        refreshData()
    },[currentPage])

    const refreshData = () => {
        const filterData = {
            "name": nameFilterValue,
            "ownerUserName": localStorage.getItem("UserName") !== null && mineOnly ? localStorage.getItem("UserName") : '',
            "categoryName": category.toLowerCase(),
            "page": currentPage - 1,
            "pageSize": "20",
            "minOrMax": price
        }
        axios.post('advert/bypage', filterData).then(r => {
            setItems(r.data.adverts);
            setNumberOfPages(r.data.numberOfPages);
        })
    }

    useEffect(() => {
        setCurrentPage(1);
        refreshData();
    }, [])

    return (
        <section className="vh-100">
            <Container>
                <Row/>
                <Row style={{margin: "80px"}}/>
                <Row>
                    <Col>
                        <Card style={{marginBottom: "30%"}}>
                            <Card.Header className="table-header">
                                <DataTableHeader categories={categories}
                                                 nameFilterValue={nameFilterValue}
                                                 nameFilterValueChange={nameFilterValueChange}
                                                 mineOnly={mineOnly} mineOnlyChange={mineOnlyChange}
                                                 priceChange={priceChange} price={price}
                                                 category={category} categoryChange={categoryChange}/>
                            </Card.Header>
                            <Card.Body className="table-header">
                                <DataTableBody
                                    refreshFunc={refreshData} userLoggedIn={localStorage.getItem('UserName') !== null}
                                    data={items} currentPage={currentPage}/>
                            </Card.Body>
                            <Card.Footer className="text-muted d-flex justify-content-center table-footer">
                                <DataTableFooter numberOfPages={numberOfPages} currentPage={currentPage}
                                                 setCurrentPage={setCurrentPage}
                                />
                            </Card.Footer>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </section>

    )
        ;
}

export default HomePage
