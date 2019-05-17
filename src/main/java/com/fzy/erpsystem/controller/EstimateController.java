package com.fzy.erpsystem.controller;

import com.fzy.erpsystem.dao.GoodsMapper;
import com.fzy.erpsystem.dao.GoodsStockMapper;
import com.fzy.erpsystem.entity.Estimate;
import com.fzy.erpsystem.entity.Goods;
import com.fzy.erpsystem.entity.GoodsStock;
import com.fzy.erpsystem.entity.Kc;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @program: EstimateController
 * @description:
 * @author: fzy
 * @date: 2019/05/16 21:48:16
 **/
@RestController
@RequestMapping("/estimate")
public class EstimateController {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsStockMapper goodsStockMapper;

    @PostMapping
    public List<Estimate> findAll(@RequestBody Estimate estimate)throws Exception{
        List<Estimate> all=new ArrayList<>();
        String[] estimateDate = estimate.getEstimateDate().split(" - ");
        String[] consultDate = estimate.getConsultDate().split(" - ");
        Goods goods = goodsMapper.findone(estimate.getGoodId());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");

        Date aDate=sdf.parse(consultDate[0]);
        Date bDate=sdf.parse(consultDate[1]);
        long cdays=(bDate.getTime()-aDate.getTime())/(1000*3600*24);
        Map weekmap=new HashMap();
        for (int i=0;i<=cdays;i++){
            Calendar c = Calendar.getInstance();
            c.setTime(aDate);
            c.add(Calendar.DAY_OF_MONTH, i);
            Date time = c.getTime();

            if(!Objects.isNull(weekmap.get(dateFm.format(time)))){
                BigDecimal price = (BigDecimal) weekmap.get(dateFm.format(time));
                BigDecimal dayBusiness = getDayBusiness(estimate.getStoreId(), estimate.getGoodId(), sdf.format(time));
                BigDecimal res = price.add(dayBusiness);
                weekmap.put(dateFm.format(time),res);
            }else {
                weekmap.put(dateFm.format(time),getDayBusiness(estimate.getStoreId(),estimate.getGoodId(),sdf.format(time)));
            }
        }

        Date fDate=sdf.parse(estimateDate[0]);
        Date oDate=sdf.parse(estimateDate[1]);
        long days=(oDate.getTime()-fDate.getTime())/(1000*3600*24);
        for (int i=0;i<=days;i++){
            Estimate estimate1=new Estimate();
            Calendar c = Calendar.getInstance();
            c.setTime(fDate);
            c.add(Calendar.DAY_OF_MONTH, i);
            Date time = c.getTime();
            estimate1.setEstimateDate(sdf.format(time));
            estimate1.setWeek(dateFm.format(time));
            estimate1.setGoodsName(goods.getGoodsName());
            estimate1.setEstimateBusiness(weekmap.get(dateFm.format(time))==null?BigDecimal.ZERO: (BigDecimal) weekmap.get(dateFm.format(time)));
            estimate1.setConsultBusiness(weekmap.get(dateFm.format(time))==null?BigDecimal.ZERO: (BigDecimal) weekmap.get(dateFm.format(time)));
            all.add(estimate1);
        }

        return all;
    }


    public BigDecimal getDayBusiness(Long storeId,Long goodsId,String day){
       BigDecimal busedDay = goodsStockMapper.findBusedDay(day, storeId, goodsId);
        return busedDay==null?BigDecimal.ZERO:busedDay;
    }

}
