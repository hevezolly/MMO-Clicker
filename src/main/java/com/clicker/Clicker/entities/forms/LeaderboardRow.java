package com.clicker.Clicker.entities.forms;

import java.util.Objects;

public class LeaderboardRow implements Comparable<LeaderboardRow>{
    private String name;
    private Long score;

    public LeaderboardRow(String name, Long score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderboardRow that = (LeaderboardRow) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(LeaderboardRow o) {
        var i = -score.compareTo(o.score);
        return (i == 0) ? name.compareTo(o.name) : i;
    }
}
