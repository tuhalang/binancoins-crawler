package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagBr implements EnricherTag {

    private static final String TAG_BR = "br";
    private static final String EMPTY = "";

    @Override
    public boolean isProcess(Element element) {
        return TAG_BR.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        return EMPTY;
    }
}
