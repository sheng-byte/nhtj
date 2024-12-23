package cn.edu.nhic.nhtj.tree.mapper;

import cn.edu.nhic.nhtj.tree.entity.Tree;
import cn.edu.nhic.nhtj.vo.TreeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-12-02
 */
public interface TreeMapper extends BaseMapper<Tree> {

}
