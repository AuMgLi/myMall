package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.PmsProduct;
import com.jq.mall.mbg.model.PmsProductExample;
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

public interface PmsProductMapper {
    @SelectProvider(type=PmsProductSqlProvider.class, method="countByExample")
    long countByExample(PmsProductExample example);

    @DeleteProvider(type=PmsProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(PmsProductExample example);

    @Delete({
        "delete from pms_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into pms_product (brand_id, product_category_id, ",
        "feight_template_id, product_attribute_category_id, ",
        "name, pic, product_sn, ",
        "delete_status, publish_status, ",
        "new_status, recommand_status, ",
        "verify_status, sort, ",
        "sale, price, promotion_price, ",
        "gift_growth, gift_point, ",
        "use_point_limit, sub_title, ",
        "original_price, stock, ",
        "low_stock, unit, ",
        "weight, preview_status, ",
        "service_ids, keywords, ",
        "note, album_pics, ",
        "detail_title, promotion_start_time, ",
        "promotion_end_time, promotion_per_limit, ",
        "promotion_type, brand_name, ",
        "product_category_name, description, ",
        "detail_desc, detail_html, ",
        "detail_mobile_html)",
        "values (#{brandId,jdbcType=BIGINT}, #{productCategoryId,jdbcType=BIGINT}, ",
        "#{feightTemplateId,jdbcType=BIGINT}, #{productAttributeCategoryId,jdbcType=BIGINT}, ",
        "#{name,jdbcType=VARCHAR}, #{pic,jdbcType=VARCHAR}, #{productSn,jdbcType=VARCHAR}, ",
        "#{deleteStatus,jdbcType=INTEGER}, #{publishStatus,jdbcType=INTEGER}, ",
        "#{newStatus,jdbcType=INTEGER}, #{recommandStatus,jdbcType=INTEGER}, ",
        "#{verifyStatus,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, ",
        "#{sale,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL}, #{promotionPrice,jdbcType=DECIMAL}, ",
        "#{giftGrowth,jdbcType=INTEGER}, #{giftPoint,jdbcType=INTEGER}, ",
        "#{usePointLimit,jdbcType=INTEGER}, #{subTitle,jdbcType=VARCHAR}, ",
        "#{originalPrice,jdbcType=DECIMAL}, #{stock,jdbcType=INTEGER}, ",
        "#{lowStock,jdbcType=INTEGER}, #{unit,jdbcType=VARCHAR}, ",
        "#{weight,jdbcType=DECIMAL}, #{previewStatus,jdbcType=INTEGER}, ",
        "#{serviceIds,jdbcType=VARCHAR}, #{keywords,jdbcType=VARCHAR}, ",
        "#{note,jdbcType=VARCHAR}, #{albumPics,jdbcType=VARCHAR}, ",
        "#{detailTitle,jdbcType=VARCHAR}, #{promotionStartTime,jdbcType=TIMESTAMP}, ",
        "#{promotionEndTime,jdbcType=TIMESTAMP}, #{promotionPerLimit,jdbcType=INTEGER}, ",
        "#{promotionType,jdbcType=INTEGER}, #{brandName,jdbcType=VARCHAR}, ",
        "#{productCategoryName,jdbcType=VARCHAR}, #{description,jdbcType=LONGVARCHAR}, ",
        "#{detailDesc,jdbcType=LONGVARCHAR}, #{detailHtml,jdbcType=LONGVARCHAR}, ",
        "#{detailMobileHtml,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PmsProduct record);

    @InsertProvider(type=PmsProductSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PmsProduct record);

    @SelectProvider(type=PmsProductSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="feight_template_id", property="feightTemplateId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_category_id", property="productAttributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="publish_status", property="publishStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="new_status", property="newStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="recommand_status", property="recommandStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="verify_status", property="verifyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="sale", property="sale", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="promotion_price", property="promotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_growth", property="giftGrowth", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_point", property="giftPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit", property="usePointLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_title", property="subTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="low_stock", property="lowStock", jdbcType=JdbcType.INTEGER),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
        @Result(column="weight", property="weight", jdbcType=JdbcType.DECIMAL),
        @Result(column="preview_status", property="previewStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="service_ids", property="serviceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="album_pics", property="albumPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_title", property="detailTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="promotion_start_time", property="promotionStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_end_time", property="promotionEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_per_limit", property="promotionPerLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_type", property="promotionType", jdbcType=JdbcType.INTEGER),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_desc", property="detailDesc", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_html", property="detailHtml", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_mobile_html", property="detailMobileHtml", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<PmsProduct> selectByExampleWithBLOBs(PmsProductExample example);

    @SelectProvider(type=PmsProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="feight_template_id", property="feightTemplateId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_category_id", property="productAttributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="publish_status", property="publishStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="new_status", property="newStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="recommand_status", property="recommandStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="verify_status", property="verifyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="sale", property="sale", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="promotion_price", property="promotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_growth", property="giftGrowth", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_point", property="giftPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit", property="usePointLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_title", property="subTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="low_stock", property="lowStock", jdbcType=JdbcType.INTEGER),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
        @Result(column="weight", property="weight", jdbcType=JdbcType.DECIMAL),
        @Result(column="preview_status", property="previewStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="service_ids", property="serviceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="album_pics", property="albumPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_title", property="detailTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="promotion_start_time", property="promotionStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_end_time", property="promotionEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_per_limit", property="promotionPerLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_type", property="promotionType", jdbcType=JdbcType.INTEGER),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR)
    })
    List<PmsProduct> selectByExample(PmsProductExample example);

    @Select({
        "select",
        "id, brand_id, product_category_id, feight_template_id, product_attribute_category_id, ",
        "name, pic, product_sn, delete_status, publish_status, new_status, recommand_status, ",
        "verify_status, sort, sale, price, promotion_price, gift_growth, gift_point, ",
        "use_point_limit, sub_title, original_price, stock, low_stock, unit, weight, ",
        "preview_status, service_ids, keywords, note, album_pics, detail_title, promotion_start_time, ",
        "promotion_end_time, promotion_per_limit, promotion_type, brand_name, product_category_name, ",
        "description, detail_desc, detail_html, detail_mobile_html",
        "from pms_product",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="brand_id", property="brandId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_category_id", property="productCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="feight_template_id", property="feightTemplateId", jdbcType=JdbcType.BIGINT),
        @Result(column="product_attribute_category_id", property="productAttributeCategoryId", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="pic", property="pic", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_sn", property="productSn", jdbcType=JdbcType.VARCHAR),
        @Result(column="delete_status", property="deleteStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="publish_status", property="publishStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="new_status", property="newStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="recommand_status", property="recommandStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="verify_status", property="verifyStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER),
        @Result(column="sale", property="sale", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL),
        @Result(column="promotion_price", property="promotionPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="gift_growth", property="giftGrowth", jdbcType=JdbcType.INTEGER),
        @Result(column="gift_point", property="giftPoint", jdbcType=JdbcType.INTEGER),
        @Result(column="use_point_limit", property="usePointLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="sub_title", property="subTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="original_price", property="originalPrice", jdbcType=JdbcType.DECIMAL),
        @Result(column="stock", property="stock", jdbcType=JdbcType.INTEGER),
        @Result(column="low_stock", property="lowStock", jdbcType=JdbcType.INTEGER),
        @Result(column="unit", property="unit", jdbcType=JdbcType.VARCHAR),
        @Result(column="weight", property="weight", jdbcType=JdbcType.DECIMAL),
        @Result(column="preview_status", property="previewStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="service_ids", property="serviceIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="keywords", property="keywords", jdbcType=JdbcType.VARCHAR),
        @Result(column="note", property="note", jdbcType=JdbcType.VARCHAR),
        @Result(column="album_pics", property="albumPics", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_title", property="detailTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="promotion_start_time", property="promotionStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_end_time", property="promotionEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="promotion_per_limit", property="promotionPerLimit", jdbcType=JdbcType.INTEGER),
        @Result(column="promotion_type", property="promotionType", jdbcType=JdbcType.INTEGER),
        @Result(column="brand_name", property="brandName", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_category_name", property="productCategoryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_desc", property="detailDesc", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_html", property="detailHtml", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="detail_mobile_html", property="detailMobileHtml", jdbcType=JdbcType.LONGVARCHAR)
    })
    PmsProduct selectByPrimaryKey(Long id);

    @UpdateProvider(type=PmsProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);

    @UpdateProvider(type=PmsProductSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);

    @UpdateProvider(type=PmsProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);

    @UpdateProvider(type=PmsProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PmsProduct record);

    @Update({
        "update pms_product",
        "set brand_id = #{brandId,jdbcType=BIGINT},",
          "product_category_id = #{productCategoryId,jdbcType=BIGINT},",
          "feight_template_id = #{feightTemplateId,jdbcType=BIGINT},",
          "product_attribute_category_id = #{productAttributeCategoryId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "pic = #{pic,jdbcType=VARCHAR},",
          "product_sn = #{productSn,jdbcType=VARCHAR},",
          "delete_status = #{deleteStatus,jdbcType=INTEGER},",
          "publish_status = #{publishStatus,jdbcType=INTEGER},",
          "new_status = #{newStatus,jdbcType=INTEGER},",
          "recommand_status = #{recommandStatus,jdbcType=INTEGER},",
          "verify_status = #{verifyStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "sale = #{sale,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "promotion_price = #{promotionPrice,jdbcType=DECIMAL},",
          "gift_growth = #{giftGrowth,jdbcType=INTEGER},",
          "gift_point = #{giftPoint,jdbcType=INTEGER},",
          "use_point_limit = #{usePointLimit,jdbcType=INTEGER},",
          "sub_title = #{subTitle,jdbcType=VARCHAR},",
          "original_price = #{originalPrice,jdbcType=DECIMAL},",
          "stock = #{stock,jdbcType=INTEGER},",
          "low_stock = #{lowStock,jdbcType=INTEGER},",
          "unit = #{unit,jdbcType=VARCHAR},",
          "weight = #{weight,jdbcType=DECIMAL},",
          "preview_status = #{previewStatus,jdbcType=INTEGER},",
          "service_ids = #{serviceIds,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "album_pics = #{albumPics,jdbcType=VARCHAR},",
          "detail_title = #{detailTitle,jdbcType=VARCHAR},",
          "promotion_start_time = #{promotionStartTime,jdbcType=TIMESTAMP},",
          "promotion_end_time = #{promotionEndTime,jdbcType=TIMESTAMP},",
          "promotion_per_limit = #{promotionPerLimit,jdbcType=INTEGER},",
          "promotion_type = #{promotionType,jdbcType=INTEGER},",
          "brand_name = #{brandName,jdbcType=VARCHAR},",
          "product_category_name = #{productCategoryName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=LONGVARCHAR},",
          "detail_desc = #{detailDesc,jdbcType=LONGVARCHAR},",
          "detail_html = #{detailHtml,jdbcType=LONGVARCHAR},",
          "detail_mobile_html = #{detailMobileHtml,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(PmsProduct record);

    @Update({
        "update pms_product",
        "set brand_id = #{brandId,jdbcType=BIGINT},",
          "product_category_id = #{productCategoryId,jdbcType=BIGINT},",
          "feight_template_id = #{feightTemplateId,jdbcType=BIGINT},",
          "product_attribute_category_id = #{productAttributeCategoryId,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "pic = #{pic,jdbcType=VARCHAR},",
          "product_sn = #{productSn,jdbcType=VARCHAR},",
          "delete_status = #{deleteStatus,jdbcType=INTEGER},",
          "publish_status = #{publishStatus,jdbcType=INTEGER},",
          "new_status = #{newStatus,jdbcType=INTEGER},",
          "recommand_status = #{recommandStatus,jdbcType=INTEGER},",
          "verify_status = #{verifyStatus,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "sale = #{sale,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "promotion_price = #{promotionPrice,jdbcType=DECIMAL},",
          "gift_growth = #{giftGrowth,jdbcType=INTEGER},",
          "gift_point = #{giftPoint,jdbcType=INTEGER},",
          "use_point_limit = #{usePointLimit,jdbcType=INTEGER},",
          "sub_title = #{subTitle,jdbcType=VARCHAR},",
          "original_price = #{originalPrice,jdbcType=DECIMAL},",
          "stock = #{stock,jdbcType=INTEGER},",
          "low_stock = #{lowStock,jdbcType=INTEGER},",
          "unit = #{unit,jdbcType=VARCHAR},",
          "weight = #{weight,jdbcType=DECIMAL},",
          "preview_status = #{previewStatus,jdbcType=INTEGER},",
          "service_ids = #{serviceIds,jdbcType=VARCHAR},",
          "keywords = #{keywords,jdbcType=VARCHAR},",
          "note = #{note,jdbcType=VARCHAR},",
          "album_pics = #{albumPics,jdbcType=VARCHAR},",
          "detail_title = #{detailTitle,jdbcType=VARCHAR},",
          "promotion_start_time = #{promotionStartTime,jdbcType=TIMESTAMP},",
          "promotion_end_time = #{promotionEndTime,jdbcType=TIMESTAMP},",
          "promotion_per_limit = #{promotionPerLimit,jdbcType=INTEGER},",
          "promotion_type = #{promotionType,jdbcType=INTEGER},",
          "brand_name = #{brandName,jdbcType=VARCHAR},",
          "product_category_name = #{productCategoryName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PmsProduct record);
}