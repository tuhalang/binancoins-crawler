package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class EnricherTagDiv implements EnricherTag {

    private static final String TAG_DIV = "div";

    @Override
    public boolean isProcess(Element element) {
        return TAG_DIV.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        final StringBuilder result = new StringBuilder();
        for (Node node : element.childNodes()) {
            if (node instanceof Element) {
                final Element child = (Element) node;
                result.append(EnricherFactory.enrich(child)).append('\n');
            }
            if (node instanceof TextNode) {
                final TextNode child = (TextNode) node;
                result.append(child.text()).append('\n');
            }
        }
        return result.toString();
    }
}
