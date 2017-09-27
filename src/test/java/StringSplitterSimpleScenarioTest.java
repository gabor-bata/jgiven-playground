
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.junit.SimpleScenarioTest;
import static java.util.Arrays.asList;
import java.util.List;
import jgiven.playground.StringSplitter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Unit test for {@link StringSplitter}, using {@link SimpleScenarioTest} as base.
 *
 * @author Gabor_Bata
 */
public class StringSplitterSimpleScenarioTest extends SimpleScenarioTest<StringSplitterSimpleScenarioTest.Steps> {

    @Test
    public void should_properly_split_name() {
        given().a_string_splitter();
        when().splits_$name("John Doe");
        then().the_result_equals_to_$names(asList("John", "Doe"));
    }

    protected static class Steps {

        private StringSplitter stringSplitter;
        private List<String> names;

        public void a_string_splitter() {
            stringSplitter = new StringSplitter();
        }

        public void splits_$name(@Quoted String name) {
            names = stringSplitter.split(name);
        }

        public void the_result_equals_to_$names(List<String> names) {
            assertThat(names, is(equalTo(names)));
        }
    }
}
