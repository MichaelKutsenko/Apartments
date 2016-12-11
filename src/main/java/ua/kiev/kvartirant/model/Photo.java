package ua.kiev.kvartirant.model;

import javax.persistence.*;

@Entity
@Table(name="Photos")
public class Photo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private String name;
    private int serial;
    private byte[] body;

    public Photo() {
    }

    public Photo(String name, byte[] body, int serial) {
        this.name = name;
        this.body = body;
        this.serial = serial;
    }

    public Photo(Apartment apartment, String name, byte[] body) {
        this.apartment = apartment;
        this.name = name;
        this.body = body;

        this.serial = apartment.getPhotos().size();
        apartment.addPhoto(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
