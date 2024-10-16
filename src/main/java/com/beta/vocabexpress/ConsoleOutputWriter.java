package com.beta.vocabexpress;

import com.beta.vocabexpress.formatter.TextFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputWriter {
    private TextFormatter textFormatter;

    @Autowired
    ConsoleOutputWriter(TextFormatter textFormatter){
        this.textFormatter = textFormatter;
    }

    void println(String text){
        String formattedText =  textFormatter.format(text);
        System.out.println(formattedText);
    }

}
