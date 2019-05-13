package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.SupplierMapper;
import com.fzy.erpsystem.entity.Supplier;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: SupplierContoller
 * @description:
 * @author: fzy
 * @date: 2019/05/11 11:55:12
 **/
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private SupplierMapper supplierMapper;

    @PostMapping
    public Map save(@RequestBody Supplier supplier){
        Map map=new HashMap();
        supplierMapper.insert(supplier);
        map.put("code","200");
        return map;
    }

    @GetMapping
    public List<Supplier> findAll(){
        List<Supplier> all = supplierMapper.findAll();
        return all;
    }

    @PutMapping
    public Map update(@RequestBody Supplier supplier){
        Map map=new HashMap();
        supplierMapper.update(supplier);
        map.put("code","200");
        return map;
    }

    @DeleteMapping("/{id}")
    public Map delete(@PathVariable("id") Long id){
        Map map=new HashMap();
        supplierMapper.delete(id);
        map.put("code","200");
        return map;
    }

    @GetMapping("/search")
    public List<Supplier> search(@RequestParam("supplierName") String supplierName){
        return  supplierMapper.search(supplierName);
    }
}
