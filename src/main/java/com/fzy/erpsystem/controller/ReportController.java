package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.entity.GoodsStock;
import com.fzy.erpsystem.entity.Kc;
import com.fzy.erpsystem.entity.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: ReportController
 * @description:
 * @author: fzy
 * @date: 2019/05/15 07:56:45
 **/
@RestController
@RequestMapping("/report")
public class ReportController {

    @Resource
    private GoodsStockMapper goodsStockMapper;

    @GetMapping("/cg")
    public List<GoodsStock> findAllCg(@RequestParam("storeId") Long storeId){
        return goodsStockMapper.report(Kc.RK + "", storeId);
    }

    @GetMapping("/xs")
    public List<GoodsStock> findAllXS(@RequestParam("storeId") Long storeId){
        return goodsStockMapper.report(Kc.CK+"",storeId);
    }

    @GetMapping("/kc")
    public List<GoodsStock> findAllkc(@RequestParam("storeId") String storeId){
        return Collections.emptyList();
    }

}
