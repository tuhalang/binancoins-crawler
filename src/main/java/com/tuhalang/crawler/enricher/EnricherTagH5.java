package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagH5 implements EnricherTag {

    private static final String TAG_H5 = "h5";
    private static final String FORMAT = "##### %s";

    @Override
    public boolean isProcess(Element element) {
        return TAG_H5.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return String.format(FORMAT, element.text());
    }
}
