package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagOL implements EnricherTag{
    private static final String TAG_OL = "ol";
    private static final String FORMAT = "- %s";

    @Override
    public boolean isProcess(Element element) {
        return TAG_OL.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        final StringBuilder result = new StringBuilder();
        for (Element child : element.children()) {
            result.append(String.format(FORMAT, child.text())).append('\n');
        }
        return result.toString();
    }
}
