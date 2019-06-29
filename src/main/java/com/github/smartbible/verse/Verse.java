package com.github.smartbible.verse;

public class Verse {
    private String text;
    private Address address;

    public void setText(String text) {
        this.text = text;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public Address getAddress() {
        return address;
    }
}
