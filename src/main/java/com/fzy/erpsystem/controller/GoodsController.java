package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsMapper;
import com.fzy.erpsystem.dao.StoreHomeMapper;
import com.fzy.erpsystem.dao.SupplierMapper;
import com.fzy.erpsystem.entity.Goods;
import com.fzy.erpsystem.entity.StoreHome;
import com.fzy.erpsystem.entity.Supplier;
import org.apache.catalina.Store;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: GoodsController
 * @description:
 * @author: fzy
 * @date: 2019/05/14 19:42:36
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private StoreHomeMapper storeHomeMapper;
    @Resource
    private SupplierMapper supplierMapper;

    @PostMapping
    public Map save(@RequestBody Goods goods){
        Map map=new HashMap();
        Goods billNo = goodsMapper.findBillNo(goods.getBillNo());
        if(!Objects.isNull(billNo)){
            map.put("code","500");
            map.put("msg","物品编码存在");
            return map;
        }

        Supplier mapperOne = supplierMapper.findOne(goods.getSupplierId());
        goods.setSupplierName(mapperOne.getName());
        StoreHome storeHomeOne = storeHomeMapper.findOne(goods.getHomeId());
        goods.setCkName(storeHomeOne.getCkName());
        goodsMapper.save(goods);
        map.put("code","200");

        return map;
    }

    @GetMapping
    public List<Goods> findAll(){
        return goodsMapper.findAll();
    }

    @GetMapping("/search")
    public List<Goods> search(@RequestParam("keyword") String keyword){
        if(StringUtils.isEmpty(keyword)){
            return goodsMapper.findAll();
        }else {
            return goodsMapper.search(keyword);
        }
    }

    @GetMapping("/{id}")
    public List<Goods> findBySupplier(@PathVariable("id") Long id){
        return goodsMapper.findBySupplier(id);
    }

    @GetMapping("/find")
    public List<Goods> findByhome(@RequestParam("homeId") Long homeId,@RequestParam("supplierId") String supplierId){
        if(supplierId.equals("0")){
            supplierId = "%";
        }
        return goodsMapper.findByhome(homeId,supplierId);
    }

    @DeleteMapping("/{id}")
    public Map delete(@PathVariable("id") Long id){
        Map map=new HashMap();
        goodsMapper.delete(id);
        map.put("code","200");
        return map;
    }
}
