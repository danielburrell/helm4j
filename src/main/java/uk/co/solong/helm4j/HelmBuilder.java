package uk.co.solong.helm4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelmBuilder {

    private static final Logger logger = LoggerFactory.getLogger(HelmBuilder.class);
    private final List<String> commandStrings;

    public HelmBuilder(String kbld) {
        commandStrings = new ArrayList<>();
        commandStrings.add(kbld);
    }
    public HelmBuilder() {
        this("helm");
    }

    List<String> getCommandStrings() {
        return commandStrings;
    }

    public HelmBuilder template() {
        commandStrings.add("template");
        return this;
    }

    public HelmBuilder pull() {
        commandStrings.add("pull");
        return this;
    }

    public HelmBuilder untar() {
        commandStrings.add("--untar");
        return this;
    }

    public HelmBuilder additionalArgs(List<String> additionalArgs) {
        commandStrings.addAll(additionalArgs);
        return this;
    }
    public HelmBuilder destination(Path path) {
        commandStrings.add("--destination");
        commandStrings.add(path.toString());
        return this;
    }

    public HelmBuilder set(String key, String value) {
        commandStrings.add("--set");
        commandStrings.add(key+"="+value);
        return this;
    }

    public HelmBuilder set(Map<String, String> valueOverrides) {
        List<String> overrideKeyPairParams = valueOverrides.entrySet().stream().map(x -> x.getKey() + "=" + x.getValue()).collect(Collectors.toList());
        for (String overrideKeyPairParam : overrideKeyPairParams) {
            commandStrings.add("--set");
            commandStrings.add(overrideKeyPairParam);
        }
        return this;
    }

    public HelmBuilder outputDir(Path outputDirectory) {
        commandStrings.add("--output-dir");
        commandStrings.add(outputDirectory.toAbsolutePath().toString());
        return this;
    }

    public HelmBuilder workingDirectory(Path workingDirectory) {
        commandStrings.add(workingDirectory.toAbsolutePath().toString());
        return this;
    }

    public ProcessExecutor executeAs() {
        logger.info("Execution plan: {}", commandStrings.stream().collect(Collectors.joining(" ")));
        return new ProcessExecutor().command(this.commandStrings);
    }
}
