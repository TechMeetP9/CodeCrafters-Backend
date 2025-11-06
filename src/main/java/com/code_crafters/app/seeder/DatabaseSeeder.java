package com.code_crafters.app.seeder;

import com.code_crafters.app.entity.*;
import com.code_crafters.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private LocationRepository locationRepository;
    private EventRepository eventRepository;
    private BCryptPasswordEncoder bcrypt;
    
    public DatabaseSeeder(UserRepository userRepository,
                         CategoryRepository categoryRepository,
                         LocationRepository locationRepository,
                         EventRepository eventRepository,
                         BCryptPasswordEncoder bcrypt) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
        this.bcrypt = bcrypt;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        // Create users
        if (userRepository.count() == 0) {
            User user1 = User.builder()
                .name("John Smith")
                .username("johnsmith")
                .email("john@example.com")
                .password(bcrypt.encode("password123"))
                .profileImageUrl("https://i.pravatar.cc/150?img=12")
                .build();
            
            User user2 = User.builder()
                .name("Sarah Johnson")
                .username("sarahjohnson")
                .email("sarah@example.com")
                .password(bcrypt.encode("password123"))
                .profileImageUrl("https://i.pravatar.cc/150?img=5")
                .build();
            
            User user3 = User.builder()
                .name("Mike Davis")
                .username("mikedavis")
                .email("mike@example.com")
                .password(bcrypt.encode("password123"))
                .profileImageUrl("https://i.pravatar.cc/150?img=33")
                .build();
            
            userRepository.saveAll(List.of(user1, user2, user3));
            System.out.println("✅ Users created");
        }
        
        // Create categories
        if (categoryRepository.count() == 0) {
            Category inPerson = Category.builder()
                .name("In-Person")
                .build();
            
            Category online = Category.builder()
                .name("Online")
                .build();
            
            categoryRepository.saveAll(List.of(inPerson, online));
            System.out.println("✅ Categories created");
        }
        
        // Create locations
        if (locationRepository.count() == 0) {
            Location location1 = Location.builder()
                .name("Barcelona Convention Center")
                .address("Plaça de Willy Brandt, 11-14, 08019 Barcelona")
                .latitude(new BigDecimal("41.3851"))
                .longitude(new BigDecimal("2.1734"))
                .build();
            
            Location location2 = Location.builder()
                .name("Madrid National Auditorium")
                .address("Príncipe de Vergara, 146, 28002 Madrid")
                .latitude(new BigDecimal("40.4378"))
                .longitude(new BigDecimal("-3.6795"))
                .build();
            
            Location location3 = Location.builder()
                .name("Online - Zoom")
                .address("Virtual platform")
                .latitude(null)
                .longitude(null)
                .build();
            
            Location location4 = Location.builder()
                .name("Online - Google Meet")
                .address("Virtual platform")
                .latitude(null)
                .longitude(null)
                .build();
            
            locationRepository.saveAll(List.of(location1, location2, location3, location4));
            System.out.println("✅ Locations created");
        }
        
        // Create events
        if (eventRepository.count() == 0) {
            User user1 = userRepository.findByUsername("johnsmith").get();
            User user2 = userRepository.findByUsername("sarahjohnson").get();
            User user3 = userRepository.findByUsername("mikedavis").get();
            
            Category inPerson = categoryRepository.findByName("In-Person").get();
            Category online = categoryRepository.findByName("Online").get();
            
            Location location1 = locationRepository.findAll().get(0);
            Location location2 = locationRepository.findAll().get(1);
            Location location3 = locationRepository.findAll().get(2);
            Location location4 = locationRepository.findAll().get(3);
            
            Event event1 = Event.builder()
                .title("Spring Boot Workshop")
                .description("Learn to build applications with Spring Boot from scratch. We'll cover basic concepts, security, JPA, and best practices.")
                .eventDate(LocalDate.now().plusDays(15))
                .eventTime(LocalTime.of(10, 0))
                .duration("4 hours")
                .capacity(50)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1517694712202-14dd9538aa97")
                .category(inPerson)
                .location(location1)
                .creator(user1)
                .build();
            
            Event event2 = Event.builder()
                .title("Introduction to React and Hooks")
                .description("Discover the power of React and its modern hooks to create dynamic user interfaces.")
                .eventDate(LocalDate.now().plusDays(7))
                .eventTime(LocalTime.of(16, 30))
                .duration("3 hours")
                .capacity(100)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1633356122544-f134324a6cee")
                .category(online)
                .location(location3)
                .creator(user2)
                .build();
            
            Event event3 = Event.builder()
                .title("Java Developers Meetup")
                .description("Networking and talks about the latest trends in Java and the Spring ecosystem.")
                .eventDate(LocalDate.now().plusDays(20))
                .eventTime(LocalTime.of(18, 0))
                .duration("2.5 hours")
                .capacity(30)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1515879218367-8466d910aaa4")
                .category(inPerson)
                .location(location2)
                .creator(user1)
                .build();
            
            Event event4 = Event.builder()
                .title("Docker and Kubernetes for Beginners")
                .description("Learn to containerize your applications and deploy them on Kubernetes in a practical way.")
                .eventDate(LocalDate.now().plusDays(10))
                .eventTime(LocalTime.of(19, 0))
                .duration("2 hours")
                .capacity(80)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1605745341112-85968b19335b")
                .category(online)
                .location(location4)
                .creator(user3)
                .build();
            
            Event event5 = Event.builder()
                .title("Microservices Architecture")
                .description("Design, implementation, and best practices for microservices-based architectures.")
                .eventDate(LocalDate.now().plusDays(25))
                .eventTime(LocalTime.of(10, 0))
                .duration("5 hours")
                .capacity(40)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1558494949-ef010cbdcc31")
                .category(inPerson)
                .location(location1)
                .creator(user2)
                .build();
            
            Event event6 = Event.builder()
                .title("Testing with JUnit 5 and Mockito")
                .description("Improve your code quality by learning advanced testing techniques in Java.")
                .eventDate(LocalDate.now().plusDays(12))
                .eventTime(LocalTime.of(17, 0))
                .duration("3 hours")
                .capacity(60)
                .currentAttendees(0)
                .imageUrl("https://images.unsplash.com/photo-1516116216624-53e697fedbea")
                .category(online)
                .location(location3)
                .creator(user3)
                .build();
            
            eventRepository.saveAll(List.of(event1, event2, event3, event4, event5, event6));
            System.out.println("✅ Events created");
        }
        
        System.out.println("\n Database initialized successfully");
        System.out.println("📧 Test users:");
        System.out.println("   - johnsmith / password123");
        System.out.println("   - sarahjohnson / password123");
        System.out.println("   - mikedavis / password123");
    }
}