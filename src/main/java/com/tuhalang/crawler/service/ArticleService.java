package com.tuhalang.crawler.service;

import org.springframework.stereotype.Service;

import com.tuhalang.crawler.model.Article;
import com.tuhalang.crawler.model.ArticleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public boolean isExists(String code) {
        return articleRepository.findByCode(code).isPresent();
    }

    public void save(Article article) {
        articleRepository.save(article);
    }
}
