package pojo;

//@JsonIgnoreProperties(value = "id",allowSetters = true)
public class Spartan {
    // a class for Plain Old Java Object (POJO)
// to represent data

        private int id;
        private String name;
        private String gender;
        private long phone;

        public Spartan(){

        }


    public Spartan(int id) {
        this.id = id;
    }

    public Spartan(String name, String gender, long phone) {
;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}