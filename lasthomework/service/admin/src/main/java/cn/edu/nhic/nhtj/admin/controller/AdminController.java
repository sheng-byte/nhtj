package cn.edu.nhic.nhtj.admin.controller;


import cn.edu.nhic.nhtj.admin.Dto.AdminDto;
import cn.edu.nhic.nhtj.admin.entity.Admin;
import cn.edu.nhic.nhtj.admin.service.IAdminService;
import cn.edu.nhic.nhtj.admin.vo.AdminLoginVo;
import cn.edu.nhic.nhtj.admin.vo.AdminVo;
import cn.edu.nhic.nhtj.common.JwtUtils;
import cn.edu.nhic.nhtj.common.entity.R;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-12-02
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public R login(@RequestBody AdminDto admindto) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("aname", admindto.getUsername());
        queryWrapper.eq("apswd", admindto.getPassword());
        Admin admin = adminService.getOne(queryWrapper);
        if (admin != null) {
            String token = JwtUtils.createAdminToken( admin.getAid());
            return R.success(token);
        }else{
            return R.error("用户名或密码错误");
        }

    }

    @GetMapping("/getInfo")
    public R getInfo(@RequestHeader("token") String token) {
        Integer aid = JwtUtils.verifyAdminTokenAndGetId(token);
        AdminVo adminVo = new AdminVo();
        BeanUtil.copyProperties(adminService.getById(aid), adminVo);
        adminVo.setAvator("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return R.success(adminVo);

    }

    @DeleteMapping("logout")
    public R logout() {
        return R.success("退出成功");
    }

}
