package dev.jobyfoster.SoccerTeamTracker.controller;

import dev.jobyfoster.SoccerTeamTracker.dto.requestDto.CoachRequestDto;
import dev.jobyfoster.SoccerTeamTracker.model.Coach;
import dev.jobyfoster.SoccerTeamTracker.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @PostMapping
    public ResponseEntity<Coach> addCoach(@RequestBody CoachRequestDto coachRequestDto) {
        Coach coach = coachService.addCoach(coachRequestDto);
        return new ResponseEntity<>(coach, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Coach>> getAllCoaches() {
        List<Coach> coaches = coachService.getCoaches();
        return new ResponseEntity<>(coaches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoach(@PathVariable Long id) {
        Coach coach = coachService.getCoach(id);
        return new ResponseEntity<>(coach, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coach> updateCoach(@PathVariable Long id, @RequestBody CoachRequestDto coachRequestDto) {
        Coach updatedCoach = coachService.editCoach(id, coachRequestDto);
        return new ResponseEntity<>(updatedCoach, HttpStatus.OK);
    }
}
