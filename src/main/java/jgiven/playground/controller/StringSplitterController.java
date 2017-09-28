package jgiven.playground.controller;

import java.util.List;
import jgiven.playground.service.StringSplitterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample controller which invokes {@link StringSplitterService}.
 *
 * @author Gabor_Bata
 */
@RestController
public class StringSplitterController {

    private final StringSplitterService stringSplitterService;

    /**
     * Creates a new {@link StringSplitterController} with the given {@code stringSplitterService}.
     *
     * @param stringSplitterService the string splitter service
     */
    public StringSplitterController(StringSplitterService stringSplitterService) {
        this.stringSplitterService = stringSplitterService;
    }

    /**
     * Splits the string provided in the given path parameter and optional {@code delimiter} query parameter.
     *
     * @param string the string
     * @param delimiter the delimiter (optional)
     * @return a list representing the split string
     */
    @GetMapping("/split/{string}")
    public List<String> split(@PathVariable String string, @RequestParam(required = false) String delimiter) {
        return stringSplitterService.split(string, delimiter);
    }
}
