package cn.edu.nhic.nhtj.tree.service.impl;

import cn.edu.nhic.nhtj.tree.entity.Tree;
import cn.edu.nhic.nhtj.tree.mapper.TreeMapper;
import cn.edu.nhic.nhtj.tree.service.ITreeService;
import cn.edu.nhic.nhtj.vo.TreeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-12-02
 */
@Service
public class TreeServiceImpl extends ServiceImpl<TreeMapper, Tree> implements ITreeService {


}
