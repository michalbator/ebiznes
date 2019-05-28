import React from 'react';
import {
    Route,
    NavLink,
    HashRouter
} from "react-router-dom";
import Main from "./Main";
import Form from "./Form";
import Table from "./Table";

class Menu extends React.Component {
    render() {
        return <HashRouter>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <NavLink className="navbar-brand" to='/'>Strona główna</NavLink>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <NavLink className="nav-link" to='/book/add'>Dodaj książkę</NavLink>
                    <NavLink className="nav-link" to='/country/add'>Dodaj kraj</NavLink>
                    <NavLink className="nav-link" to='/user/add'>Dodaj użytkownika</NavLink>
                    <NavLink className="nav-link" to='/author/add'>Dodaj autora</NavLink>
                    <NavLink className="nav-link" to="/book/list">Lista książek</NavLink>
                    <NavLink className="nav-link" to="/user/list">Lista użytkowników</NavLink>
                    <NavLink className="nav-link" to="/country/list">Lista państw</NavLink>
                    <NavLink className="nav-link" to='/author/list'>Lista autorów</NavLink>
                </div>
            </nav>
            <div className="content">
                <Route exact path="/" component={Main}/>
                <Route path="/book/add" component={() => <Form data={[{id: "isbn", label: "ISBN"},
                    {id: "title", label: "Tytuł"}, {id: "author", label: "Autor"}]} url={"admin/books/add"}/>}/>
                <Route path="/user/add" component={() => <Form data={[{id: "login", label: "Nazwa użytkownika"},
                    {id: "password", label: "Hasło"}]} url={"admin/users/add"}/>}/>
                <Route path="/country/add" component={() => <Form data={[{id: "name", label: "Nazwa"},
                    {id: "shortName", label: "Nazwa skrócona"}]} url={"admin/country/add"}/>}/>
                <Route path="/author/add" component={() => <Form data={[{id: "name", label: "Imię i nazwisko"},
                    {id: "country", label: "Kraj"}]} url={"admin/author/add"}/>}/>
                <Route path="/book/list"
                       component={() => <Table headers={["ISBN", "Tytuł", "Autor"]} ids={["isbn", "title", "author"]}
                                               url={"admin/books/all"}/>}/>
                <Route path="/user/list"
                       component={() => <Table headers={["Login", "Hasło"]} ids={["login", "password"]}
                                               url={"admin/users/all"}/>}/>
                <Route path="/country/list"
                       component={() => <Table headers={["Nazwa", "Nazwa skrócona"]} ids={["name", "shortName"]}
                                               url={"admin/country/all"}/>}/>
                <Route path="/author/list"
                       component={() => <Table headers={["Imię i nazwisko", "Kraj"]} ids={["name", "country"]}
                                               url={"admin/author/all"}/>}/>
            </div>
        </HashRouter>
    }
}

export default Menu;