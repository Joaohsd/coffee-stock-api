var HttpStatus = require("http-status-codes")

const baseUrl = "http://localhost:9000/api"

const clientCpf = "111.111.111-11"

const correctClientBody = {
    "cpf": "111.111.111-11",
    "name": "Fulano",
    "birthDate": "11/11/1111",
    "estate": "Villa Farm",
    "email": "fulano@email.com",
    "isAdmin": false,
    "password": "fulano"
}

const incorrectClientBody = {
    "cpf": "111.111.111-1",
    "name": "Fulano",
    "birthDate": "11/11/1111",
    "estate": "Villa Farm",
    "email": "fulano@email.com",
    "isAdmin": false,
    "password": "fulano"
}

function createCorrectNewClient()
{
    cy.request({
      method: "POST", 
      url: `${baseUrl}/clients`, 
      body: correctClientBody,
      failOnStatusCode: false
    }).then((response) => {
      expect(response.status).to.eq(HttpStatus.StatusCodes.CREATED)
    })
}

function createIncorrectNewClient()
{
    cy.request({
      method: "POST", 
      url: `${baseUrl}/clients`, 
      body: incorrectClientBody,
      failOnStatusCode: false
    }).then((response) => {
      expect(response.status).to.eq(HttpStatus.StatusCodes.FORBIDDEN)
    })
}

function getClientInfo(clientCpf)
{
    cy.request({
        method: "GET", 
        url: `${baseUrl}/clients/${clientCpf}`,
        failOnStatusCode: false
    }).then((response) => {
        expect(response.status).to.eq(HttpStatus.StatusCodes.OK)
        expect(response.body.name).to.equal(correctClientBody.name);
        expect(response.body.email).to.equal(correctClientBody.email);
    })
}

function updateCorrectClient(client)
{
    cy.request({
        method: "PUT", 
        url: `${baseUrl}/clients`,
        body: client,
        failOnStatusCode: false
    }).then((response) => {
        expect(response.status).to.eq(HttpStatus.StatusCodes.OK)
        expect(response.body.name).to.equal(client.name);
        expect(response.body.email).to.equal(client.email);
    })
}

function updateIncorrectClient(client)
{
    cy.request({
        method: "PUT", 
        url: `${baseUrl}/clients`,
        body: client,
        failOnStatusCode: false
    }).then((response) => {
        expect(response.status).to.eq(HttpStatus.StatusCodes.NOT_FOUND)
    })
}

function clearClient()
{
    cy.request({
        method: 'DELETE',
        url: `${baseUrl}/clients/${clientCpf}`
    }).then((response) => {
        expect(response.status).to.eq(HttpStatus.StatusCodes.OK);
        expect(response.body).to.be.empty;
    });
}

const correctStockBody = {
    "quantity": 10,
    "coffeeType": "Arabic",
    "coffeeCupping": 10,
    "status": "Submitted",
    "clientCpf": "111.111.111-11"
}

function createCorrectNewStock()
{
    cy.request({
        method: "POST", 
        url: `${baseUrl}/stocks`, 
        body: correctStockBody,
        failOnStatusCode: false
    }).then((response) => {
    expect(response.status).to.eq(HttpStatus.StatusCodes.CREATED)
    })
}

describe('Perform CRUD in Client entity', () => {
    after(() => {
        clearClient();
    }),

    it('Test if correct client has been created correctly', () => {
        // given / when/ then
        createCorrectNewClient();
    }),

    it('Test if incorrect client has been blocked correctly', () => {
        // given / when/ then
        createIncorrectNewClient();
    }),

    it('Test if correct client has been returned correctly', () => {
        // given / when/ then
        getClientInfo(clientCpf);
    }),

    it('Test if client has been updated correctly', () => {
        // given 
        let clientBody = {
            "cpf": "111.111.111-11",
            "name": "Fulano",
            "birthDate": "11/11/1111",
            "estate": "Villa Farm",
            "email": "fulano@email.com",
            "isAdmin": false,
            "password": "fulano"
        };
        clientBody.name = "Ciclano";

        // when/ then
        updateCorrectClient(clientBody);
    }),

    it('Test if incorrect client has not been updated', () => {
        // given 
        let clientBody = {
            "cpf": "111.111.111-11",
            "name": "Fulano",
            "birthDate": "11/11/1111",
            "estate": "Villa Farm",
            "email": "fulano@email.com",
            "isAdmin": false,
            "password": "fulano"
        };
        clientBody.cpf = "123.456.789-10";

        // when/ then
        updateIncorrectClient(clientBody);
    })

})

describe('Perform CRUD in Stock entity', () => {
    after(() => {
        clearClient();
    }),

    it('Test if correct client has been created correctly', () => {
        // given / when/ then
        createCorrectNewClient();
    }),

    it('Test if correct stock has been created correctly', () => {
        // given / when/ then
        createCorrectNewStock();
    })

})