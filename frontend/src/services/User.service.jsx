import axios from 'axios';

const USER_URL = "http://localhost:8089/user";

class UserDataService {
  getUsers(params) {
    return axios.get(USER_URL, { params });
  }

  // other CRUD methods
}

export default new UserDataService();