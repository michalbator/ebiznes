import React from "react";

class BookForm extends React.Component {
    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        fetch('http://localhost:9000/admin/books/add', {
            method: 'POST',
            body: data,
        });
    }

    render() {
        return (<form onSubmit={this.handleSubmit}>
                Id: <input type="text" name="id"/>
                Isbn: <input type="text" name="isbn"/>
                Title: <input type="text" name="title"/>
                Author: <input type="text" name="author"/>
                <input type="submit" value="Submit"/>
            </form>
        );
    }
}

export default BookForm;