package uk.co.solong.helm4j;

public class Helm4j {

    public static HelmBuilder helm(String helmLocation) {
        return new HelmBuilder(helmLocation);
    }

    public static HelmBuilder helm() {
        return new HelmBuilder();
    }
}
