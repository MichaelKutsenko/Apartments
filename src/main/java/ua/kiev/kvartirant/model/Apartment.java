package ua.kiev.kvartirant.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Apartments")
public class Apartment {

    @Id
    @GeneratedValue
    private long id;


    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;
    private String type;
    private int floor;
    private String location;
    private String street;
    private int price;
    private String phone;
    private String name;
    private String description;
    private Date date;

    public Apartment() {
        this.date = new Date();
    }

    public Apartment(String status, String type, int floor, String location, String street,
                     int price, String phone, String name, String description) {
        this.status = status;
        this.type = type;
        this.floor = floor;
        this.location = location;
        this.street = street;
        this.price = price;
        this.phone = phone;
        this.name = name;
        this.description = description;
        this.date = new Date();
    }

    public void addPhoto(Photo photo){
        photos.add(photo);

        if (photo.getApartment() != this){
            photo.setApartment(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
