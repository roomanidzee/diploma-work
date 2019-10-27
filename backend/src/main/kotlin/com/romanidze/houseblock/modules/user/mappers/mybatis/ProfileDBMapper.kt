package com.romanidze.houseblock.modules.user.mappers.mybatis

import com.romanidze.houseblock.modules.user.domain.Profile

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.Result

@Mapper
interface ProfileDBMapper {

    @Insert("""
        INSERT INTO profiles(user_id, surname, name, patronymic, email, phone, created_time)
        VALUES(#{userID}, #{surname}, #{name}, #{patronymic}, #{email}, #{phone}, #{createdTime})
        """
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun save(profile: Profile)

    @Select("SELECT * FROM profiles")
    @Results(
            Result(column = "user_id", property = "userID"),
            Result(column = "created_time", property = "createdTime")
    )
    fun findAll(): List<Profile>

    @Update("""
        UPDATE profiles SET user_id = #{userID}, surname = #{surname}, name = #{name},
                            patronymic = #{patronymic}, email = #{email}, phone = #{phone}
        WHERE id = #{id}
        """
    )
    fun update(profile: Profile)

    @Delete("DELETE FROM profiles WHERE id = #{id}")
    fun delete(@Param("id") id: Long)

    @Select("SELECT * FROM profiles WHERE user_id = #{user_id}")
    @Results(
            Result(column = "user_id", property = "userID"),
            Result(column = "created_time", property = "createdTime")
    )
    fun findByUserID(@Param("user_id") userID: Long): Profile

}