package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.dao.SupplierMapper;
import com.fzy.erpsystem.entity.GoodsStock;
import com.fzy.erpsystem.entity.Kc;
import com.fzy.erpsystem.entity.Supplier;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: GoodsStockController
 * @description:
 * @author: fzy
 * @date: 2019/05/12 13:13:48
 **/
@RestController
@RequestMapping("/stock")
public class GoodsStockController {

    @Resource
    private GoodsStockMapper goodsStockMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @PostMapping("/addin")
    public Map addin(@RequestBody  GoodsStock goodsStock){
        Map map=new HashMap();
        goodsStock.setKc(Kc.RK);
        Supplier one = supplierMapper.findOne(Long.parseLong(goodsStock.getSupplierName()));
        goodsStock.setSupplierName(one.getName());
        goodsStockMapper.kc(goodsStock);
        map.put("code","200");
        return map;
    }


    @PostMapping("/addout")
    public Map addout(@RequestBody  GoodsStock goodsStock){
        Map map=new HashMap();
        goodsStock.setKc(Kc.CK);
        Supplier one = supplierMapper.findOne(Long.parseLong(goodsStock.getSupplierName()));
        goodsStock.setSupplierName(one.getName());
        goodsStockMapper.kc(goodsStock);
        map.put("code","200");
        return map;
    }

    @GetMapping("/all/{type}")
    public List<GoodsStock> findAll(@PathVariable("type") String type){
        return goodsStockMapper.findAll(type);
    }


    @GetMapping("/search")
    public List<GoodsStock> findAll(@RequestParam("type") String type,@RequestParam("keyword") String keyword){
        return goodsStockMapper.search(type, Long.parseLong(keyword));
    }

    @GetMapping("/num")
    public List<GoodsStock> findStock(@RequestParam("keyword") String keyword){
        if(StringUtils.isEmpty(keyword)){
            keyword=null;
        }
        List<GoodsStock>num=goodsStockMapper.num(keyword);
        if(CollectionUtils.isEmpty(num)){
            return Collections.emptyList();
        }

        Map<String,GoodsStock> stockenum=new HashMap<>();

        for (GoodsStock detail:num ){
            if(!stockenum.containsKey(detail.getGoodsName())){
                BigDecimal kucushu=BigDecimal.ZERO;
                if(Kc.RK.equals(detail.getKc())){
                    kucushu = kucushu.add(detail.getGoodsAmt());
                }else if(Kc.CK.equals(detail.getKc())){
                    kucushu = kucushu.subtract(detail.getGoodsAmt());
                }
                detail.setGoodsAmt(kucushu);
                stockenum.put(detail.getGoodsName(),detail);
            }else {
                GoodsStock goodsStock = stockenum.get(detail.getGoodsName());
                BigDecimal decimal = goodsStock.getGoodsAmt();
                if(Kc.RK.equals(detail.getKc())){
                    decimal = decimal.add(detail.getGoodsAmt());
                }else if(Kc.CK.equals(detail.getKc())){
                    decimal = decimal.subtract(detail.getGoodsAmt());
                }
                goodsStock.setGoodsAmt(decimal);
                stockenum.put(detail.getGoodsName(),goodsStock);
            }
        }
         List<GoodsStock> list=new ArrayList(stockenum.size());
         stockenum.forEach((k,v)->{
             BigDecimal bigDecimal = new BigDecimal(5);
             if(v.getGoodsAmt().compareTo(bigDecimal)>0){
                 v.setStatus(1);
             }else {
                 v.setStatus(0);
             }
            list.add(v);
         });

        return list;
    }

}
