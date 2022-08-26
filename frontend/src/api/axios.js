import axios from "axios";

let baseURL=process.env.REACT_APP_BACKEND_URL_BASE

const getAuthHeader = () => {
    let authToken = localStorage.getItem("JWT");
    if (authToken === null) {
        return null;
    } else {
        return `Bearer ${authToken}`;
    }
};


export default axios.create({
    baseURL: baseURL,
    headers: {
       'Authorization':getAuthHeader()
    }
});

