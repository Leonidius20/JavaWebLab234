package io.github.leonidius20.java_web_lab_234.domain;

import java.util.List;

public record UserWithRequests (
    User user, List<BookRequest> requests
) {}
