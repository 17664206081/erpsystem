package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.entity.*;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.val;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    @PostMapping("/cgd")
    public List<GoodsStockVO> findAllCgd(@RequestBody GoodsStockVO goodsStockVO,@RequestParam("type")String type) throws Exception{
        List<GoodsStockVO> all= new ArrayList<>();
        String[] reportTimeDate = goodsStockVO.getReportTime().split(" - ");
        String goodsId= null;
        if(goodsStockVO.getGoodsId()==0){
            goodsId = "%";
        }else {
            goodsId = goodsStockVO.getGoodsId().toString();
        }
        List<GoodsStock> goodsdtock = goodsStockMapper.reportgd(type,goodsStockVO.getStoreId(),goodsStockVO.getHomeId(),goodsId);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Date aDate=sdf.parse(reportTimeDate[0]);
        Date bDate=sdf.parse(reportTimeDate[1]);

        int goodsnum = 0;
        List<Long> GoodsId = new ArrayList<>();
        List<String> GoodsName= new ArrayList<>();
        List<String> StoreName= new ArrayList<>();
        boolean falg = true;
        //判断有几种物品
        for(int i=0;i<goodsdtock.size();i++){
            for(int j = 0;j<GoodsId.size();j++){
                if(GoodsId.get(j)==goodsdtock.get(i).getGoodsId()){
                    falg=false;
                }
            }
            if(falg){
                goodsnum++;
                GoodsId.add(goodsdtock.get(i).getGoodsId());
                GoodsName.add(goodsdtock.get(i).getGoodsName());
                StoreName.add(goodsdtock.get(i).getStoreName());
            }
            falg = true;
        }

        for(int j =0;j<goodsnum;j++){
            GoodsStockVO newvo = new GoodsStockVO();
            if(goodsdtock!=null){
                String[] stringTime = new String[goodsdtock.size()];
                BigDecimal[] numDate = new BigDecimal[goodsdtock.size()];
                newvo.setGoodsName(GoodsName.get(j));
                newvo.setStoreName(StoreName.get(j));
                for(int i = 0;i<goodsdtock.size();i++){
                    stringTime[i]=sdf.format(goodsdtock.get(i).getBuseDate());
                    if(goodsdtock.get(i).getGoodsId()==GoodsId.get(j)){
                        if(goodsdtock.get(i).getBuseDate().compareTo(aDate)>=0&&goodsdtock.get(i).getBuseDate().compareTo(bDate)<=0){
                            numDate[i]=goodsdtock.get(i).getGoodsAmt();
                        }
                    }
                }
                newvo.setStringTime(stringTime);
                newvo.setNumDate(numDate);
            }
            all.add(newvo);
        }

        return all;

    }

    @GetMapping("/cg")
    public List<GoodsStock> findAllCg(@RequestParam("storeId") Long storeId){
//        System.out.println("+++"+homeId+"-----"+goodId);
//        if(homeId==null){
//            homeId = "%";
//        }
//        if(goodId==null){
//            goodId = "%";
//        }
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

    public BigDecimal getDayBusiness(Long storeId,Long goodsId,String day){
        BigDecimal busedDay = goodsStockMapper.findBusedDay(day, storeId, goodsId);
        return busedDay==null?BigDecimal.ZERO:busedDay;
    }
}
