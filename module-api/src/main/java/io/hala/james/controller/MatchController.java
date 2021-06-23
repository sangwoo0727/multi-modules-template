package io.hala.james.controller;

import io.hala.james.match.Match;
import io.hala.james.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchController {

  private final MatchService matchService;

  @GetMapping("/match")
  public ResponseEntity<?> getMatches() {
    return ResponseEntity.ok(matchService.getAllMatches());
  }

  @PostMapping("/match")
  public ResponseEntity<?> insertMatch(@RequestBody MatchRequestDto matchRequestDto) {
    matchService.insertMatch(matchRequestDto);
    return ResponseEntity.ok(null);
  }
}
