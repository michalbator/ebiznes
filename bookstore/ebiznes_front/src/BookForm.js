import React from "react";


class BookForm extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            formData: props.data,
            url: props.url,
            authors: [],
            countries: []
        };

    }

    componentDidMount() {
        fetch('http://localhost:9000/admin/author/all')
            .then((response) => response.json())
            .then(data => this.setState({authors: data}))
        fetch('http://localhost:9000/admin/countries/all')
            .then((response) => response.json())
            .then(data => this.setState({countries: data}))
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        fetch('http://localhost:9000/' + this.state.url, {
            method: 'POST',
            body: data,
        });
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <div><label>Tytuł</label><input id='title' name='title'/>
                    </div>
                    <div>
                        <label>Autor</label><select id='author' name='author'>{this.state.authors.map(a =>
                        <option value={a.id}>{a.name}</option>)}</select></div>
                    <div><label>ISBN</label><input id='isbn' name='isbn'/>
                    </div>
                    <input type="hidden" id="id" name="id" value="0"/>
                    <input type="submit" value="Wyślij"/>
                </form>
            </div>
        );
    }
}

export default BookForm;