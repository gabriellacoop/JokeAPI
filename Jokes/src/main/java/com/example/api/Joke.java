package com.example.api;

public class Joke {
    private String setup;
    private String punchline;
    private String id;

    public Joke(String setup, String punchline, String id) {
        this.setup = setup;
        this.punchline = punchline;
        this.id = id;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return setup;
    }
}
