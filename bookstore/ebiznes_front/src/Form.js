import React from "react";

class Form extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            formData: props.data,
            url: props.url
        };
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
                    {this.state.formData.map(a => <div key={a.id}><label>{a.label}</label><input id={a.id} name={a.id}/></div>)}
                    <input type="hidden" id="id" name="id" value="0"/>
                    <input type="submit" value="Wyślij"/>
                </form>
            </div>
        );
    }
}

export default Form;