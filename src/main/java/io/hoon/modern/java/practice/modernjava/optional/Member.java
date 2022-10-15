package io.hoon.modern.java.practice.modernjava.optional;

import java.time.LocalDate;

public class Member {

    private String name;
    private LocalDate birthday;

    public Member(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
