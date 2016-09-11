package com.bilalalp.proteomicsdbresolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources(value = {@PropertySource("application.properties")})
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final LinkGenerator linkGenerator;

    private final CsvGenerator csvGenerator;

    @Value("${selection}")
    private String selection;

    @Autowired
    public DemoApplication(final LinkGenerator linkGenerator, final CsvGenerator csvGenerator) {
        this.linkGenerator = linkGenerator;
        this.csvGenerator = csvGenerator;
    }

    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(final String... strings) throws Exception {

        if ("1".equals(selection)) {
            linkGenerator.generateLinks();
        } else if ("2".equals(selection)) {
            csvGenerator.generateOutput();
        }
    }
}