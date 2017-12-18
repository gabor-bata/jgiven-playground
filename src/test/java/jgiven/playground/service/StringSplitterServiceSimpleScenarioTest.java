package jgiven.playground.service;

import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@link StringSplitterService}, using {@link SimpleScenarioTest} as base.
 *
 * @author Gabor_Bata
 */
public class StringSplitterServiceSimpleScenarioTest extends SimpleScenarioTest<StringSplitterServiceSimpleScenarioTest.Steps> {

    @Test
    public void should_properly_split_name() {
        given().a_string_splitter_service();
        when().splits_$name("John Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    protected static class Steps {

        private StringSplitterService stringSplitter;
        private List<String> names;

        public void a_string_splitter_service() {
            stringSplitter = new StringSplitterService();
        }

        public void splits_$name(@Quoted String name) {
            names = stringSplitter.split(name);
        }

        public void the_result_equals_to_$names(List<String> expectedNames) {
            assertThat(names, is(equalTo(expectedNames)));
        }
    }
}
