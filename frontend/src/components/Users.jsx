import React, {Component}  from 'react'
import { User } from './User'
import UserDataService from '../services/User.service';
import Pagination from "react-bootstrap-4-pagination";

export default class Users extends Component {

  constructor(props) {
    super(props);
    this.onChangeSearchUser = this.onChangeSearchUser.bind(this);
    this.retrieveUsers = this.retrieveUsers.bind(this);
    //this.refreshList = this.refreshList.bind(this);
    //this.removeAllUser = this.removeAllUser.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handlePageSizeChange = this.handlePageSizeChange.bind(this);

    this.state = {
      users: [],
      currentUser: null,
      currentIndex: -1,
      searchUser: "",

      page: 1,
      count: 0,
      pageSize: 3,
    };

    this.pageSizes = [3, 6, 9,12];

  }


  componentDidMount() {
    this.retrieveUsers();
  }



  onChangeSearchUser(e) {
    const searchUser = e.target.value;

    this.setState({
      searchUser: searchUser,
    });
    this.retrieveUsers();
  }

  getRequestParams(searchUser, page, pageSize) {
    let params = {};

    if (searchUser) {
      params["name"] = searchUser;
    }

    if (page) {
      params["page"] = page - 1;
    }

    if (pageSize) {
      params["size"] = pageSize;
    }

    return params;
  }

  retrieveUsers() {
    const { searchUser, page, pageSize } = this.state;
    const params = this.getRequestParams(searchUser, page, pageSize);

    UserDataService.getUsers(params)
      .then((response) => {

        const { users, totalPages } = response.data

        this.setState({
          users: users,
          count: totalPages,
        });
        console.log(response.data);
      })
      .catch((e) => {
        console.log(e);
          const { content, totalPages } = e.response.data
          
          this.setState({
            users: content,
            count: totalPages,
          });
        
      });
  }

  handlePageChange(event, value) {
    this.setState(
      {
        page: value,
      },
      () => {
        this.retrieveUsers();
      }
    );
  }

  handlePageSizeChange(event) {
    this.setState(
      {
        pageSize: event.target.value,
        page: 1
      },
      () => {
        this.retrieveUsers();
      }
    );
  }

  render() {
    const {
      searchUser,
      users,
      currentUser,
      page,
      count,
      pageSize,
    } = this.state;
    return <>
               <div className="list row">
                <div className="col-md-8">
                  <div className="input-group mb-3">
                    <input
                      type="text"
                      className="form-control"
                      placeholder="Search by Username"
                      value={searchUser}
                      onChange={this.onChangeSearchUser}
                    />
                  </div>
                </div>
              </div>
              <div className="col-md-6">
                <h4>Tutorials List</h4>
                 <div className="mt-3">
                    {"Items per Page: "} 
                      <select onChange={this.handlePageSizeChange} value={pageSize}>
                         {this.pageSizes.map((size) => (
                            <option key={size} value={size}>
                               {size}
                           </option>
                            ))}
                      </select>
                      <Pagination
                        className="my-3"
                        count={count}
                        page={page}
                        siblingCount={1}
                        boundaryCount={1}
                        variant="outlined"
                        shape="rounded"
                        onChange={this.handlePageChange}
                    />
                   
                   <ul className="list-group">
                  {users &&
                    users.map((user, index) => (
                      <User user={user}></User>
                    ))}
                </ul>
                    
                 </div>
              </div>
          </>
  }



}
