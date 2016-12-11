package ua.kiev.kvartirant.dao;

import ua.kiev.kvartirant.model.Apartment;

import java.util.List;

public interface ApartmentDAO {

    List<Apartment> list(String location, String type, String status, int minPrice, int maxPrice);

    Apartment getApartmentByID(long apartmentId);

    void add(Apartment apartment);

    byte[] getPhoto(long apartmentID, int serial);
}
