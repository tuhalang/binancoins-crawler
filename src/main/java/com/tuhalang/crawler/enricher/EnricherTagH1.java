package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;
public class EnricherTagH1 implements EnricherTag {

    private static final String TAG_H1 = "h1";
    private static final String FORMAT = "# %s";
    @Override
    public boolean isProcess(Element element) {
        return TAG_H1.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return String.format(FORMAT, element.text());
    }
}
