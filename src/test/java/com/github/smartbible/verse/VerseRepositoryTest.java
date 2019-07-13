package com.github.smartbible.verse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerseRepositoryTest {

    private VerseRepository verseRepository;

    @Before
    public void setUp() {
        verseRepository = new VerseRepository();
    }

    @Test
    public void canSaveAndGetByAddressVerse() {
        Verse verse = new Verse();
        verse.setText("Some text");
        Address address = new Address("Scripture", 1, 1);
        verse.setAddress(address);
        verseRepository.save(verse);

        assertThat("Some text", equalTo(verseRepository.getByAddress(address).get().getText()));
    }

    @Test
    public void canMapBibleToVerses() {
        ArrayList<ScriptDto> bible = new BibleReader().read("bible.json");
        VerseMapper verseMapper = new VerseMapper();
        List<Verse> verses = bible.stream()
                .flatMap(script -> verseMapper.map(script).stream())
                .collect(Collectors.toList());
        assertThat(verses.get(0).getText(), equalTo("In the beginning God created the heaven and the earth."));
    }

    @Test
    public void canSearch() {
        Verse verse1 = new Verse();
        verse1.setText("verse1");

        Set<Verse> verses = verseRepository.search("Test verse");
//        assertThat(verses, co);
    }

}
