package com.inatel.coffeestock.model.service

import com.inatel.coffeestock.mock.MockClientRepository
import com.inatel.coffeestock.model.repository.ClientRepository
import com.inatel.coffeestock.model.entity.Client
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import java.time.LocalDate

class ClientServiceTest{

    private lateinit var clientRepository: ClientRepository
    private lateinit var clientService: ClientService

    @BeforeEach
    fun setup() {
        clientRepository = MockClientRepository()
        clientService = ClientService(clientRepository)
    }

    @Nested
    @DisplayName("Test scenario for GET CLIENTS")
    inner class GetClients{

        @Test
        @DisplayName("should provide a collection of clients with desired number of clients")
        fun verifyNumberOfClients() {
            // given
            val numberOfClients = 3

            // when
            val clients = clientService.getClients()

            // then
            assertEquals(numberOfClients, clients.size)
        }

        @Test
        @DisplayName("verifying if the retrieved clients are correct")
        fun correctClientsRetrieved() {
            // given
            val firstClientExpected = Client(123456, "Fulano Ciclano", "123.456.789-10", LocalDate.of(1999, 1, 1), "Santa Rita", "fulano@email.com.br", "fulano")
            val secondClientExpected = Client(234567, "Ciclano Fulano", "999.999.999-99", LocalDate.of(1996, 2, 2), "Rancho Alegre", "ciclano@email.com.br", "ciclano")
            val thirdClientExpected = Client(345678, "Federico José", "111.111.111-11", LocalDate.of(1980, 3, 3), "Boa Vista", "frederico@email.com.br", "frederico")

            // when
            val clients = clientService.getClients()

            // then
            assertAll("Clients",
                    { assertTrue(clients.contains(firstClientExpected)) },
                    { assertTrue(clients.contains(secondClientExpected)) },
                    { assertTrue(clients.contains(thirdClientExpected)) },
            )

        }
    }

    @Nested
    @DisplayName("Test scenario for GET CLIENT")
    inner class GetClient{

        @Test
        @DisplayName("should provide the client with desired id")
        fun verifyCorrectClientRetrieved() {
            // given
            val desiredClientID: Long = 123456
            val desiredClient = Client(123456, "Fulano Ciclano", "123.456.789-10", LocalDate.of(1999, 1, 1), "Santa Rita", "fulano@email.com.br", "fulano")

            // when
            val client = clientService.getClient(desiredClientID)

            // then
            assertEquals(desiredClient, client)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find client id")
        fun verifyIncorrectClientRetrieved(){
            // given
            val incorrectClientID: Long = 1111

            // when / then
            assertThrows(NoSuchElementException::class.java){
                clientService.getClient(incorrectClientID)
            }
        }

    }

    @Nested
    @DisplayName("Test scenario for UPDATE CLIENT")
    inner class UpdateClient{
        @Test
        @DisplayName("should provide the updated client")
        fun verifyCorrectClientUpdated() {
            // given
            val updatedClient = Client(123456, "Fulano Beltrano", "123.456.789-10", LocalDate.of(1999, 1, 1), "Santa Rita", "fulano.beltrano@email.com.br", "fulano")

            // when
            val returnedClient = clientService.updateClient(updatedClient)

            // then
            assertEquals(updatedClient, returnedClient)
        }

        @Test
        @DisplayName("should throw NoSuchElementException when doesn't find client id")
        fun verifyIncorrectClientUpdated() {
            // given
            val updatedClient = Client(1111, "Fulano Beltrano", "123.456.789-10", LocalDate.of(1999, 1, 1), "Santa Rita", "fulano.beltrano@email.com.br", "fulano")

            // when / then
            assertThrows(NoSuchElementException::class.java){
                clientService.updateClient(updatedClient)
            }
        }
    }

}