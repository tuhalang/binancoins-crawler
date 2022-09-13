package com.tuhalang.crawler.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tuhalang.crawler.enricher.EnricherFactory;
import com.tuhalang.crawler.model.Article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class EnrichPostService {
    private static final Pattern COMPILE = Pattern.compile("[#\n-]+");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    @Value("${html.xpath.article}")
    private String articleXpath;
    @Value("${html.xpath.title}")
    private String titleXpath;
    @Value("${html.xpath.image}")
    private String imageXpath;
    @Value("${html.xpath.published-at}")
    private String publishedAtXpath;

    public Article enrich(String url, boolean hotNews) throws IOException {
        final Document page = Jsoup.connect(url).get();

        final Element article = page.selectXpath(articleXpath).get(0);
        final Element publishedAt = page.selectXpath(publishedAtXpath).get(0);
        final Element title = page.selectXpath(titleXpath).get(0);
        final Element image = page.selectXpath(imageXpath).get(0);
        final String content = EnricherFactory.enrich(article);
        final String description = COMPILE.matcher(content.substring(0, Math.min(150, content.length()))).replaceAll("").trim();

        return Article.builder()
                      .content(content)
                      .description(description)
                      .title(title.text())
                      .slug(image.attr("src"))
                      .hotNews(hotNews)
                      .publishedAt(LocalDateTime.parse(publishedAt.attr("datetime").substring(0, 19), FORMATTER))
                      .build();

    }

}
