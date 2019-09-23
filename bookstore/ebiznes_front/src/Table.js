import React, {Component} from 'react';
import Row from './Row'


class Table extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: [],
            ids: props.ids,
            headers: props.headers,
            url: props.url
        };
    }

    componentDidMount() {
        fetch(this.state.url)
            .then((response) => response.json())
            .then((data) => {
                this.setState({data: data});
                if (data !== undefined && data.length !== 0 && data[0].hasOwnProperty('author')) {
                    fetch('http://localhost:9000/admin/author/all')
                        .then((response) => response.json())
                        .then(author => {
                            data.forEach(a => {
                                a.author = author[0].name
                            });
                            console.log(author[0].name);
                            this.setState({data: data})
                        });
                    data.forEach(a => console.log(a.author));
                }
            });
    }

    render() {
        return (
            <table className="table">
                <thead>
                <tr>
                    {this.state.headers.map(header => <th
                        key={header}>{header}</th>)}
                </tr>
                </thead>
                <tbody>
                {this.state.data.map((data) => <Row ids={this.state.ids}
                                                    data={data}
                                                    key={data.id}/>)}
                </tbody>
            </table>
        );
    }
}

export default Table;