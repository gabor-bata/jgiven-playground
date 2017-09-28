package jgiven.playground.controller;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.JGivenConfiguration;
import com.tngtech.jgiven.integration.spring.SimpleSpringScenarioTest;
import jgiven.playground.controller.configuration.JGivenPlaygroundConfiguration;
import jgiven.playground.controller.stage.StringSplitterControllerStage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

/**
 * Test for {@link StringSplitterController}.
 *
 * @author Gabor_Bata
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@JGivenConfiguration(JGivenPlaygroundConfiguration.class)
public class StringSplitterControllerTest extends SimpleSpringScenarioTest<StringSplitterControllerStage> {

    @Test
    @As("The path '/split' should split the given string using the default delimiter")
    public void the_path_split_shoult_split_the_given_string_using_the_default_delimiter() throws Exception {
        given().path("/split/Homer Simpson");
        when().get();
        then().the_status_is(OK).and().the_content_is("[\"Homer\", \"Simpson\"]");
    }

    @Test
    @As("The path '/split' should split the given string using the given delimiter")
    public void the_path_split_shoult_split_the_given_string_using_the_given_delimiter() throws Exception {
        given().path("/split/Homer,Simpson").with().delimiter_parameter(",");
        when().get();
        then().the_status_is(OK).and().the_content_is("[\"Homer\", \"Simpson\"]");
    }

    @Test
    @As("The path '/foo' should return NOT FOUND")
    public void the_path_foo_returns_not_found() throws Exception {
        given().path("/foo");
        when().get();
        then().the_status_is(NOT_FOUND);
    }
}
