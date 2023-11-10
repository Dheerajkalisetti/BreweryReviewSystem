package com.webapp.BRS.controller;

import com.webapp.BRS.dto.RatingDto;
import com.webapp.BRS.service.RatingService;
import org.hibernate.HibernateException;
import org.hibernate.dialect.MySQLDialect;
import org.json.*;
import com.webapp.BRS.dto.ReviewDto;
import com.webapp.BRS.entity.Review;
import com.webapp.BRS.service.ReviewService;
import jakarta.validation.Valid;
import com.webapp.BRS.dto.UserDto;
import com.webapp.BRS.entity.User;
import com.webapp.BRS.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    private ReviewService reviewService;

    private RatingService ratingService;

    public AuthController(UserService userService, ReviewService reviewService, RatingService ratingService) {
        this.userService = userService;
        this.reviewService=reviewService;
        this.ratingService=ratingService;
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/index/review")
    public String review(@Valid @ModelAttribute("review") ReviewDto reviewDto, BindingResult result,
                         Model model) {
        if(result.hasErrors()){
            return "redirect:/index?failedReview";
        }

            reviewService.saveReview(reviewDto);
            return "redirect:/index?reviewed";
    }


    @GetMapping("/allBreweries")
    public String allBreweries(Model model)
    {
        String url = "https://api.openbrewerydb.org/v1/breweries?per_page=5";
        RestTemplate restTemplate=new RestTemplate();
        Object[] breweries=restTemplate.getForObject(url, Object[].class);
        List<RatingDto> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);
        model.addAttribute("breweries",Arrays.asList(breweries));
        return "breweries";
    }

    @RequestMapping("/index/findById")
    public String findById(@RequestParam String id, Model model)
    {
        ReviewDto review = new ReviewDto();
        model.addAttribute("review", review);
        String url = "https://api.openbrewerydb.org/v1/breweries/"+id;
        RestTemplate restTemplate=new RestTemplate();
        Object breweries=restTemplate.getForObject(url, Object.class);
        List<RatingDto> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);
        model.addAttribute("breweries",Arrays.asList(breweries));
        return "brewery";
    }

    @RequestMapping("/index/findByCity")
    public String findByCity(@RequestParam String city, Model model)
    {
        String url = "https://api.openbrewerydb.org/v1/breweries?by_city="+city;
        RestTemplate restTemplate=new RestTemplate();
        Object[] breweries=restTemplate.getForObject(url, Object[].class);
        List<RatingDto> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);
        model.addAttribute("breweries",Arrays.asList(breweries));
        return "breweries";
    }

    @RequestMapping("/index/findByName")
    public String findByName(@RequestParam String name, Model model)
    {
        String url = "https://api.openbrewerydb.org/v1/breweries?by_name="+name;
        RestTemplate restTemplate=new RestTemplate();
        Object[] breweries=restTemplate.getForObject(url, Object[].class);
        List<RatingDto> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);
        model.addAttribute("breweries",Arrays.asList(breweries));
        return "breweries";
    }

    @RequestMapping("/index/findByType")
    public String findByType(@RequestParam String type, Model model)
    {
        String url = "https://api.openbrewerydb.org/v1/breweries?by_type="+type;
        RestTemplate restTemplate=new RestTemplate();
        Object[] breweries=restTemplate.getForObject(url, Object[].class);
        List<RatingDto> ratings = ratingService.findAllRatings();
        model.addAttribute("ratings", ratings);
        model.addAttribute("breweries",Arrays.asList(breweries));
        return "breweries";
    }
}