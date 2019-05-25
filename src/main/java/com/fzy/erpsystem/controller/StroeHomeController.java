package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.StoreHomeMapper;
import com.fzy.erpsystem.entity.Store;
import com.fzy.erpsystem.entity.StoreHome;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shome")
public class StroeHomeController {

    @Resource
    private StoreHomeMapper storeHomeMapper;

    @GetMapping
    public List<StoreHome> findAll(){
        List<StoreHome> all = storeHomeMapper.findAll();
        return all;
    }

    @PostMapping
    public Map save(@RequestBody StoreHome storeHome){
        Map map=new HashMap();
        System.out.println(storeHome.getCkName());
        storeHomeMapper.insert(storeHome);
        map.put("code","200");
        return map;
    }

    @DeleteMapping("/{id}")
    public Map delete(@PathVariable("id") Long id){
        Map map=new HashMap();
        storeHomeMapper.delete(id);
        map.put("code","200");
        return map;
    }

    @GetMapping("/search")
    public List<StoreHome> search(@RequestParam("ckName") String ckName){
        return  storeHomeMapper.search(ckName);
    }

}
