import axios from 'axios';
import APIClient from '@/components/common/services/clients/APIClient';

export default class AppClientImpl implements APIClient {
  private API_URL: string;

  constructor(apiURL: string) {
    this.API_URL = apiURL;
  }

  async get(url: string): Promise<any> {
    const httpResponse = await axios.get(`${this.API_URL}${url}`);

    return httpResponse;
  }

  async post(url: string, data: any): Promise<any> {
    const httpResponse = await axios.post(`${this.API_URL}${url}`, data);

    return httpResponse;
  }

  async put(url: string, data: any): Promise<any> {
    const httpResponse = await axios.put(`${this.API_URL}${url}`, data);

    return httpResponse;
  }

  async delete(url: string): Promise<any> {
    const httpResponse = await axios.delete(`${this.API_URL}${url}`);

    return httpResponse;
  }
}
