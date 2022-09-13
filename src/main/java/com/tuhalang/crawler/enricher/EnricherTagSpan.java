package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagSpan implements EnricherTag {

    private static final String TAG_NAME = "span";

    @Override
    public boolean isProcess(Element element) {
        return TAG_NAME.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return element.text();
    }
}
