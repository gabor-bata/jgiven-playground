package jgiven.playground.service;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@link StringSplitterService}, using {@link ScenarioTest} as base.
 *
 * @author Gabor_Bata
 */
public class StringSplitterServiceTest extends ScenarioTest<StringSplitterServiceTest.GivenStage, StringSplitterServiceTest.WhenStage, StringSplitterServiceTest.ThenStage> {

    @Test
    public void should_properly_split_name_using_the_default_delimiter() {
        given().a_string_splitter_service();
        when().splits_$name("John Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    @Test
    public void should_properly_split_name_using_a_given_delimiter() {
        given().a_string_splitter_service().and().$delimiter_as_delimiter(",");
        when().splits_$name("John,Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    protected static class GivenStage extends Stage<GivenStage> {

        @ScenarioState
        private StringSplitterService stringSplitter;

        @ScenarioState
        private String delimiter;

        public GivenStage a_string_splitter_service() {
            this.stringSplitter = new StringSplitterService();
            return self();
        }

        public GivenStage $delimiter_as_delimiter(@Quoted String delimiter) {
            this.delimiter = delimiter;
            return self();
        }
    }

    protected static class WhenStage extends Stage<WhenStage> {

        @ScenarioState(required = true)
        private StringSplitterService stringSplitter;

        @ScenarioState
        private String delimiter;

        @ScenarioState
        private List<String> result;

        public void splits_$name(@Quoted String name) {
            result = stringSplitter.split(name, delimiter);
        }
    }

    protected static class ThenStage extends Stage<ThenStage> {

        @ScenarioState(required = true)
        private List<String> result;

        public void the_result_equals_to_$names(List<String> names) {
            assertThat(result, is(equalTo(names)));
        }
    }
}
