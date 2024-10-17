package dev.lucas.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryRunRepositoryTest {

    InMemoryRunRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryRunRepository();

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
    void shouldFindAllRuns() {
        List<Run> runs = repository.findAll();
        assertEquals(2, runs.size(), "Should return 2 runs");
    }

    @Test
    void shouldFindRunById() {
        var run = repository.findById(1).get();
        assertEquals("Monday Morning Run", run.title(), "Should return the run with id 1");
        assertEquals(3, run.miles(), "Should return the run with id 1");
    }

    @Test
    void shouldCreateNewRun() {
        repository.create(new Run(
                3,
                "Wednesday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(45),
                4,
                Location.OUTDOOR));
        List<Run> runs = repository.findAll();
        assertEquals(3, runs.size(), "Should return 3 runs");
    }

    @Test
    void shouldUpdateRun() {
     repository.update(new Run(
                1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(45),
                5,
                Location.OUTDOOR), 1);
        var run = repository.findById(1).get();
        assertEquals(5, run.miles(), "Should return the run with id 1");
    }

    @Test
    void shouldDeleteRun() {
        repository.delete(1);
        List<Run> runs = repository.findAll();
        assertEquals(1, runs.size(), "Should return 1 run");
    }
}