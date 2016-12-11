package ua.kiev.kvartirant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.kiev.kvartirant.dao.ApartmentDAO;
import ua.kiev.kvartirant.dao.UserDAO;
import ua.kiev.kvartirant.model.Apartment;
import ua.kiev.kvartirant.model.Photo;
import ua.kiev.kvartirant.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private ApartmentDAO apartmentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    @Qualifier("mapLocations")
    private LinkedHashMap<String, String> mapLocations;

    @Autowired
    @Qualifier("mapAddLocations")
    private LinkedHashMap<String, String> mapAddLocations;

    @Autowired
    @Qualifier("mapStatues")
    private LinkedHashMap<String, String> mapStatues;

    @Autowired
    @Qualifier("mapTypes")
    private LinkedHashMap<String, String> mapTypes;

    @Autowired
    @Qualifier("mapAddTypes")
    private LinkedHashMap<String, String> mapAddTypes;

    @Autowired
    @Qualifier("mapMinPrices")
    private LinkedHashMap<Integer, String> mapMinPrices;

    @Autowired
    @Qualifier("mapMaxPrices")
    private LinkedHashMap<Integer, String> mapMaxPrices;

    @RequestMapping("/")
    public ModelAndView loadHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("mapLocations", mapLocations);
        modelAndView.addObject("mapStatues", mapStatues);
        modelAndView.addObject("mapTypes", mapTypes);
        modelAndView.addObject("mapMinPrices", mapMinPrices);
        modelAndView.addObject("mapMaxPrices", mapMaxPrices);

        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value="location") String location,
                               @RequestParam(value="status") String status,
                               @RequestParam(value="min_price") int minPrice,
                               @RequestParam(value="max_price") int maxPrice,
                               @RequestParam(value="type") String type) {

        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("apartments", apartmentDAO.list(location, type, status, minPrice, maxPrice));
        modelAndView.addObject("mapLocations", mapLocations);
        modelAndView.addObject("mapStatues", mapStatues);
        modelAndView.addObject("mapTypes", mapTypes);
        modelAndView.addObject("mapMinPrices", mapMinPrices);
        modelAndView.addObject("mapMaxPrices", mapMaxPrices);

        return modelAndView;
    }

    @RequestMapping("/image/{apartment_id}")
    public void getPhoto(@PathVariable("apartment_id") long apartmentID,
                         @RequestParam("serial_number")int serial,
                         HttpServletResponse response){

        try {
            byte [] content = apartmentDAO.getPhoto(apartmentID, serial);

            if (content != null){
                response.setContentType("image/png");
                response.getOutputStream().write(content);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping("/search/apartment")
    public String getApp(@RequestParam(value = "id") long apartmentID, ModelMap model, HttpServletResponse response){
        Apartment apartment = apartmentDAO.getApartmentByID(apartmentID);
        List<Photo> list = apartment.getPhotos();

        System.out.println(apartment == null);

        if (apartment == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:/";
        }
        model.addAttribute("apartment", apartment);
        model.addAttribute("photos", list);
        model.addAttribute("mapLocations", mapAddLocations);

        return "apartment";
    }

    @RequestMapping(value = "/zdat_kvartiru")
    public ModelAndView loadAddPage() {
        ModelAndView modelAndView = new ModelAndView("add");
        modelAndView.addObject("mapLocations", mapAddLocations);
        modelAndView.addObject("mapStatues", mapStatues);
        modelAndView.addObject("mapTypes", mapAddTypes);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addApartment(@RequestParam(value = "status") String status,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "floor") int floor,
                               @RequestParam(value = "location") String location,
                               @RequestParam(value = "street") String street,
                               @RequestParam(value = "price") int price,
                               @RequestParam(value = "phone") String phone,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "description") String description,
                               @RequestParam(value="photo") MultipartFile[] photos,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        try {
            Apartment apartment = new Apartment(status, type, floor, location, street, price, phone, name, description);

            if (photos[0].getSize() > 0){
                for (MultipartFile file : photos){
                    apartment.addPhoto(new Photo(file.getOriginalFilename(), file.getBytes(),
                            apartment.getPhotos().size()));
                }
            }

            User user = getCurrentUser();
            if (user != null){
                user.addApartment(apartment);
            }

            apartmentDAO.add(apartment);
        }catch (IOException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ex.printStackTrace();

            return "redirect:/zdat_kvartiru";
        }

        return "redirect:/";
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object obj = auth.getPrincipal();

        String username;
        if (obj instanceof UserDetails) {
            username = ((UserDetails) obj).getUsername();
        } else {
            username = obj.toString();
        }

        User user = userDAO.findUserByUserName(username);

        return user;
    }
}
