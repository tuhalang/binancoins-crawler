package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagH2 implements EnricherTag {

    private static final String TAG_H2 = "h2";
    private static final String FORMAT = "## %s";

    @Override
    public boolean isProcess(Element element) {
        return TAG_H2.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return String.format(FORMAT, element.text());
    }
}
