package jgiven.playground.controller.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import jgiven.playground.controller.StringSplitterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * {@link Stage} definition for {@link StringSplitterController}.
 *
 * @author Gabor_Bata
 */
@JGivenStage
public class StringSplitterControllerStage extends Stage<StringSplitterControllerStage> {

    @Autowired
    private StringSplitterController stringSplitterController;

    private MockMvc mvc;
    private String path;
    private String delimiter;
    private ResultActions mvcResult;

    @BeforeStage
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(stringSplitterController).build();
    }

    public StringSplitterControllerStage path(@Quoted String path) {
        this.path = path;
        return this;
    }

    public StringSplitterControllerStage delimiter_parameter(@Quoted String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public StringSplitterControllerStage get() throws Exception {
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(path).param("delimiter", delimiter).accept(MediaType.APPLICATION_JSON));
        return this;
    }

    public StringSplitterControllerStage the_status_is(HttpStatus status) throws Exception {
        mvcResult.andExpect(status().is(status.value()));
        return this;
    }

    public StringSplitterControllerStage the_content_is(String content) throws Exception {
        mvcResult.andExpect(content().json(content));
        return this;
    }
}
