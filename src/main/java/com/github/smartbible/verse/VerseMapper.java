package com.github.smartbible.verse;

import java.util.ArrayList;
import java.util.List;

public class VerseMapper {
    public List<Verse> map(ScriptDto script) {
        ArrayList<Verse> verses = new ArrayList<>();
        for (int i = 0; i < script.getChapters().size(); i++) {
            List<String> chapter = script.getChapters().get(i);
            for (int j = 0; j < chapter.size(); j++) {
                String verse = chapter.get(0);
                Verse verseObject = new Verse();
                verseObject.setText(verse);
                verseObject.setAddress(new Address(script.getName(), i + 1, j + 1));
                verses.add(verseObject);
            }
        }
        return verses;
    }
}
