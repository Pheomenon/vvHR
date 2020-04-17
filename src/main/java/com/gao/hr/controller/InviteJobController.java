package com.gao.hr.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.hr.common.R;
import com.gao.hr.entity.Cj;
import com.gao.hr.entity.InviteJob;
import com.gao.hr.service.CjService;
import com.gao.hr.service.InviteJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gao
 * @since 2020-04-14
 */
@RestController
@CrossOrigin
@RequestMapping("/invite")
public class InviteJobController {

    @Autowired
    private InviteJobService inviteJobService;

    @PutMapping
    public R updateCj(@RequestBody InviteJob InviteJob) {
        if (inviteJobService.updateById(InviteJob)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping
    public R addInvite(@RequestBody InviteJob InviteJob) {
        if (inviteJobService.updateById(InviteJob)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteInvite(@PathVariable Integer id) {
        if (inviteJobService.removeById(id)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("/bind")
    public R bindResumeWithOtherInfo(){
        QueryWrapper<InviteJob> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        InviteJob result = inviteJobService.getOne(wrapper);
        return R.ok().data("id",result.getId());
    }

    @GetMapping("/{id}")
    public R getInvite(@PathVariable Integer id){
        InviteJob inviteJob = inviteJobService.getById(id);
        return R.ok().data("invite", inviteJob);
    }

    @PostMapping("/search/{current}/{limit}")
    public R pageCjCondition(@PathVariable long current,
                             @PathVariable long limit,
                             @RequestBody(required = false) Map inviteCondition) {
        Page<InviteJob> invitePage = new Page<>(current, limit);
        QueryWrapper<InviteJob> wrapper = new QueryWrapper<>();
        if (inviteCondition.get("begin") != null) {
            wrapper.gt("create_time", inviteCondition.get("begin").toString());
        }
        if (inviteCondition.get("end") != null) {
            wrapper.le("create_time", inviteCondition.get("end").toString());
        }
        wrapper.orderByDesc("create_time");
        wrapper.isNotNull("name");
        inviteJobService.page(invitePage, wrapper);
        long total = invitePage.getTotal();
        List<InviteJob> records = invitePage.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }
}

