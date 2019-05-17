package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsMapper;
import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.dao.StoreMapper;
import com.fzy.erpsystem.dao.SupplierMapper;
import com.fzy.erpsystem.entity.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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
    private GoodsMapper goodsMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @PostMapping("/addin")
    public Map addin(@RequestBody  GoodsStock goodsStock){
        Map map=new HashMap();
        goodsStock.setKc(Kc.RK);

        Supplier one = supplierMapper.findOne(goodsStock.getSupplierId());
        goodsStock.setSupplierName(one.getName());

        Store store = storeMapper.findOne(goodsStock.getStoreId());
        goodsStock.setStoreName(store.getName());

        Goods goods = goodsMapper.findone(goodsStock.getGoodsId());
        goodsStock.setGoodsName(goods.getGoodsName());

        goodsStockMapper.kc(goodsStock);
        map.put("code","200");
        return map;
    }


    @PostMapping("/addout")
    public Map addout(@RequestBody  GoodsStock goodsStock){
        Map map=new HashMap();
        List<GoodsStock> list = goodsStockMapper.findGoodsStock(goodsStock.getGoodsId(), goodsStock.getStoreId());
        BigDecimal stockNum=BigDecimal.ZERO;
        for (GoodsStock detail:list ){
            if(Kc.CK.equals(detail.getKc())){
                stockNum=stockNum.subtract(detail.getGoodsAmt());
            }else if(Kc.RK.equals(detail.getKc())){
                stockNum=stockNum.add(detail.getGoodsAmt());
            }
        }

        if(stockNum.compareTo(goodsStock.getGoodsAmt())<0){
            map.put("code","500");
            map.put("msg","库存不足");
            return map;
        }

        goodsStock.setKc(Kc.CK);
        Supplier one = supplierMapper.findOne(goodsStock.getSupplierId());
        goodsStock.setSupplierName(one.getName());

        Store store = storeMapper.findOne(goodsStock.getStoreId());
        goodsStock.setStoreName(store.getName());

        Goods goods = goodsMapper.findone(goodsStock.getGoodsId());
        goodsStock.setGoodsName(goods.getGoodsName());

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
    public List<GoodsStock> findStock(@RequestParam(value = "keyword" ,required = false) String keyword){
        if(StringUtils.isEmpty(keyword)){
            keyword=null;
        }
        List<GoodsStock>num=goodsStockMapper.num(keyword);
        if(CollectionUtils.isEmpty(num)){
            return Collections.emptyList();
        }

        Map<String,GoodsStock> stockenum=new HashMap<>();

        for (GoodsStock detail:num ){
            if(!stockenum.containsKey(detail.getStoreId()+"_"+detail.getGoodsId())){
                BigDecimal kucushu=BigDecimal.ZERO;
                if(Kc.RK.equals(detail.getKc())){
                    kucushu = kucushu.add(detail.getGoodsAmt());
                }else if(Kc.CK.equals(detail.getKc())){
                    kucushu = kucushu.subtract(detail.getGoodsAmt());
                }
                detail.setGoodsAmt(kucushu);
                stockenum.put(detail.getStoreId()+"_"+detail.getGoodsId(),detail);
            }else {
                GoodsStock goodsStock = stockenum.get(detail.getStoreId()+"_"+detail.getGoodsId());
                BigDecimal decimal = goodsStock.getGoodsAmt();
                if(Kc.RK.equals(detail.getKc())){
                    decimal = decimal.add(detail.getGoodsAmt());
                }else if(Kc.CK.equals(detail.getKc())){
                    decimal = decimal.subtract(detail.getGoodsAmt());
                }
                goodsStock.setGoodsAmt(decimal);
                stockenum.put(detail.getStoreId()+"_"+detail.getGoodsId(),goodsStock);
            }
        }
         List<GoodsStock> list=new ArrayList(stockenum.size());
         stockenum.forEach((k,v)->{
             Goods goods = goodsMapper.findone(v.getGoodsId());
             if(v.getGoodsAmt().compareTo(goods.getStock())>0){
                 v.setStatus(1);
             }else {
                 v.setStatus(0);
             }
            list.add(v);
         });

        return list;
    }

    @GetMapping("/manage/{type}")
    public List<GoodsStock> manage(@PathVariable("type") String type, @RequestParam(value = "keyword", required = false) String keyword){
        if(StringUtils.isEmpty(keyword)){
            keyword=null;
        }
        return goodsStockMapper.manage(type,keyword);
    }


    @PostMapping("/yasuo/{id}")
    public Map yasuo(@PathVariable("id") Long id){
        Map map=new HashMap();
        goodsStockMapper.yasuo(id);
        map.put("code","200");
        return map;
    }

}
