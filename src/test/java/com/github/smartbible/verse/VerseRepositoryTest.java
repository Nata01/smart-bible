package com.github.smartbible.verse;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerseRepositoryTest {

    private VerseRepository verseRepository;
    private static Set<Verse> verses;

    @BeforeClass
    public static void init() {
        ArrayList<ScriptDto> bible = new BibleReader().read("bible.json");
        VerseMapper verseMapper = new VerseMapper();
        verses = bible.stream()
                .flatMap(script -> verseMapper.map(script).stream())
                .collect(Collectors.toSet());
    }

    @Before
    public void setUp() {
        verseRepository = new VerseRepository(verses);
    }


    @Test
    public void canMapBibleToVerses() {
        Verse genesis11 = verseRepository.getByAddress(new Address("Genesis", 1, 1)).get();
        assertThat(genesis11.getText(), equalTo("In the beginning God created the heaven and the earth."));

        Verse genesis22 = verseRepository.getByAddress(new Address("Genesis", 2, 2)).get();
        assertThat(genesis22.getText(), equalTo("And on the seventh day God ended his work which he had made; and he rested on the seventh day from all his work which he had made."));
    }

    @Test
    public void canSearch() {
        Verse verse1 = new Verse();
        verse1.setText("verse1");

        Set<Verse> verses = verseRepository.search("Test verse");
//        assertThat(verses, co);
    }

}
