package com.github.smartbible.verse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.ArrayList;

public class BibleReader {

    public ArrayList<ScriptDto> read(String fileName) {
        var founderListType = new TypeToken<ArrayList<ScriptDto>>(){}.getType();
        return new Gson().fromJson(new InputStreamReader(BibleReader.class.getClassLoader().getResourceAsStream(fileName)), founderListType);
    }
}
