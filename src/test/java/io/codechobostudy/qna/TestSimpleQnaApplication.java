package io.codechobostudy.qna;

import io.codechobostudy.qna.config.SampleDataInitializer;
import io.codechobostudy.qna.config.WebConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        classes = {
                SimpleQnaApplication.class,
                WebConfiguration.class,
                SampleDataInitializer.class
        },
        type = FilterType.ASSIGNABLE_TYPE
))
public class TestSimpleQnaApplication {
}
