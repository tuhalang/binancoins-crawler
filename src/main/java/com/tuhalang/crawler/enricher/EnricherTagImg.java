package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagImg implements EnricherTag{

    private static final String TAG_IMG = "img";
    private static final String FORMAT = "![%s](%s)";
    @Override
    public boolean isProcess(Element element) {
        return TAG_IMG.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return String.format(FORMAT, element.attr("title"), element.attr("src"));
    }
}
