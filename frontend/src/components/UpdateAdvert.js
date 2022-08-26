import {useEffect, useState} from "react";
import axios from "../api/axios";
import {useNavigate, useParams} from "react-router-dom";
import {Button, Form, Stack} from "react-bootstrap";

const UpdateAdvert = () => {

    const navigate = useNavigate();
    const categories = ['clothing', 'tools', 'sports', 'accessories', 'furniture', 'pets', 'games', 'books', 'technology']
    let [advert, setAdvert] = useState({name:'', price:0, imageUrl:'', category:categories[0], city:''});
    const {code} = useParams()
    const renderCategories = ()=>{
        return categories.map(c=><option value={c} key={c}>{c.charAt(0).toUpperCase() + c.slice(1)}</option>)
    }

    const getAdvert=()=>{
        axios.get('advert/code/' + code).then(r => {
            setAdvert(r.data)
        })
    }

    useEffect(() => {
        axios.get('advert/code/' + code).then(r => {
            setAdvert(r.data)
        })
    }, [code])

    function update() {
        axios.put('advert/',advert).then(r => {
            alert(r.data)
            navigate('/', { replace: true })
        })
    }

    return (
        <section className="vh-100" style={{marginTop:"4%"}}>
            <div className="container py-5 h-100">
                        <div className="card shadow-2-strong" style={{borderRadius: "1rem"}}>
                            <div className="card-body p-5 text-center">
                                <div className='row'>
                                    <div className="col">
                                        <h3 className="mb-5">Edit advert</h3>
                                        <div className="form-outline mb-4">
                                        </div>
                                        <Form>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Name</Form.Label>
                                                <Form.Control type="text"  value={advert.name} onInput={event => setAdvert({
                                                    ...advert, name:event.target.value})} placeholder="Enter name" />
                                                <Form.Text className="text">
                                                    Please enter new name
                                                </Form.Text>
                                            </Form.Group>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Image url</Form.Label>
                                                <Form.Control type="url"  value={advert.imageUrl} onInput={event => setAdvert({
                                                        ...advert, imageUrl:event.target.value})} placeholder="Enter name" />
                                                <Form.Text className="text-muted">
                                                    Please enter new image url
                                                </Form.Text>
                                            </Form.Group>
                                            <Form.Group className="mb-3">
                                                <Form.Label>City</Form.Label>
                                                <Form.Control type="text"  value={advert.city} onInput={event => setAdvert({
                                                    ...advert, city:event.target.value})} placeholder="Enter city" />
                                                <Form.Text className="text-muted">
                                                    Please enter new name of city
                                                </Form.Text>
                                            </Form.Group>
                                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                                <Form.Label>Price</Form.Label>
                                                <Form.Control type="number" placeholder="Set price"
                                                              value={advert.price} onInput={event => setAdvert({...advert, price: event.target.value})} min={1} max={9999999}/>
                                            </Form.Group>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Category</Form.Label>
                                                <Form.Select value={advert.category} onChange={e=>{setAdvert({...advert, category:e.target.value});}} >
                                                    {renderCategories()}
                                                </Form.Select>
                                            </Form.Group>
                                            <Form.Group className="mb-3">
                                                <Form.Label>Description</Form.Label>
                                                <Form.Control as="textarea" type="text" value={advert.description}
                                                              onChange={e=>{setAdvert({...advert, description:e.target.value});}}/>
                                            </Form.Group>
                                            <Form.Group>
                                                <Stack direction="horizontal" gap={3}>
                                                    <Button variant="primary" type="submit" onClick={update}>
                                                        Submit
                                                    </Button>
                                                    <Button variant="primary" type="submit" onClick={getAdvert}>
                                                        Restart
                                                    </Button>
                                                </Stack>
                                            </Form.Group>
                                        </Form>
                                    </div>
                                    <div className="col">
                                        <img alt="img" src={advert.imageUrl} style={{width:'70%', maxWidth:'70%', maxHeight:'90%'}}/>
                                    </div>
                                </div>
                            </div>
                        </div>

            </div>
        </section>
    )
}
export default UpdateAdvert
