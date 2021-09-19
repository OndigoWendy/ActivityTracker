package models;

import java.util.Objects;

public class Ranger {
    private String name;
    private int id;
    private String ranger;
    private String zone;

//    public Ranger(String name ,String ranger, String zone)
//    {
//        this.name = name;
//        this.name = ranger;
//        this.name = zone;
//    }
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

    public String getRanger() {
        return ranger;
    }

    public  String getZone() {
        return zone;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRanger(String ranger) {
        this.ranger = ranger;
    }
    public void setZone(String zone) {
        this.zone = zone;
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
                Objects.equals(name, ranger.name)
//                Objects.equals(ranger, ranger.ranger)&&
//                Objects.equals(zone, ranger.zone)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
