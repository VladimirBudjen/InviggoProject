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
    let userLoggedIn = false;
    const [currentPage, setCurrentPage] = useState(1);
    const [nameFilterValue, nameFilterValueChange] = useState('');
    const [mineOnly, mineOnlyChange] = useState(false);
    const [price, priceChange] = useState("all");
    const [category, categoryChange] = useState(categories[0]);

    useEffect(()=>{
        setCurrentPage(1);
    },[mineOnly, price, category, nameFilterValue])

    const reducedData = () => {
        let newData = [...items]
        newData = (category.toLowerCase() === "all" ? newData : newData.filter(item => item.category === category.toLowerCase()));
        newData = newData.filter(item=>{
            return item.name.toLowerCase().includes(nameFilterValue.toLowerCase());
        })
        if (price === "min" && newData.length>0) {
            newData = [newData.reduce((a, b) => a.price <= b.price ? a : b)]
        } else if (price === "max"  && newData.length>0)
            newData = [newData.reduce((a, b) => a.price >= b.price ? a : b)]
        return  newData
    };

    useEffect(() => {
        axios.get('advert/all').then(r => {
            setItems(r.data);
        })
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
                                <DataTableBody userLoggedIn={userLoggedIn} data={reducedData()} currentPage={currentPage}/>
                            </Card.Body>
                            <Card.Footer className="text-muted d-flex justify-content-center table-footer">
                                <DataTableFooter numberOfPages={Math.ceil(reducedData().length / 20)} currentPage={currentPage}
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
