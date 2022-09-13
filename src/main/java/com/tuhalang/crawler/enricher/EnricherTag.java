package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public interface EnricherTag {
    boolean isProcess(Element element);
    String enrich(Element element);
}
