import React, { Component } from 'react'

class Navbar extends Component {
    render() {
        return (
                <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href={'http://google.com'}>Brand</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                    <li class="active"><a href={'http://google.com'}>Link</a></li>
                    <li><a href={'http://google.com'}>Link</a></li>
                   
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    <li><a href={'http://google.com'}>Link</a></li>
                    <li class="dropdown">
                        <a href={'http://google.com'} class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        <li><a href={'http://google.com'}>Action</a></li>
                        <li><a href={'http://google.com'}>Another action</a></li>
                        <li><a href={'http://google.com'}>Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href={'http://google.com'}>Separated link</a></li>
                        </ul>
                    </li>
                    </ul>
                </div>
                </nav>
        )
    }
}

export default Navbar;
