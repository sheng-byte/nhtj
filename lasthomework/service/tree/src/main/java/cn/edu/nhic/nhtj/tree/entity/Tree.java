package cn.edu.nhic.nhtj.tree.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tree")
//@ApiModel(value="Tree对象", description="")
public class Tree implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    private String tname;

    private Integer deleted;

    private String tfl;

    private String tcolor;

    private String ttime;

    private Integer spring;

    private Integer summer;

    private Integer autumn;

    private Integer winter;
    @TableField(exist = false)
    private Integer aid;

    private String image;
    @TableField(exist = false)
    private String aname;

}
