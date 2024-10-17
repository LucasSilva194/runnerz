package dev.lucas.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class JdbcClientRunRepositoryTest {

    @Autowired
    JdbcClientRunRepository repository;

    @BeforeEach
    void setUp() {
        repository.create(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                3,
                Location.INDOOR));

        repository.create(new Run(2,
                "Tuesday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                5,
                Location.OUTDOOR));
    }


    @Test
    void findAll() {
        var runs = repository.findAll();
        assertEquals(2, runs.size(), "Should return 2 runs");
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void count() {
    }

    @Test
    void saveAll() {
    }

    @Test
    void findByLocation() {
    }
}