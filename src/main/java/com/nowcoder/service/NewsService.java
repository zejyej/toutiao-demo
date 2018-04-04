package com.nowcoder.service;


import com.nowcoder.dao.NewsDao;
import com.nowcoder.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    NewsDao newsDao;

    public List<News> getLatestNewsByLimit(
            int userId,
            int offset,
            int limit
    ) {
        return newsDao.selectByUserIdAndOffset(userId,offset,limit);
    }
}
