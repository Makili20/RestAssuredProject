package pojo;

import lombok.*;

@Getter//this annotation will add getters for all fields
@Setter//_----||-----------------setters for all the fields
@AllArgsConstructor//will add constructor including all fields
@NoArgsConstructor//will add no argument constructor
@ToString//wil add toString method for the class
public class Category {

    private String id;
    private String name;




}
