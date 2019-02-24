package com.iqx.thymeleaf.controller;
import com.iqx.thymeleaf.domain.User;
import com.iqx.thymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * User Controller
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get the user list from user repo
     * @return
     */
    private List<User> getUserlist() {
        return userRepository.listUser();
    }

    /**
     * Get all the Users
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userList",userRepository.listUsers());
        model.addAttribute("title","User Management");
        return new ModelAndView("list","userModel",model);
    }

    /**
     * Get User By ID
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {

        User user = userRepository.getUserById(id);
        model.addAttribute("user",user);
        model.addAttribute("title","Find a User");
        return new ModelAndView("view","userModel",model);

    }

    /**
     * Return a form to add new user
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("title","Create a new User");
        System.out.println("form");
        return new ModelAndView("form","userModel",model);

    }

    /**
     * Add or Create a new User, if succeed return to list.html
     */
    @PostMapping
    public ModelAndView saveOrUpdateUser(User user,Model model) {
        user = userRepository.saveOrUpdateUser(user);
        return new ModelAndView("form","userModel",model);
    }

    @GetMapping(value="delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model) {

        userRepository.deleteUser(id);

        model.addAttribute("userList",getUserlist());

        return new ModelAndView("list","userModel",model);

    }

    /**
     * Modify a user info
     * @param id
     * @return
     */
    @GetMapping(value = "modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model) {
        User user = userRepository.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("title", "修改用户");
        return new ModelAndView("form", "userModel", model);
    }

}
