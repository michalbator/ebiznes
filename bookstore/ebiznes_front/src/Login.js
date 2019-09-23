import React, {Component} from 'react';
import {
    BrowserRouter as Router
} from 'react-router-dom';


class Login extends Component {
    render() {
        return <Router>
            <div style={{marginTop: '20px'}}>
                <div style={{margin: '15px', textAlign: 'center'}}>
                    <label>Zaloguj się używając jednego z serwisów
                        autentykacyjnych</label>
                </div>
                <div style={{textAlign: 'center'}}>
                    <a href="http://localhost:9000/authenticate/facebook">
                        <img
                            src={process.env.PUBLIC_URL + '/facebook.png'}
                            alt={'Facebook'}/>
                    </a>
                    <a href="http://localhost:9000/authenticate/twitter">
                        <img
                            src={process.env.PUBLIC_URL + '/twitter.png'}
                            alt={'Twitter'}/>
                    </a>
                </div>
            </div>
        </Router>
    }
}

export default Login;