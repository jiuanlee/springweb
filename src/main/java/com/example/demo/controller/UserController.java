package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello Springboot";
    }

    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    public String showUsers(Model model) {
        System.out.println("**********showUsers********");
        List<User> userList = new ArrayList<User>();
        userList = userService.selectAllUser();
        model.addAttribute("userList", userList); // 填充数据到model
        return "showUser";
    }

    /**
     * 增加�?��用户
     *
     * @param userName
     * @param sex
     * @param age
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap addUser(String userName, String sex, int age) {
        System.out.println("******addUser********");
        System.out.println(userName + sex + age);
        User user = new User();
        user.setSex(sex);
        user.setUserName(userName);
        user.setAge(age);
        userService.insertUser(user);
        ModelMap model = new ModelMap();
        model.addAttribute("result", "添加成功");
        return model;
    }

    /**
     * 通过userID删除用户
     *
     * @param userID
     */
    @RequestMapping(value = "/delUser/{userID}", method = RequestMethod.GET)
    public ModelAndView delUser(@PathVariable int userID) {
        System.out.println(userID);
        userService.deleteUser(userID);
        ModelAndView mv = new ModelAndView();
        List<User> userList = new ArrayList<User>();
        userList = userService.selectAllUser();
        mv.addObject("userList", userList); // 填充数据到model
        mv.setViewName("showUser");
        return mv;
    }

    /**
     * 查询用户
     *
     * @param model
     * @param keyWords
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String findUsers(Model model, String keyWords) {
        System.out.println(keyWords);
        List<User> userList = new ArrayList<User>();
        userList = userService.findUsers(keyWords);
        model.addAttribute("userList", userList); // 填充数据到model
        return "showUser";
    }

    /**
     * 更新用户信息
     *
     * @param userName
     * @param sex
     * @param age
     * @param id
     * @return
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(String userName, String sex, int age, int id) {
        System.out.println(userName + sex + age);
        User user = new User();
        user.setSex(sex);
        user.setUserName(userName);
        user.setAge(age);
        user.setId(id);
        userService.editUser(user);
        ModelAndView mv = new ModelAndView();
        List<User> userList = new ArrayList<User>();
        userList = userService.findUsers(String.valueOf(user.getId()));
        mv.addObject("userList", userList); // 填充数据到model
        mv.setViewName("redirect:/UserCRUD/showUser");
        return mv;
    }
}
