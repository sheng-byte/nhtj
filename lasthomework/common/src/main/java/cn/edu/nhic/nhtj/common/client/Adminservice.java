package cn.edu.nhic.nhtj.common.client;

import cn.edu.nhic.nhtj.common.vo.AdminVo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "nhtj-admin")
public interface Adminservice {
    @GetMapping("/auth/{aid}")
    public AdminVo getAdminByid(@PathVariable Integer aid);
}
