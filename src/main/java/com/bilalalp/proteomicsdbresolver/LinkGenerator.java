package com.bilalalp.proteomicsdbresolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LinkGenerator {

    @Value("${input.file.path}")
    private String inputDirectoryPath;

    @Value("${replaceable.link}")
    private String replaceableLink;

    @Value("${generated.link.path}")
    private String generatedLinkPath;

    public Map<String, String> getGeneratedLinks() {
        try {

            return Files.lines(new File(inputDirectoryPath).toPath())
                    .filter(input -> !input.isEmpty())
                    .collect(Collectors.toMap(input -> input, k -> String.format(replaceableLink, k)));
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    public void generateLinks() {

        final String collect = getGeneratedLinks().values().stream().collect(Collectors.joining("\n"));

        try {
            Files.write(Paths.get(generatedLinkPath), collect.getBytes());
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}