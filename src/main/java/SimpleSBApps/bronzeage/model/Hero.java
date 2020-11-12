package SimpleSBApps.bronzeage.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hero {

    private final Integer id;
    private final String name;
    private String house;
    private String city;

    public Hero(@JsonProperty("id") Integer id,
                @JsonProperty("name") String name,
                @JsonProperty("house") String house,
                @JsonProperty("city") String city) {
        this.id = id;
        this.name = name;
        this.house = house;
        this.city = city;
    }

    public Hero(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHouse() {
        return house;
    }

    public String getCity() {
        return city;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", house='" + house + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
