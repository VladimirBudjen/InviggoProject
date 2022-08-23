import {Col, Container, Row, Table, Card} from "react-bootstrap";
import "../css/Table.css"
import PaginationBar from "./PaginationBar";
import {useState} from "react";
import DataTableHeader from "./DataTableHeader";
import DataTableBody from "./DataTableBody";

function HomePage() {

    const cat = {image: "https://i.natgeofe.com/n/46b07b5e-1264-42e1-ae4b-8a021226e2d0/domestic-cat_thumb.jpg", name: "Miluje", price:"333", city:"NS", category:"Tools"}

    let items =  new Array(23).fill(cat);
    const categories = ['All', 'Clothing', 'Tools', 'Sports', 'Accessories', 'Furniture', 'Pets', 'Games', 'Books', 'Technology']
    let userLoggedIn = false;
    const [currentPage, setCurrentPage] = useState(1);
    const [nameFilterValue, nameFilterValueChange] = useState('');
    const [mineOnly, mineOnlyChange] = useState(false);
    const [price, priceChange] = useState(categories[0]);
    const [category, categoryChange] = useState();


    return (
        <section className="vh-100">
            <Container >
                <Row/>
                <Row style={{margin: "80px"}}/>
                <Row>
                    <Col>
                        <Card style={{marginBottom:"30%"}}>
                            <Card.Header className="table-header">
                                <DataTableHeader categories={categories}
                                                 nameFilterValue={nameFilterValue}
                                                 nameFilterValueChange={nameFilterValueChange}
                                                 mineOnly={mineOnly} mineOnlyChange={mineOnlyChange}
                                                 priceChange={priceChange} category={category}
                                                 categoryChange={categoryChange}/>
                            </Card.Header>
                            <Card.Body className="table-header">
                                <DataTableBody userLoggedIn={userLoggedIn} data={items} currentPage={currentPage}/>
                            </Card.Body>
                            <Card.Footer  className="text-muted d-flex justify-content-center table-footer">
                                <PaginationBar numberOfPages={Math.ceil(items.length/20)} currentPage={currentPage}
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
