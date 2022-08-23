import {Button, Dropdown, Form, Stack} from "react-bootstrap";

const DataTableHeader = ({categories, nameFilterValue, nameFilterValueChange, mineOnly, mineOnlyChange, priceChange, category, categoryChange})=>{

    const renderCategories = ()=>{
        return categories.map(c=>{
            return <Dropdown.Item key={c} onClick={e=>{categoryChange(c)}}>{c}</Dropdown.Item>
        })
    }

    return (

        <Stack direction="horizontal" gap={1}>
            <Form.Control value={nameFilterValue} onChange={e=>nameFilterValueChange(e.target.value)} className="me-5" style={{width:"30%"}} placeholder="Search by name..."/>
            <Button variant="secondary" className="me-3">Submit</Button>
            <div className="vr me-3" />
            <label htmlFor={"mine-check-box"} className="me-3" style={{width:"15%"}}><b>Mine only:</b></label>
            <input onChange={e => {mineOnlyChange(!mineOnly);console.log(mineOnly)}} type="checkbox" id="mine-check-box" name="scales" />
            <br/><div className="vr"/><br/>
            <input onChange={() => priceChange("max")} type="radio" id="max-price-radio" name="fav_language" value="max"/>
            <label htmlFor="max-price-radio" className="me-5" style={{width:"25%"}}>Maximum price</label><br/>
            <input onChange={() => priceChange("min")} type="radio" id="min-price-radio" name="fav_language" value="min"/>
            <label htmlFor="min-price-radio" style={{width:"25%"}}>Minimum price</label><br/>
            <input onChange={() => priceChange("all")} type="radio" id="all-price-radio" name="fav_language" value="CSS"/>
            <label htmlFor="all-price-radio"  className="me-5">All</label><br/>
            <div className="vr"/>
            <br/>
            <label style={{width:"30%"}} htmlFor="category"><b>Select category:</b></label>
            <Dropdown  id="category" style={{width:"30%"}}>
                <Dropdown.Toggle variant="secondary" id="dropdown-basic" style={{width:"90%"}}>
                    {category}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    {renderCategories()}
                </Dropdown.Menu>
            </Dropdown>
        </Stack>)
}
export default DataTableHeader
