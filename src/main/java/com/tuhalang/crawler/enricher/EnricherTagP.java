package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagP implements EnricherTag {

    private static final String TAG_P = "p";

    @Override
    public boolean isProcess(Element element) {
        return TAG_P.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return element.html();
    }
}
