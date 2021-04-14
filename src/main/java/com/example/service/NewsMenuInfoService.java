package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import com.example.dao.CollectInfoDao;
import com.example.dao.NewsMenuInfoDao;
import com.example.dao.PraiseInfoDao;
import com.example.entity.NewsMenuInfo;
import com.example.vo.NewsMenuInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsMenuInfoService {

    @Resource
    private NewsMenuInfoDao newsMenuInfoDao;
    @Resource
    private PraiseInfoDao praiseInfoDao;
    @Resource
    private CollectInfoDao collectInfoDao;

    public NewsMenuInfo add(NewsMenuInfo info) {
        newsMenuInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        newsMenuInfoDao.deleteByPrimaryKey(id);
    }

    public void update(NewsMenuInfo info) {
        newsMenuInfoDao.updateByPrimaryKeySelective(info);
    }

//    public NewsMenuInfoVo findById(Long id) {
//        List<NewsMenuInfoVo> list = newsMenuInfoDao.findByNameAndId(null, id, null);
//        if (!CollectionUtil.isEmpty(list)) {
//            NewsMenuInfoVo foodsMenuInfoVo = list.get(0);
//            Integer count = praiseInfoDao.count(null, id);
//            NewsMenuInfoVo.setPraiseCount(count);
//
//            Integer collectCount = collectInfoDao.count(null, id);
//            foodsMenuInfoVo.setCollectCount(collectCount);
//            return foodsMenuInfoVo;
//        }
//        return new NewsMenuInfoVo();
//    }

    public List<NewsMenuInfoVo> findAll() {
        return newsMenuInfoDao.findByNameAndId("all", null, null);
    }

    public PageInfo<NewsMenuInfoVo> findPage(String name, Long classifyId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NewsMenuInfoVo> info = newsMenuInfoDao.findByNameAndId(name, null, classifyId);
        for (NewsMenuInfoVo foodsMenuInfoVo : info) {
            Long id = foodsMenuInfoVo.getId();
            Integer count = praiseInfoDao.count(null, id);
            foodsMenuInfoVo.setPraiseCount(count);

            Integer collectCount = collectInfoDao.count(null, id);
            foodsMenuInfoVo.setCollectCount(collectCount);
        }
        // 按点赞数排序
        info = info.stream().sorted(Comparator.comparing(NewsMenuInfoVo::getPraiseCount).reversed()).collect(Collectors.toList());
        return PageInfo.of(info);
    }

    public PageInfo<NewsMenuInfoVo> findPageByUser(String name, String username, Integer level, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<NewsMenuInfoVo> info = newsMenuInfoDao.findByNameAndUser(name, username, level);
        return PageInfo.of(info);
    }
}
