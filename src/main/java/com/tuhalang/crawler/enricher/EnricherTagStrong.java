package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagStrong implements EnricherTag{

    private static final String TAG_STRONG = "strong";
    private static final String FORMAT = "**%s**";

    @Override
    public boolean isProcess(Element element) {
        return TAG_STRONG.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return String.format(FORMAT, element.text());
    }
}
