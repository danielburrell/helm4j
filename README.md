# helm4j

helm4j is a java wrapper around the popular helm binary integrating with 
zeroturnaround's excellent process executor.

## Usage

```java
ProcessExecutor pe = Helm4j.helm(helm)
                    .pull()
                    .destination(chartTarTmp)
                    .additionalArgs(...)
                    .executeAs();
```

You can then execute this command via the zero-turnaround API as follows:

```java
try {
    pe.directory(pullDir.toFile())
        .redirectErrorStream(true)
        .readOutput(true)
        .exitValue(0).execute();
} catch (InvalidExitValueException e) {
    logger.error("Helm exited with code {}", e.getExitValue());
    logger.error("Check the following helm logs for what went wrong: {}", e.getResult().outputUTF8());
    throw new HelmPullException();
}
```
