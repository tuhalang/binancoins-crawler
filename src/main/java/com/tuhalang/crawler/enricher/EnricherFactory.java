package com.tuhalang.crawler.enricher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.jsoup.nodes.Element;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class EnricherFactory {
    private static final String EMPTY = "";
    private static final List<? extends EnricherTag> enricherTags = Arrays.asList(new EnricherTagA(),
                                                                                  new EnricherTagBr(),
                                                                                  new EnricherTagDiv(),
                                                                                  new EnricherTagFigure(),
                                                                                  new EnricherTagH1(),
                                                                                  new EnricherTagH2(),
                                                                                  new EnricherTagH3(),
                                                                                  new EnricherTagH4(),
                                                                                  new EnricherTagH5(),
                                                                                  new EnricherTagImg(),
                                                                                  new EnricherTagP(),
                                                                                  new EnricherTagStrong(),
                                                                                  new EnricherTagTable(),
                                                                                  new EnricherTagUL(),
                                                                                  new EnricherTagOL(),
                                                                                  new EnricherTagSpan());

    private EnricherFactory() {}

    public static String enrich(Element element) {
        final Optional<? extends EnricherTag> enricherTag = enricherTags.stream().filter(
                enricher -> enricher.isProcess(element)).findFirst();
        if (enricherTag.isPresent()) {
            return enricherTag.get().enrich(element);
        }
        log.error("Not found enricher for element: {}", element);
        return EMPTY;
    }
}
