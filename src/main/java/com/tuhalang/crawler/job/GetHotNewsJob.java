package com.tuhalang.crawler.job;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.tuhalang.crawler.model.Article;
import com.tuhalang.crawler.service.ArticleService;
import com.tuhalang.crawler.service.EnrichPostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "crawler.batch.job.name", havingValue = "GetHotNewsJob")
public class GetHotNewsJob implements ApplicationRunner {

    private final EnrichPostService enrichPostService;
    private final ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            final Document doc = Jsoup.connect("https://binancoins.com/hot-news").get();
            final Elements articles = doc.select("article");
            for (Element article : articles) {
                final Element link = article.selectFirst("a[href]");
                final String linkUrl = link.attr("abs:href");
                getNews(linkUrl);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void getNews(String url) {
        try {
            log.info("Start enrich url: {}", url);
            final String code = url.substring(url.lastIndexOf('-') + 1);
            if (!articleService.isExists(code)) {
                log.info("Code: {} is NOT exists, start enricher ...", code);
                final Article article = enrichPostService.enrich(url, true);
//                article.setCode(code);
                articleService.save(article);
                log.info("End enrich url: {}", url);
            } else {
                log.info("Code: {} is exists, don't start enricher ...", code);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


}
