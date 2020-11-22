import axios from 'axios';

export default axios.create({
  baseURL: 'http://localhost:8000/happyhouse',
  headers: {
    'Content-type': 'application/json',
  },
});
