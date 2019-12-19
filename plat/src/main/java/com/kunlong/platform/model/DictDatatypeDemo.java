package com.kunlong.platform.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Table(name = "dict_datatype")//如果实体类名字与数据库不一致又不使用注解会报错
@Component
public class DictDatatypeDemo extends KunlongModel implements Serializable {
    /**
     * 字典ID
     */
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")

    @Column(name = "data_inner_id")// 注解声明该表的字段名
    private Integer dataInnerId;

    /**
     * 分类编号
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 字典编号
     */
    private Integer dataId;

    /**
     * 字典名称
     */
    private String dataName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getDataInnerId() {
        return dataInnerId;
    }

    public void setDataInnerId(Integer dataInnerId) {
        this.dataInnerId = dataInnerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public String toJson() {
        return this.toString();
}
}