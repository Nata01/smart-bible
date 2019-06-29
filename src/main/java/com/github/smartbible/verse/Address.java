package com.github.smartbible.verse;

import java.util.Objects;

public class Address {
    private String scripture;
    private int chapterNumber;
    private int verseNumber;

    public Address(String scripture, int chapterNumber, int verseNumber) {
        this.scripture = scripture;
        this.chapterNumber = chapterNumber;
        this.verseNumber = verseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return chapterNumber == address.chapterNumber &&
                verseNumber == address.verseNumber &&
                Objects.equals(scripture, address.scripture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scripture, chapterNumber, verseNumber);
    }
}
