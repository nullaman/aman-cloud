//package com.aman.cloud.model;
//
//import com.baomidou.mybatisplus.annotation.Version;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//import org.springframework.data.annotation.*;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
///**
// * 公共实体父类
// *
// * @author caoxiang
// * @date 2020-01-29
// */
//@Data
//public class BaseEntity<T> implements Serializable {
//
//    @Id
//    @TableId(value = "id", type = IdType.AUTO)
//    @ApiModelProperty(
//            value = "编号",
//            notes = "唯一标识"
//    )
//    private Integer id;
//
//    @CreatedBy
//    @ApiModelProperty("创建者")
//    private Integer createBy;
//
//    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    @CreatedDate
//    @JsonFormat(
//            timezone = "GMT+8",
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    @DateTimeFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    @ApiModelProperty("创建时间")
//    private LocalDateTime createTime;
//
//
//    @ApiModelProperty("更新者")
//    @LastModifiedBy
//    private Integer updateBy;
//
//    @TableField(value = "update_time", fill = FieldFill.UPDATE, update = "NOW()")
//    @LastModifiedDate
//    @JsonFormat(
//            timezone = "GMT+8",
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    @DateTimeFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    @ApiModelProperty("更新时间")
//    private LocalDateTime updateTime;
//
//    @Version
//    @TableField(value = "version", fill = FieldFill.INSERT, update = "%s+1")
//    @ApiModelProperty("乐观锁-版本号")
//    private Integer version = 0;
//
//    @TableLogic
//    @TableField(value = "del_flag", fill = FieldFill.INSERT)
//    @ApiModelProperty("逻辑删除，0生效，1已删除")
//    private Integer delFlag = 0;
//
//}
