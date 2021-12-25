package io.github.leonidius20.java_web_lab_234.domain;

public record Book(
        int id,
        String name,
        int authorId,
        int publisherId,
        int year,
        int numberOfCopies,
        String publisherName,
        String authorName,
        int edition) { }
