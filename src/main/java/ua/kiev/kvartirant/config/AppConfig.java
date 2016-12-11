package ua.kiev.kvartirant.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ua.kiev.kvartirant.dao.*;
import ua.kiev.kvartirant.model.enums.FlatLocation;
import ua.kiev.kvartirant.model.enums.FlatStatus;
import ua.kiev.kvartirant.model.enums.FlatType;
import ua.kiev.kvartirant.service.UserDetailsServiceImpl;
import ua.kiev.kvartirant.service.UserService;
import ua.kiev.kvartirant.service.UserServiceImpl;
import ua.kiev.kvartirant.validator.UserValidator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedHashMap;

@Configuration
@EnableWebMvc
@ComponentScan("ua.kiev.kvartirant")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public EntityManager entityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FlatsJPA");
        return emf.createEntityManager();
    }

    @Bean
    public ApartmentDAO apartmentDAO(){
        return new ApartmentDAOImpl();
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl();
    }

    @Bean
    public RoleDAO roleDAO(){
        return new RoleDAOImpl();
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public UserValidator userValidator(){
        return new UserValidator();
    }

    @Bean
    public LinkedHashMap<String, String> mapLocations(){
        LinkedHashMap<String, String> mapLocations = new LinkedHashMap<>();
        mapLocations.put(FlatLocation.ALL.name(), "Любой район");
        mapLocations.put(FlatLocation.HOLOSIIVSKYI.name(), "Голосеевский р-н");
        mapLocations.put(FlatLocation.DARNYTSKIY.name(), "Дарницкий р-н");
        mapLocations.put(FlatLocation.DESNYANSKIY.name(), "Деснянский р-н");
        mapLocations.put(FlatLocation.DNIPRO.name(), "Днепровский р-н");
        mapLocations.put(FlatLocation.OBOLONSKIY.name(), "Оболонский р-н");
        mapLocations.put(FlatLocation.PECHERSKIY.name(), "Печерский р-н");
        mapLocations.put(FlatLocation.PODOLSKY.name(), "Подольский р-н");
        mapLocations.put(FlatLocation.SVYATOSHINSKIY.name(),"Святошинский р-н" );
        mapLocations.put(FlatLocation.SOLOMENSKIY.name(), "Соломенский р-н");
        mapLocations.put(FlatLocation.SHEVCHENKO.name(), "Шевченковский р-н");

        return mapLocations;
    }

    @Bean
    public LinkedHashMap<String, String> mapAddLocations(){
        LinkedHashMap<String, String> mapAddLocations = new LinkedHashMap<>();
        mapAddLocations.put(FlatLocation.HOLOSIIVSKYI.name(), "Голосеевский р-н");
        mapAddLocations.put(FlatLocation.DARNYTSKIY.name(), "Дарницкий р-н");
        mapAddLocations.put(FlatLocation.DESNYANSKIY.name(), "Деснянский р-н");
        mapAddLocations.put(FlatLocation.DNIPRO.name(), "Днепровский р-н");
        mapAddLocations.put(FlatLocation.OBOLONSKIY.name(), "Оболонский р-н");
        mapAddLocations.put(FlatLocation.PECHERSKIY.name(), "Печерский р-н");
        mapAddLocations.put(FlatLocation.PODOLSKY.name(), "Подольский р-н");
        mapAddLocations.put(FlatLocation.SVYATOSHINSKIY.name(),"Святошинский р-н" );
        mapAddLocations.put(FlatLocation.SOLOMENSKIY.name(), "Соломенский р-н");
        mapAddLocations.put(FlatLocation.SHEVCHENKO.name(), "Шевченковский р-н");

        return mapAddLocations;
    }

    @Bean
    public LinkedHashMap<String, String> mapStatues(){
        LinkedHashMap<String, String> mapStatues = new LinkedHashMap<>();
        mapStatues.put(FlatStatus.LONG_TERM.name(), "Долговременная аренда");
        mapStatues.put(FlatStatus.SHORT_TERM.name(), "Суточная аренда");

        return mapStatues;
    }

    @Bean
    public LinkedHashMap<String, String> mapTypes(){
        LinkedHashMap<String, String> mapTypes = new LinkedHashMap<>();
        mapTypes.put(FlatType.FLAT_ALL.name(), "Квартира (любая)");
        mapTypes.put(FlatType.STUDIO.name(), "1к квартира");
        mapTypes.put(FlatType.TWO_ROOM.name(), "2к квартира");
        mapTypes.put(FlatType.THREE_ROOM.name(), "3к квартира");
        mapTypes.put(FlatType.MAX_ROOM.name(), ">3к квартира");
        mapTypes.put(FlatType.ROOM_ALL.name(), "Комната (любая)");
        mapTypes.put(FlatType.ROOM_WITHOUT_OWNER.name(), "Комната без хозяев");
        mapTypes.put(FlatType.ROOM_WITH_OWNER.name(), "Комната с хозяевами");
        mapTypes.put(FlatType.BED_PLACE.name(), "Койкоместо");

        return mapTypes;
    }

    @Bean
    public LinkedHashMap<String, String> mapAddTypes(){
        LinkedHashMap<String, String> mapAddTypes = new LinkedHashMap<>();
        mapAddTypes.put(FlatType.STUDIO.name(), "1к квартира");
        mapAddTypes.put(FlatType.TWO_ROOM.name(), "2к квартира");
        mapAddTypes.put(FlatType.THREE_ROOM.name(), "3к квартира");
        mapAddTypes.put(FlatType.MAX_ROOM.name(), ">3к квартира");
        mapAddTypes.put(FlatType.ROOM_WITHOUT_OWNER.name(), "Комната без хозяев");
        mapAddTypes.put(FlatType.ROOM_WITH_OWNER.name(), "Комната с хозяевами");
        mapAddTypes.put(FlatType.BED_PLACE.name(), "Койкоместо");

        return mapAddTypes;
    }

    @Bean
    public LinkedHashMap<Integer, String> mapMinPrices(){
        LinkedHashMap<Integer, String> mapMinPrices = new LinkedHashMap<>();
        mapMinPrices.put(0, "Мин цена");
        mapMinPrices.put(50, "от 50 грн");
        mapMinPrices.put(500, "от 500 грн");
        mapMinPrices.put(1000, "от 1 000 грн");
        mapMinPrices.put(3000, "от 3 000 грн");
        mapMinPrices.put(5000, "от 5 000 грн");
        mapMinPrices.put(10000, "от 10 000 грн");
        mapMinPrices.put(15000, "от 15 000 грн");

        return mapMinPrices;
    }

    @Bean
    public LinkedHashMap<Integer, String> mapMaxPrices(){
        LinkedHashMap<Integer, String> mapMaxPrices = new LinkedHashMap<>();
        mapMaxPrices.put(0, "Макс цена");
        mapMaxPrices.put(500, "до 500 грн");
        mapMaxPrices.put(1000, "до 1 000 грн");
        mapMaxPrices.put(3000, "до 3 000 грн");
        mapMaxPrices.put(5000, "до 5 000 грн");
        mapMaxPrices.put(10000, "до 10 000 грн");
        mapMaxPrices.put(20000, "до 20 000 грн");
        mapMaxPrices.put(Integer.MAX_VALUE, "Любая");

        return mapMaxPrices;
    }

    @Bean
    public UrlBasedViewResolver setupUrlBasedViewResolver(){
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setPrefix("/resources/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(1);

        return viewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/classes/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
