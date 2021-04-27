package com.example.practice15.services;


import com.example.practice15.models.Game;
import com.example.practice15.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public void addGame(Game game) {
        log.info("Save game {}",game);
        gameRepository.save(game);
    }

    public Game findGame(int id ) {
        log.info("find game by id {}",id);
        return gameRepository.findById(id).orElse(null);
    }

    public List<Game> findAllGames() {
        log.info("find all games");
        return gameRepository.findAll();
    }

    public void deleteGame(int id) {
        log.info("Delete game by id {}",id);
        gameRepository.delete(findGame(id));
    }

    public List<Game> filterGameName() {
        log.info("filter(sort) games by name");
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
    }

    public List<Game> filterById() {
        log.info("filter(sort) games by Id");
        return gameRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public List<Game> filterByCreationDate() {
        log.info("filter(sort) games by creation date");
        return gameRepository.findAll(Sort.by(Sort.Direction.ASC,"creationDate"));
    }
}
