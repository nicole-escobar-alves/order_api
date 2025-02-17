package br.com.postech.techchallenge.orderapi.infrastructure.config.testcontainer;

@TestConfiguration
public class TestContainer {

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        container.start();
        return container;
    }
}
