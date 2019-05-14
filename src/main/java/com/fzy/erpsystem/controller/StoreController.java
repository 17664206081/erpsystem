package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.StoreMapper;
import com.fzy.erpsystem.entity.Store;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: StoreContoller
 * @description:
 * @author: fzy
 * @date: 2019/05/13 19:26:50
 **/
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreMapper storeMapper;

    @PostMapping
    public Map save(@RequestBody Store store){
        Map map=new HashMap();
        storeMapper.insert(store);
        map.put("code","200");
        return map;
    }

    @GetMapping
    public List<Store> findAll(){
        List<Store> all = storeMapper.findAll();
        return all;
    }

    @PutMapping
    public Map updateStore(@RequestBody Store store){
        Map map=new HashMap();
        storeMapper.update(store);
        map.put("code","200");
        return map;
    }

    @DeleteMapping("/{id}")
    public Map delete(@PathVariable("id") Long id){
        Map map=new HashMap();
        storeMapper.delete(id);
        map.put("code","200");
        return map;
    }

    @GetMapping("/search")
    public List<Store> search(@RequestParam("storerName") String storerName){
        return  storeMapper.search(storerName);
    }
}
