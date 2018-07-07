import React from 'react';
import $ from 'jquery';
import * as appConfig from '../AppConfiguration';

import './component.signup.css';

class SignUp extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            sectionsToDo: 3,
            isSetUserName: false,
            isSetPassword: false,
            isSetFirstName: false,
            isSetLastName: false
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.getState = this.getState.bind(this);
    }
    componentDidMount() {
        this.$el = $(this.el);
        this.submmitDiv = $(this.submmitDiv);

        let prevList = this.$el;
        let $this = this;

        $((() => {
            let btn;
            for (let t of this.submmitDiv.children()) {
                if ($(t).hasClass("next")) {
                    btn = t;
                    break;
                }
            }
            return btn;
        })()).on('click', function () { //can not click until text is provided. TODO
            let currItem = prevList.find("section.active"),
                nextItem = currItem.next();

            $this.setState(prevState => {
                currItem.removeClass('active');
                return {
                    sectionsToDo: prevState.sectionsToDo - 1
                };
            });

            ($this.getState().sectionsToDo === 1) ? (() => {
                nextItem.addClass("active")
                $($(this).siblings()).removeClass("hidden");
                $(this).addClass("hidden");
            })() : nextItem.addClass("active");
        });
    }

    componentDidUpdate(prevProps) {
        if (prevProps.children !== this.props.children) {
            //this.$el.trigger("chosen:updated");
            console.log(prevProps, this.props.children)
        }
    }

    getState = () => {
        return this.state;
    }

    handleUserNameChange = e => {
        this.setState({
                userName: e.target.value,
                isSetUserName: true
        });
    }

    handlePasswordChange = e => {

        this.setState({
            password: e.target.value,
            isSetPassword: true
        });
    }

    handleLastNameChange = e => {
        this.setState({
            lastName: e.target.value,
            isSetLastName: true
        });
    }

    handleEmailChange = e => {
        this.setState({
            email: e.target.value
        });
    }

    handleFirstNameChange = e => {
        this.setState({
            firstName: e.target.value,
            isSetFirstName: true
        });
    }

    handleSubmit = e => {

        e.preventDefault();

        if(this.state.isSetUserName && this.state.isSetPassword 
            && this.state.isSetFirstName && this.state.lastName){
                fetch(`${appConfig.apiURL}/api/signup`, {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                    body: JSON.stringify(this.state)
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
            }else{
                alert("please enter a valid username, password, first name, and last name")
            }
    }

    render() {
        return (
            <div>
                <form method="POST" onSubmit={this.handleSubmit} ref={el => this.el = el}>
                        {/* this div is not part of the slide class. This div must have a button with className .next */}
                        <div className="submitDiv" ref={submmitDiv => this.submmitDiv = submmitDiv}>
                            <input type="submit" name="Sign Up" className="hidden" value="Sign Up"/>                      
                            <button type="button" name="Next" className="next">
                                <a >Next</a>
                            </button>
                        </div>
                        <section className="slide active">
                            <input type="text" name="userName" placeholder="username" onChange={this.handleUserNameChange}/><br/>
                            <input type="password" name="password" placeholder="password" onChange={this.handlePasswordChange}/>
                        </section>
                        <section className="slide">
                            <input type="text" name="firstName" placeholder="First Name" onChange={this.handleFirstNameChange}/><br/>
                            <input type="text" name="lastName" placeholder="Last Name" onChange={this.handleLastNameChange}/>
                        </section>
                        <section className="slide">
                            <input type="text" name="email" placeholder="email" onChange={this.handleEmailChange}/>
                        </section>
                </form>
                
            </div>
        );
    }
}

export default SignUp;