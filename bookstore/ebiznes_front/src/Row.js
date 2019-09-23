import React, {Component} from 'react';

class Row extends Component {

    constructor(props) {
        super(props);
        this.onclick = this.onclick.bind(this);
    }

    onclick() {
        fetch("http://localhost:9000/user/info")
            .then((response) => response.text())
            .then((data) => {
                console.log(data);
            });
        fetch("http://localhost:9000/user/info")
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
            });
    }

    render() {
        return (
            <tr>
                {this.props.ids.map(data => <td key={data}>{this.props.data[data]}</td>)}
                <td>
                    <button onClick={this.onclick} value="Kup"/>
                </td>
            </tr>
        );
    }
}

export default Row;