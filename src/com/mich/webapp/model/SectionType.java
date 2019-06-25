package com.mich.webapp.model;

public enum SectionType {
    PERSONAL("Особисті якості"),
    OBJECTIVE("Позиція"),
    ACHIEVEMENT("Досягнення"),
    QUALIFICATIONS("Кваліфікація"),
    EXPERIENCE("Досвід роботи"),
    EDUCATION("Освіта");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
