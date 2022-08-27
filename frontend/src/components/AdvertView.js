import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "../api/axios";
import * as Icon from "react-bootstrap-icons"

const AdvertView = () => {
    const {code} = useParams()
    const [advert, setAdvert] = useState({});
    const [user, setUser] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`advert/code/${code}`).then(r => {
            setAdvert(r.data)
            axios.get(`user/${r.data.ownerUsername}`).then(r=>setUser(r.data))
        })
    }, [code])

    const deleteItem= ()=>{
        axios.delete('advert/code/'+advert.code).then(() => {
            navigate('../', {replace:true})
        })
    }

    const updateItem= ()=>{
        navigate(`../update/${advert.code}`, {replace:true})
    }

    return (
        <section className="vh-100" style={{marginTop: "4%"}}>
            <div className="container py-5 h-100">
                <div className="card shadow-2-strong" style={{borderRadius: "1rem"}}>
                    <div className="card-body p-5 text-center">
                        <div className='row'>
                            <div className="col justify-content-start">
                                <h3 className="mb-5">{advert.name}</h3>
                                <div className='text-start'>
                                    <h5>Price: {advert.price}</h5>
                                    <h5>Category: {advert.category}</h5>
                                    <h5>Created: {new Intl.DateTimeFormat(navigator.language).format(advert.creationDate)}</h5>
                                    <h5>From: {advert.city}</h5>
                                    <hr/>
                                </div>
                                <div className='text-start'>
                                    <h5>By: {advert.ownerUsername}</h5>
                                    <h5>Contact-number: {user.phone}</h5>
                                    <p>User has been active since {new Intl.DateTimeFormat(navigator.language).format(user.registerationDate)}</p>
                                    <hr/>
                                </div>
                                <div>
                                    <h5>Description:</h5>{advert.description}
                                </div>
                                {localStorage.getItem('UserName')===advert.ownerUsername ?
                                <div>
                                    <hr/>
                                    <button className='btn btn-danger m-2' onClick={deleteItem} title='Delete advert'><Icon.Trash/></button>
                                    <button className='btn btn-info m-2' onClick={updateItem}  title='Update advert'><Icon.Pen/></button>
                                </div> : null}
                            </div>
                            <div className="col">
                                <img alt="img" src={advert.imageUrl}
                                     style={{width: '70%', maxWidth: '70%', maxHeight: '90%'}}/>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>)
}
export default AdvertView
