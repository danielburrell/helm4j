package uk.co.solong.helm4j;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelmBuilderTest {

    @Test
    public void returnDefaultHelm() {
        List<String> actual = Helm4j.helm().getCommandStrings();
        List<String> expected = Arrays.asList("helm");
        assertEquals(expected, actual);
        assertNotSame(expected, actual);
    }
}
