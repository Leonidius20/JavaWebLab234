package io.github.leonidius20.java_web_lab_234.domain;

import java.sql.Date;

public record BookRequest(
        int id, int userId, int bookId, String bookName,
        BorrowingType borrowingType, Date desiredDate, Date endDate, Status status
) {

    public enum BorrowingType {

        TAKE_HOME(1, "Take home"),
        READ_AT_LIBRARY(2, "Read at reading room");

        final int id;
        final String name;

        BorrowingType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public static BorrowingType fromId(int id) {
            return switch (id) {
                case 1 -> TAKE_HOME;
                case 2 -> READ_AT_LIBRARY;
                default -> null;
            };
        }

        public String getName() {
            return name;
        }
    }

    public enum Status {
        PENDING(1),
        TAKEN(2),
        RETURNED(3);

        final int id;

        Status(int id) { this.id = id; }

        public int getId() {
            return id;
        }

        public static Status fromId(int id) {
            return switch (id) {
                case 1 -> PENDING;
                case 2 -> TAKEN;
                case 3 -> RETURNED;
                default -> null;
            };
        }
    }

}
