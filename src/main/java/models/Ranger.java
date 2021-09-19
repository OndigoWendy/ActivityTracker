package models;

import java.util.Objects;

public class Ranger {
    private String name;
    private int id;


    public Ranger(String name )
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return id == ranger.id &&
                Objects.equals(name, ranger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
