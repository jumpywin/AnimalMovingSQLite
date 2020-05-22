package JDBC.model;

import java.util.Objects;

public class Grade {
    int id;
    int userid;
    int difficulty;
    int completion_time;

    public Grade() {
    }

    public Grade(int userid, int difficulty, int completion_time) {
        this.userid = userid;
        this.difficulty = difficulty;
        this.completion_time = completion_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getCompletion_time() {
        return completion_time;
    }

    public void setCompletion_time(int completion_time) {
        this.completion_time = completion_time;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", userid=" + userid +
                ", difficulty=" + difficulty +
                ", completion_time=" + completion_time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id == grade.id &&
                userid == grade.userid &&
                difficulty == grade.difficulty &&
                completion_time == grade.completion_time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userid, difficulty, completion_time);
    }
}
