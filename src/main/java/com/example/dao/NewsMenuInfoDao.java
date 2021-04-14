package com.example.dao;

import com.example.entity.NewsMenuInfo;
import com.example.vo.NewsMenuInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NewsMenuInfoDao extends Mapper<NewsMenuInfo> {
    List<NewsMenuInfoVo> findByNameAndId(@Param("name") String name, @Param("id") Long id, @Param("classifyId") Long classifyId);

    List<NewsMenuInfoVo> findByNameAndUser(@Param("name") String name, @Param("username") String username, @Param("level") Integer level);
}
