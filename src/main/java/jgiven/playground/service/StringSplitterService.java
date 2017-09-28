package jgiven.playground.service;

import static java.util.Arrays.stream;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

/**
 * A class which could split string with the a given delimiter.
 *
 * @author Gabor_Bata
 */
@Component
public class StringSplitterService {

    private static final String DEFAULT_DELIMITER = " ";

    /**
     * Splits the given string using the default delimiter.
     *
     * @param string the string
     * @return a list representing the split string
     */
    public List<String> split(String string) {
        return split(string, DEFAULT_DELIMITER);
    }

    /**
     * Splits the given string using the given delimiter.
     *
     * @param string the string
     * @param delimiter the delimiter
     * @return a list representing the split string
     */
    public List<String> split(String string, String delimiter) {
        requireNonNull(string);
        String resolvedDelimiter = Optional.ofNullable(delimiter).orElse(DEFAULT_DELIMITER);
        return unmodifiableList(stream(string.split(resolvedDelimiter)).collect(toList()));
    }
}
