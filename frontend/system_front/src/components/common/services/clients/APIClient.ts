
export default interface APIClient {
    get(url: string, headersInput?: any): Promise<any>
    post(url: string, data: any, headersInput?: any): Promise<any>
    put(url: string, data: any, headersInput?: any): Promise<any>
    delete(url: string, headersInput?: any): Promise<any>
}
