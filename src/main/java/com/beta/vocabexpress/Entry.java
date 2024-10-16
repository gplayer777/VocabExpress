package com.beta.vocabexpress;

public class Entry {

    private String original;
    private String translation;

    Entry(String original, String translation){
        this.original = original;
        this.translation = translation;

    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }


    @Override
    public String toString(){
        return original+";"+translation;
    }
}
