import React from 'react';


class Login extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleUserNameChange = e => {
        this.setState({
            username: e.target.value
        });
    }

    handlePasswordChange = e => {
        this.setState({
            password: e.target.value
        });
    }
    handleSubmit = e => {
        e.preventDefault();

        /*fetch("http://localhost:8080/api/get?username="+this.state.username)
        .then(res => res.json())
        .then(res => {
            if(res.err){return res.err}
            
            console.log(res);
        }).then(err => alert("error: " + err));*/

        fetch("http://localhost:8080/api/login", {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    userName: this.state.username,
                    password: this.state.password
                })
            })
            .then(res => res.json())
            .then(res => {
                if (res.err) {
                    return res.err
                }

                console.log(res);
            })
            .then(err => {
                if (err) alert("error: " + err)
            });
    }

    render() {
        return (
            <form method="post" onSubmit={this.handleSubmit}>
                <input type="text" name="username" onChange={this.handleUserNameChange}/>
                <input type="password" name="password" onChange={this.handlePasswordChange}/>
                <input type="submit" name="login"/>
            </form>
        );
    }
}

export default Login;