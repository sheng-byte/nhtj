package cn.edu.nhic.nhtj.tree.controller;


import cn.edu.nhic.nhtj.common.JwtUtils;
import cn.edu.nhic.nhtj.common.client.Adminservice;
import cn.edu.nhic.nhtj.common.entity.R;

import cn.edu.nhic.nhtj.tree.entity.Tree;
import cn.edu.nhic.nhtj.tree.service.ITreeService;
import cn.edu.nhic.nhtj.common.vo.AdminVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-12-02
 */
@RestController
@RequestMapping("/tree")
public class TreeController {

    @Autowired
    private ITreeService treeService;
    @Autowired
    private Adminservice adminservice;
//    @GetMapping("/{tid}")
//    public R getTree(@PathVariable Integer tid){
//        Tree byId = treeService.getById(tid);
//        Integer aid = byId.getAid();
//        AdminVo adminVo = adminservice.getAdminByid(aid);
//        byId.setAname(adminVo.getAname());
//        return R.success(byId);
//    }

    @GetMapping("/{pno}/{psize}/{orderBy}/{ascOrDesc}")
    public R getTreeList(@PathVariable Integer pno,@PathVariable Integer psize,@PathVariable String orderBy,@PathVariable String ascOrDesc){
        Page<Tree> page =new Page<>(pno, psize);
        QueryWrapper<Tree> treeQueryWrapper = new QueryWrapper<>();
        if (!orderBy.isEmpty()){
            treeQueryWrapper.orderBy(true,ascOrDesc.equals("asc"),orderBy);
            treeQueryWrapper.eq("tree.deleted",0);
        }
        return R.success(treeService.page(page,treeQueryWrapper));

    }

    @PostMapping("/addtree")
    public R addTree(@RequestBody Tree tree){
        treeService.save(tree);
        return R.success("添加成功");
    }
    @GetMapping("/{tid}")
    public R getTree(@PathVariable Integer tid){
        Tree byId = treeService.getById(tid);
        return R.success(byId);
    }
    @PutMapping
    public R updateTree(@RequestBody Tree tree){
        treeService.updateById(tree);
        return R.success("修改成功");
    }

    @DeleteMapping("/{tid}")
    public R deleteTree(@PathVariable Integer tid){
        treeService.removeById(tid);
        return R.success("删除成功");
    }





}
