package com.bilalalp.proteomicsdbresolver;

import com.bilalalp.proteomicsdbresolver.dto.MainResult;
import com.bilalalp.proteomicsdbresolver.dto.ProteotypicityDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CsvGenerator implements Serializable {

    @Value("${output.directory}")
    private String outputDirectory;

    @Value("${input.links.directory}")
    private String inputLinksDirectory;

    private Map<String, MainResult> getGeneratedObjects() {

        final Map<String, MainResult> proteotypicityDtoList = new HashMap<>();
        final Path dir = new File(inputLinksDirectory).toPath();
        try (final DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

            for (final Path path : stream) {
                proteotypicityDtoList.put(path.getFileName().toString(), mapTo(path.toFile()));
            }

            return proteotypicityDtoList;
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    private MainResult mapTo(final File file) {

        final ObjectMapper mapper = new ObjectMapper();

        try {
            final MainResult mainResult = mapper.readValue(file, MainResult.class);
            final List<ProteotypicityDto> collect = mainResult.getResultDto().getProteotypicityDtoList().stream().filter(k -> "protein".equals(k.getUniqueness())).collect(Collectors.toList());
            mainResult.getResultDto().setProteotypicityDtoList(collect);
            return mainResult;
        } catch (final IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void generateOutput() {

        final Map<String, MainResult> generatedObjects = getGeneratedObjects();

        for (final Map.Entry<String, MainResult> mainResult : generatedObjects.entrySet()) {
            final String key = mainResult.getKey();
            final MainResult value = mainResult.getValue();

            final List<ProteotypicityDto> proteotypicityDtoList = value.getResultDto().getProteotypicityDtoList();
            final String collect = proteotypicityDtoList.stream().map(ProteotypicityDto::toString).collect(Collectors.joining("\n"));

            try {
                Files.write(Paths.get(outputDirectory.concat("//".concat(key).concat(".csv"))), (ProteotypicityDto.getHeaders() + "\n" + collect).getBytes());
            } catch (final IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}