package com.literaluraSpring.literalura.model;

public enum Idioma {
    es("es"),
    en("en"),
    fr("fr"),
    it("it"),
    de("de"),
    pt("pt");

    private String value;

    Idioma(String value) {
        this.value = value;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.value.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    @Override
    public String toString() {
        return value;
    }
}
