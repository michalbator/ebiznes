import React from 'react';
import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import Main from "./Main";
import Form from "./Form";
import Table from "./Table";
import Login from "./Login";
import Store from "./Store"
import BookForm from "./BookForm";


class Menu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: ''
        }
    }

    componentDidMount() {
        fetch('/user/info', {
            method: 'GET',
            credentials: "include",
        }).then(response => response.json())
            .then(user => {
                this.setState({userId: user.userID});
                console.log(user)
            })
    }

    render() {
        if (this.state.userId !== '')
            return <HashRouter>
                <nav
                    className="navbar navbar-expand-lg navbar-light bg-light">
                    <NavLink className="navbar-brand" to='/'>Strona
                        główna</NavLink>
                    <div className="collapse navbar-collapse"
                         id="navbarNav">
                        <NavLink className="nav-link" to='/book/add'>Dodaj
                            książkę</NavLink>
                        <NavLink className="nav-link" to='/country/add'>Dodaj
                            kraj</NavLink>
                        <NavLink className="nav-link" to='/author/add'>Dodaj
                            autora</NavLink>
                        <NavLink className="nav-link" to='/order/add'>Dodaj
                            zamówienie</NavLink>
                        <NavLink className="nav-link" to="/book/list">Lista
                            książek</NavLink>
                        <NavLink className="nav-link" to="/country/list">Lista
                            państw</NavLink>
                        <NavLink className="nav-link" to='/author/list'>Lista
                            autorów</NavLink>
                        <NavLink className="nav-link" to='/order/user'>Lista
                            zamówień</NavLink>
                        <NavLink className="nav-link" to='/book/buy'>Kup
                            książkę</NavLink>
                        <NavLink className="nav-link"
                                 to='/login/'>Logowanie</NavLink>
                    </div>
                </nav>
                <div className="content">
                    <Route exact path="/" component={Main}/>
                    <Route path="/book/add" component={() => <BookForm
                        data={[{id: "isbn", label: "ISBN"},
                            {id: "title", label: "Tytuł"}, {
                                id: "author",
                                label: "Autor"
                            }]} url={"admin/books/add"}/>}/>
                    <Route path="/country/add" component={() => <Form
                        data={[{id: "name", label: "Nazwa"},
                            {id: "shortName", label: "Nazwa skrócona"}]}
                        url={"admin/country/add"}/>}/>
                    <Route path="/author/add" component={() => <Form
                        data={[{id: "name", label: "Imię i nazwisko"},
                            {id: "country", label: "Kraj"}]}
                        url={"admin/author/add"}/>}/>
                    <Route path="/order/add" component={() => <Form
                        data={[{id: "user", label: "Użytkownik"},
                            {id: "status", label: "Status"}, {
                                id: "book",
                                label: "Książka"
                            }]} url={"admin/order/add"}/>}/>
                    <Route path="/book/list"
                           component={() => <Table
                               headers={["ISBN", "Tytuł", "Autor"]}
                               ids={["isbn", "title", "author"]}
                               url={"admin/books/all"}/>}/>
                    <Route path="/country/list"
                           component={() => <Table
                               headers={["Nazwa", "Nazwa skrócona"]}
                               ids={["name", "shortName"]}
                               url={"admin/country/all"}/>}/>
                    <Route path="/author/list"
                           component={() => <Table
                               headers={["Imię i nazwisko", "Kraj"]}
                               ids={["name", "country"]}
                               url={"admin/author/all"}/>}/>
                    <Route path={"/order/user/"}
                           component={() => <Table
                               headers={["Użytkownik", "Status"]}
                               ids={["user", "status"]}
                               url={"admin/order/user/" + this.state.userId}/>}/>
                    <Route path="/book/buy"
                           component={() => <Store/>}/>
                    <Route path="/login"
                           component={() => <Login/>}/>
                </div>
            </HashRouter>;
        else {
            return <div><HashRouter>
                <nav
                    className="navbar navbar-expand-lg navbar-light bg-light">
                    <NavLink className="navbar-brand" to='/'>Strona
                        główna</NavLink>
                    <div className="collapse navbar-collapse"
                         id="navbarNav">
                        <NavLink className="nav-link" to='/book/add'>Dodaj
                            książkę</NavLink>
                        <NavLink className="nav-link"
                                 to='/login/'>Logowanie</NavLink>
                    </div>
                </nav>
                <div className="content">
                    <Route exact path="/" component={Main}/>
                    <Route path="/book/add" component={() => <Form
                        data={[{id: "isbn", label: "ISBN"},
                            {id: "title", label: "Tytuł"}, {
                                id: "author",
                                label: "Autor"
                            }]} url={"admin/books/add"}/>}/>
                    <Route path="/login"/>
                </div>
            </HashRouter>;
                <Login/></div>
        }
    }
}

export default Menu;