package com.nowcoder.controller;

import com.nowcoder.model.News;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.NewsService;
import com.nowcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zejyej
 * description:
 * date: 2018/4/4
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    private List<ViewObject> getNews(
            int userId,
            int offset,
            int limit
    ) {
        List<News> newsList = newsService.getLatestNewsByLimit(userId,offset,limit);
        List<ViewObject> vos = new ArrayList<>();
        for (News news: newsList) {
            ViewObject vo = new ViewObject();
            vo.set("news",news);
            vo.set("user",userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET})
    public String index(Model model) {
        model.addAttribute("vos",getNews(0,0,10));
        return "home";
    }

    @RequestMapping(path = {"/user/{userId}/"},method = {RequestMethod.GET})
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        model.addAttribute("vos",getNews(userId,0,10));
        return "home";
    }

}
