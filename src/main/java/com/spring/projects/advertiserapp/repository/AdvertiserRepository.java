package com.spring.projects.advertiserapp.repository;

import com.spring.projects.advertiserapp.model.Advertiser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdvertiserRepository {

    @Select("select * from advertiser")
    public List<Advertiser> findAll();

    @Select("SELECT * FROM advertiser WHERE name = #{name}")
    public Advertiser findByName(String name);

    @Delete("DELETE FROM advertiser WHERE name = #{name}")
    public int deleteByName(String name);

    @Insert("INSERT INTO advertiser(name, contactName, creditLimit) VALUES (#{name}, #{contactName}, #{creditLimit})")
    public int insert(Advertiser advertiser);

    @Update("Update advertiser set name=#{name},contactName=#{contactName},creditLimit=#{creditLimit} WHERE name = #{name}")
    public int update(Advertiser advertiser);

}
