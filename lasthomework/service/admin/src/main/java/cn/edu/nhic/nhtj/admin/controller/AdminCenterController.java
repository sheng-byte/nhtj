package cn.edu.nhic.nhtj.admin.controller;

import cn.edu.nhic.nhtj.admin.entity.Admin;
import cn.edu.nhic.nhtj.admin.service.IAdminService;
import cn.edu.nhic.nhtj.common.JwtUtils;
import cn.edu.nhic.nhtj.common.entity.R;
import cn.edu.nhic.nhtj.common.vo.AdminVo;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AdminCenterController {
    @Autowired
    private IAdminService adminService;
    @GetMapping
    public R getAdmin(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer adminid = JwtUtils.verifyAdminTokenAndGetId(token);
        Admin admin = adminService.getById(adminid);
        admin.setApswd("");
        return R.success(admin);
    }
    @GetMapping("/{aid}")
    public AdminVo getAdminByid(@PathVariable Integer aid) {
        Admin admin = adminService.getById(aid);
        AdminVo adminVo = new AdminVo();
        BeanUtil.copyProperties( admin,adminVo);
        return adminVo;
    }

}
