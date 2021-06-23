package io.hala.james.service;

import io.hala.james.controller.MatchRequestDto;
import io.hala.james.match.Match;
import io.hala.james.match.MatchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchService {

  private final MatchRepository matchRepository;

  public List<Match> getAllMatches() {
    return matchRepository.findAll();
  }

  public void insertMatch(MatchRequestDto matchRequestDto) {
    matchRepository.save(Match.builder()
        .teamName(matchRequestDto.getTeamName())
        .matchDate(matchRequestDto.getDate())
        .build());
  }
}
