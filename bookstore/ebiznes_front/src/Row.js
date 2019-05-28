import React, {Component} from 'react';
import {NavLink} from "react-router-dom";

class Row extends Component {

    constructor(props) {
        super(props);
        this.onclick = this.onclick.bind(this);
    }

    onclick() {
        console.log(this.props.data);
    }

    render() {
        return (
            <tr>
                {this.props.ids.map(data => <td key={data}>{this.props.data[data]}</td>)}
                <td>
                    <button onClick={this.onclick} value="Szczegóły"/>
                    <NavLink className="btn btn-outline-primary" to="/">Szczegóły</NavLink>
                    <a href="/">Klik</a>
                </td>
            </tr>
        );
    }
}

export default Row;