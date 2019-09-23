import React, {Component} from 'react';


class Store extends Component {

    constructor(props) {
        super(props);
        this.state = {
            dt: [],
            userId: '',
            success: ''
        };
        this.buy = this.buy.bind(this);
    }

    componentDidMount() {
        fetch('http://localhost:9000/admin/books/all')
            .then((response) => response.json())
            .then((data) => {
                this.setState({d: data});
                if (data !== undefined && data.length !== 0 && data[0].hasOwnProperty('author')) {
                    fetch('http://localhost:9000/admin/author/all')
                        .then((response) => response.json())
                        .then(author => {
                            data.forEach(a => {
                                a.author = author[0].name
                            });
                            console.log(author[0].name);
                            this.setState({d: data})
                        });
                    data.forEach(a => console.log(a.author));
                }
            });
        fetch('/user/info', {method: 'GET', credentials: 'include'})
            .then(response =>
                response.json()
            )
            .then(d => {
                console.log(d);
                this.setState({userId: d.userID})
            })
    }

    buy(id) {
        fetch('/admin/order/add',
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36'
                },
                credentials: "include",
                body: JSON.stringify({
                    id: 0,
                    book: id,
                    status: "new",
                    user: this.state.userId
                })
            }).then(response => response.json()).then(response => {
            if (response.status === 200) {
                this.setState({success: "Książka została zakupiona"})
            }
        });
    }


    render() {
        return (
            <div className="books">
                <h1>USER ID: {this.state.userId}</h1>
                {this.state.dt.map(o => <div className="card" key={o.id}>
                    <div className="card-body">
                        {o.title}
                        <button onClick={() => this.buy(o.id)}>Kup</button>
                    </div>
                </div>)}
                <p>{this.state.success}</p>
            </div>
        );
    }
}

export default Store;