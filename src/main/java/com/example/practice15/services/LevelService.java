package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import com.example.practice15.repositories.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

@Service
@Slf4j
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;

    public void addLevel(Level level) {
        log.info("Save level {}",level);
        levelRepository.save(level);
    }

    public void deleteLevel(int id) {
        log.info("Delete level by id {}",id);
        levelRepository.delete(findLevel(id));
    }

    public Level findLevel(int id ) {
        log.info("find level by id {}",id);
        return levelRepository.findById(id).orElse(null);
    }

    public List<Level> findAllLevels() {
        log.info("find all levels");
        return levelRepository.findAll();
    }

    public Game findGameByLevel(int levelId) {
        log.info("find game by levelId {}",levelId);
        return Objects.requireNonNull(levelRepository.findById(levelId).orElse(null)).getGame();
    }

    public List<Level> filterLevelName() {
        log.info("filter(sort) levels by levelName");
        return levelRepository.findAll(Sort.by(Sort.Direction.DESC,"levelName"));
    }

    public List<Level> filterById() {
        log.info("filter levels by id > 3");
        return levelRepository.findByIdIsGreaterThan(3);
    }

    public List<Level> filterByComplexity() {
        log.info("filter levels by complexity = low");
        return levelRepository.findByComplexityEquals("low");
    }
}
