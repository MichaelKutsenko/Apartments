package ua.kiev.kvartirant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.kvartirant.model.Apartment;
import ua.kiev.kvartirant.model.Photo;
import ua.kiev.kvartirant.model.enums.FlatType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ApartmentDAOImpl implements ApartmentDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Apartment> list(String location, String type, String status, int minPrice, int maxPrice) {
        boolean isMaxPrise = maxPrice == 0 || maxPrice == Integer.MAX_VALUE;
        Query query = null;

        if ("ALL".equals(location)) {
            switch (type) {
                case "FLAT_ALL":
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status", Apartment.class);

                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price between :minPrice and :maxPrice", Apartment.class);

                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);

                    }
                    break;

                case "ROOM_ALL":
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status", Apartment.class);

                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price between :minPrice and :maxPrice", Apartment.class);

                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);

                    }
                    break;

                default:
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.type = :apType and  a.status = :status", Apartment.class);

                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.type = :apType and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.type = :apType and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                        "a.type = :apType and  a.status = :status and a.price between :minPrice and :maxPrice",
                                Apartment.class);

                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);

                    }
                    break;
            }
        }
//        if district is selected
        else {
            switch (type) {
                case "FLAT_ALL":
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :studio or a.type = :twoRoom or a.type = :threeRoom  or a.type = :maxRoom) " +
                                "and  a.status = :status and a.price between :minPrice and :maxPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("studio", FlatType.STUDIO.name());
                        query.setParameter("twoRoom", FlatType.TWO_ROOM.name());
                        query.setParameter("threeRoom", FlatType.THREE_ROOM.name());
                        query.setParameter("maxRoom", FlatType.MAX_ROOM.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);

                    }
                    break;

                case "all_rooms":
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "(a.type = :roomWithoutOwner or a.type = :roomWithOwner) " +
                                "and  a.status = :status and a.price between :minPrice and :maxPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("roomWithoutOwner", FlatType.ROOM_WITHOUT_OWNER.name());
                        query.setParameter("roomWithOwner", FlatType.ROOM_WITH_OWNER.name());
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);

                    }
                    break;

                default:
                    if (minPrice == 0 && (isMaxPrise)) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and a.type = :apType and  a.status = :status", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                    } else if (minPrice == 0) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "a.type = :apType and  a.status = :status and a.price <= :maxPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("maxPrice", maxPrice);
                    } else if (isMaxPrise) {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                "a.location = :location and " +
                                "a.type = :apType and  a.status = :status and a.price >= :minPrice", Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                    } else {
                        query = entityManager.createQuery("select a from Apartment a where " +
                                        "a.location = :location and " +
                                        "a.type = :apType and  a.status = :status and a.price between :minPrice and :maxPrice",
                                Apartment.class);

                        query.setParameter("location", location);
                        query.setParameter("apType", type);
                        query.setParameter("status", status);
                        query.setParameter("minPrice", minPrice);
                        query.setParameter("maxPrice", maxPrice);
                    }
                    break;
            }
        }

        return (List<Apartment>) query.getResultList();
    }

    @Override
    public Apartment getApartmentByID(long apartmentId) {
        try {
            Apartment apartment = entityManager.find(Apartment.class, apartmentId);
            return apartment;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(Apartment apartment) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(apartment);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public byte[] getPhoto(long apartmentID, int serial) {
        try {
            Query query = entityManager.createQuery("select p from Photo p where p.apartment.id = :apartment and " +
                    "p.serial = :serial", Photo.class);
            query.setParameter("apartment", apartmentID);
            query.setParameter("serial", serial);

            Photo photo = (Photo) query.getSingleResult();

            return photo.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
