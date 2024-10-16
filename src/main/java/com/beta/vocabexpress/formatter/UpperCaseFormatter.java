package com.beta.vocabexpress.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UpperCaseFormatter implements TextFormatter {

    @Override
    public String format(String originalText) {
        return originalText.toUpperCase();
    }
}
