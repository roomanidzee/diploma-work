import axios from 'axios';
import APIClient from '@/components/common/services/clients/APIClient';

export default class AppClientImpl implements APIClient {
  private API_URL: string;

  constructor(apiURL: string) {
    this.API_URL = apiURL;
  }

  async get(url: string, headersInput?: any): Promise<any> {
    if (headersInput !== undefined) {
      return axios.get(`${this.API_URL}${url}`, { headers: headersInput });
    }

    return axios.get(`${this.API_URL}${url}`);
  }

  async post(url: string, data: any, headersInput?: any): Promise<any> {
    if (headersInput !== undefined) {
      return axios.post(`${this.API_URL}${url}`, data, { headers: headersInput });
    }

    return axios.post(`${this.API_URL}${url}`, data);
  }

  async put(url: string, data: any, headersInput?: any): Promise<any> {
    if (headersInput !== undefined) {
      return axios.put(`${this.API_URL}${url}`, data, { headers: headersInput });
    }

    return axios.put(`${this.API_URL}${url}`, data);
  }

  async delete(url: string, headersInput?: any): Promise<any> {
    if (headersInput !== undefined) {
      return axios.delete(`${this.API_URL}${url}`, { headers: headersInput });
    }

    return axios.delete(`${this.API_URL}${url}`);
  }
}
