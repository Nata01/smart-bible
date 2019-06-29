package com.github.smartbible.verse;

import java.util.List;

public class ScriptDto {
    private String name;
    private List<List<String>> chapters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<String>> getChapters() {
        return chapters;
    }

    public void setChapters(List<List<String>> chapters) {
        this.chapters = chapters;
    }
}
