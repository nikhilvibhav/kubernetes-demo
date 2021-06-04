import './App.css';
import ApiService from "./services/ApiService";
import {useEffect, useState} from "react";
import Moment from "react-moment";

const api = new ApiService();

function App() {
    const [greeting, setGreeting] = useState(null);

    useEffect(() => {
        api.getGreeting()
            .then(response => response.text())
            .then(text => setGreeting(text))
            .catch(error => console.log(error));
    });

    return (
        <div className="App">
            <header className="App-header">
                <h1>
                    <Moment format="DD/MM/YYYY HH:mm">{new Date()}</Moment> {greeting}
                </h1>
            </header>
        </div>
    );
}

export default App;
