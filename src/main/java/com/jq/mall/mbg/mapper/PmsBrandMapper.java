package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.PmsBrand;
import com.jq.mall.mbg.model.PmsBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PmsBrandMapper {
    @SelectProvider(type=PmsBrandSqlProvider.class, method="countByExample")
    long countByExample(PmsBrandExample example);

    @DeleteProvider(type=PmsBrandSqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsBrandExample example);

    @Delete({
        "delete from pms_brand",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_brand (name, first_letter, ",
        "sort, factory_status, ",
        "show_status, product_count, ",
        "product_comment_count, logo, ",
        "big_pic, brand_story)",
        "values (#{name,jdbcType=VARCHAR}, #{firstLetter,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=INTEGER}, #{factoryStatus,jdbcType=INTEGER}, ",
        "#{showStatus,jdbcType=INTEGER}, #{productCount,jdbcType=INTEGER}, ",
        "#{productCommentCount,jdbcType=INTEGER}, #{logo,jdbcType=VARCHAR}, ",
        "#{bigPic,jdbcType=VARCHAR}, #{brandStory,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsBrand record);

    @InsertProvider(type=PmsBrandSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsBrand record);

    @SelectProvider(type=PmsBrandSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="factory_status", property="factoryStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_comment_count", property="productCommentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="big_pic", property="bigPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="brand_story", property="brandStory", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsBrand> selectByExampleWithBLOBs(PmsBrandExample example);

    @SelectProvider(type=PmsBrandSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="factory_status", property="factoryStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_comment_count", property="productCommentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="big_pic", property="bigPic", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsBrand> selectByExample(PmsBrandExample example);

    @Select({
        "select",
        "id, name, first_letter, sort, factory_status, show_status, product_count, product_comment_count, ",
        "logo, big_pic, brand_story",
        "from pms_brand",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="factory_status", property="factoryStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="show_status", property="showStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="product_count", property="productCount", jdbcType=JdbcType.INTEGER),
        @Result(column="product_comment_count", property="productCommentCount", jdbcType=JdbcType.INTEGER),
        @Result(column="logo", property="logo", jdbcType=JdbcType.VARCHAR),
        @Result(column="big_pic", property="bigPic", jdbcType=JdbcType.VARCHAR),
        @Result(column="brand_story", property="brandStory", jdbcType=JdbcType.LONGVARCHAR)
    })
    PmsBrand selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsBrandSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);

    @UpdateProvider(type=PmsBrandSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);

    @UpdateProvider(type=PmsBrandSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsBrand record, @Param("example") PmsBrandExample example);

    @UpdateProvider(type=PmsBrandSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsBrand record);

    @Update({
        "update pms_brand",
        "set name = #{name,jdbcType=VARCHAR},",
          "first_letter = #{firstLetter,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "factory_status = #{factoryStatus,jdbcType=INTEGER},",
          "show_status = #{showStatus,jdbcType=INTEGER},",
          "product_count = #{productCount,jdbcType=INTEGER},",
          "product_comment_count = #{productCommentCount,jdbcType=INTEGER},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "big_pic = #{bigPic,jdbcType=VARCHAR},",
          "brand_story = #{brandStory,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(PmsBrand record);

    @Update({
        "update pms_brand",
        "set name = #{name,jdbcType=VARCHAR},",
          "first_letter = #{firstLetter,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "factory_status = #{factoryStatus,jdbcType=INTEGER},",
          "show_status = #{showStatus,jdbcType=INTEGER},",
          "product_count = #{productCount,jdbcType=INTEGER},",
          "product_comment_count = #{productCommentCount,jdbcType=INTEGER},",
          "logo = #{logo,jdbcType=VARCHAR},",
          "big_pic = #{bigPic,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsBrand record);
}