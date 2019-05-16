package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.entity.GoodsStock;
import com.fzy.erpsystem.entity.Kc;
import com.fzy.erpsystem.entity.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
    public List<GoodsStock> findAllkc(@RequestParam("storeId") Long storeId){
        Map<String,GoodsStock> all=new HashMap<>();
        List<GoodsStock> rklist = goodsStockMapper.report(Kc.RK + "", storeId);
        if(!CollectionUtils.isEmpty(rklist)){
            rklist.forEach(detail -> {
               if(!Objects.isNull(all.get(generate(detail)))){
                   GoodsStock goodsStock = all.get(generate(detail));
                   detail.setGoodsAmt(goodsStock.getGoodsAmt().add(detail.getGoodsAmt()));
               }
                   all.put(generate(detail),detail);

            });
        }

        List<GoodsStock> cklist = goodsStockMapper.report(Kc.CK + "", storeId);
        if(!CollectionUtils.isEmpty(cklist)){
            cklist.forEach(detail -> {
                if(!Objects.isNull(all.get(generate(detail)))){
                    GoodsStock goodsStock = all.get(generate(detail));
                    detail.setGoodsAmt(goodsStock.getGoodsAmt().subtract(detail.getGoodsAmt()));
                }
                all.put(generate(detail),detail);

            });
        }

        List<GoodsStock> alllist=new ArrayList<>();
        all.forEach((k,v)->{
            alllist.add(v);
        });

        return alllist;
    }


    public String generate (GoodsStock stock){
        return stock.getStoreId()+"_"+stock.getStoreId()+"_" +stock.getGoodsId();
    }

}
