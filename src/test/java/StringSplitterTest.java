
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import static java.util.Arrays.asList;
import java.util.List;
import static java.util.Objects.requireNonNull;
import jgiven.playground.StringSplitter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Unit test for {@link StringSplitter}, using {@link ScenarioTest} as base.
 *
 * @author Gabor_Bata
 */
public class StringSplitterTest extends ScenarioTest<StringSplitterTest.GivenStage, StringSplitterTest.WhenStage, StringSplitterTest.ThenStage> {

    @Test
    public void should_properly_split_name_using_the_default_delimiter() {
        given().a_string_splitter();
        when().splits_$name("John Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    @Test
    public void should_properly_split_name_using_a_given_delimiter() {
        given().a_string_splitter().and().$delimiter_as_delimiter(",");
        when().splits_$name("John,Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    protected static class GivenStage extends Stage<GivenStage> {

        @ScenarioState
        private StringSplitter stringSplitter;

        @ScenarioState
        private String delimiter;

        public GivenStage a_string_splitter() {
            this.stringSplitter = new StringSplitter();
            return self();
        }

        public GivenStage $delimiter_as_delimiter(@Quoted String delimiter) {
            requireNonNull(delimiter);
            this.delimiter = delimiter;
            return self();
        }

    }

    protected static class WhenStage extends Stage<WhenStage> {

        @ScenarioState(required = true)
        private StringSplitter stringSplitter;

        @ScenarioState
        private String delimiter;

        @ScenarioState
        private List<String> result;

        public void splits_$name(@Quoted String name) {
            if (delimiter == null) {
                result = stringSplitter.split(name);
            } else {
                result = stringSplitter.split(name, delimiter);
            }
        }
    }

    protected static class ThenStage extends Stage<ThenStage> {

        @ScenarioState(required = true)
        private List<String> result;

        public void the_result_equals_to_$names(List<String> names) {
            assertThat(names, is(equalTo(names)));
        }
    }
}
