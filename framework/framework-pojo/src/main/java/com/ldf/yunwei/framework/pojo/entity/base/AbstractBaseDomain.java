package com.ldf.yunwei.framework.pojo.entity.base;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

// 通用的领域模型
@Data
public abstract class AbstractBaseDomain<T> implements Serializable {

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 修改时间
     */
    @TableField("updated_time")
    private Date updatedTime;

}
