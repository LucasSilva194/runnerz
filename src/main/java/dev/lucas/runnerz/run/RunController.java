package dev.lucas.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a Spring MVC controller
@RequestMapping("api/runs") // Maps HTTP requests to /api/runs to this controller
public class RunController {

    private final RunRepository runRepository;

    // Constructor injection of RunRepository
    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }

    // FIND ALL
    @GetMapping("") // Maps HTTP GET requests to /api/runs to this method
    List<Run> findAll() {
        return runRepository.findAll(); // Returns all runs
    }

    // FIND BY ID
    @GetMapping("/{id}") // Maps HTTP GET requests to /api/runs/{id} to this method
    Run findById(@PathVariable Integer id) { // Extracts the id from the URL
        Optional<Run> run = runRepository.findById(id); // Finds the run by id
        if (run.isEmpty()) {
            throw new RunNotFoundException(); // Throws exception if run not found
        }
        return run.get(); // Returns the found run
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED) // Sets the response status to 201 Created
    @PostMapping("") // Maps HTTP POST requests to /api/runs to this method
    void create(@Valid @RequestBody Run run) { // Validates and extracts the request body
        runRepository.save(run); // Creates a new run
    }

    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT) // Sets the response status to 204 No Content
    @PutMapping("/{id}") // Maps HTTP PUT requests to /api/runs/{id} to this method
    void update(@RequestBody Run run, @PathVariable Integer id) { // Extracts the request body and id from the URL
        runRepository.save(run); // Updates the run with the given id
    }

    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT) // Sets the response status to 204 No Content
    @DeleteMapping("/{id}") // Maps HTTP DELETE requests to /api/runs/{id} to this method
    void delete(@PathVariable Integer id) { // Extracts the id from the URL
        runRepository.delete(runRepository.findById(id).get()); // Deletes the run with the given id
    }

    // FIND BY LOCATION
    @GetMapping("/location/{location}") // Maps HTTP GET requests to /api/runs/location/{location} to this method
    List<Run> findAllByLocation(@PathVariable String location) { // Extracts the location from the URL
        return runRepository.findAllByLocation(location); // Returns all runs with the given location
    }
}
