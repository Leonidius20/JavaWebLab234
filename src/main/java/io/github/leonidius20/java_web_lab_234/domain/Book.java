package io.github.leonidius20.java_web_lab_234.domain;

public record Book(
        int id,
        String name,
        int year,
        int numberOfCopies,
        String authorName,
        int edition,
        int numberOfAvailableCopies) { }
