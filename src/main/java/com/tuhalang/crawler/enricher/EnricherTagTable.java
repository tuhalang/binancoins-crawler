package com.tuhalang.crawler.enricher;

import org.jsoup.nodes.Element;

public class EnricherTagTable implements EnricherTag {

    private static final String TAG_TABLE = "table";
    private static final String COLUMN_DELIMITER = "|";
    private static final String ROW_DELIMITER = "---";

    @Override
    public boolean isProcess(Element element) {
        return TAG_TABLE.equalsIgnoreCase(element.tagName());
    }

    @Override
    public String enrich(Element element) {
        final Element tbody = element.selectFirst("tbody");
        boolean isFirstRow = true;
        final StringBuilder result = new StringBuilder();
        for (Element tr : tbody.children()) {
            final StringBuilder row = new StringBuilder(COLUMN_DELIMITER);
            for (Element td : tr.children()) {
                row.append(td.text()).append(COLUMN_DELIMITER);
            }
            result.append(row).append('\n');
            if (isFirstRow) {
                final StringBuilder header = new StringBuilder(COLUMN_DELIMITER);
                for (int i=0; i<tr.childrenSize(); i++) {
                    header.append(ROW_DELIMITER).append(COLUMN_DELIMITER);
                }
                result.append(header).append('\n');
                isFirstRow = false;
            }
        }
        return result.toString();
    }
}
