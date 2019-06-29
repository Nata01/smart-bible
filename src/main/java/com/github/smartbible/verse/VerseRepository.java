package com.github.smartbible.verse;

import java.util.*;
import java.util.stream.Collectors;

public class VerseRepository {

    private final Set<Verse> verses;

    public VerseRepository(Set<Verse> verses) {
        this.verses = Collections.unmodifiableSet(verses);
    }

    public Set<Verse> search(String query) {
        return verses.stream().filter(v -> v.getText().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toSet());
    }

    public Optional<Verse> getByAddress(Address address) {
        return verses.stream().filter(v -> v.getAddress().equals(address)).findAny();
    }
}
