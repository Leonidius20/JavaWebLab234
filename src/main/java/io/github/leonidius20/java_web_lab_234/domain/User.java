package io.github.leonidius20.java_web_lab_234.domain;

public record User(
        int id,
        String name,
        String passportNumber,
        Role role,
        boolean isBanned,
        byte[] passwordHash,
        byte[] salt
) {

    public enum Role { USER, LIBRARIAN, ADMIN }

    public User copyWithId(int newId) {
        return new User(newId, name, passportNumber, role, isBanned, passwordHash, salt);
    }

}
