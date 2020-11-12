package SimpleSBApps.bronzeage.model;

public class Hero {

    private final Integer id;
    private final String name;
    private String house;
    private String city;

    public Hero(Integer id, String name, String house, String city) {
        this.id = id;
        this.name = name;
        this.house = house;
        this.city = city;
    }

    public Hero(Integer id, String name) {
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
}
