class ApiService {

    headers = {
        'Accept': 'text/plain',
        'Content-Type': 'text/plain'
    }

    BASE_URL = 'http://localhost:8080/api/v1/kubernetes-demo'

    async getGreeting() {
        return await fetch(`${this.BASE_URL}/greet`, {
            method: 'GET',
            headers: this.headers
        });
    }
}

export default ApiService;
