
def test_validate_user(test_client):
    response = test_client.get('/auth/validate')
    
    assert response.status_code == 400
    assert response.json() == {'is_user': False}