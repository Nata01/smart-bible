package com.github.smartbible.verse;

import java.util.*;
import java.util.stream.Collectors;

public class VerseRepository {

    private final Set<Verse> verses = new HashSet<>();

    public void save(Verse verse) {
        verses.add(verse);
    }

    public Set<Verse> search(String query) {
        return verses.stream().filter(v -> v.getText().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toSet());
    }

    public Optional<Verse> getByAddress(Address address) {
        return verses.stream().filter(v -> v.getAddress().equals(address)).findAny();
    }
}
