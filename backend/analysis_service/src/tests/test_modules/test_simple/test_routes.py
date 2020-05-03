
def test_ping(test_client):
    response = test_client.get("/status/ping")
    assert response.status_code == 200
    assert response.json() == {"ping": "pong!"}
