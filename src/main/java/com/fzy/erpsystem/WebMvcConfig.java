package com.fzy.erpsystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: WebMvcConfig
 * @description:
 * @author: fzy
 * @date: 2019/05/10 18:02:07
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 前面是url路径，后面是视图路径，添加thymeleaf后自动配置prefix为/templates,suffix为.html
        registry.addViewController("/login.html").setViewName("/login");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/page/in.html").setViewName("/page/in");
        registry.addViewController("/page/out.html").setViewName("/page/out");
        registry.addViewController("/page/stock.html").setViewName("/page/stock");
        registry.addViewController("/page/supplier.html").setViewName("/page/supplier");
        registry.addViewController("/page/storehouse.html").setViewName("/page/storehouse");
        registry.addViewController("/page/addSupplier.html").setViewName("/page/addSupplier");
        registry.addViewController("/page/addin.html").setViewName("/page/addin");
        registry.addViewController("/page/addout.html").setViewName("/page/addout");
        registry.addViewController("/page/estimate.html").setViewName("/page/estimate");
        registry.addViewController("/page/store.html").setViewName("/page/store");
        registry.addViewController("/page/addStore.html").setViewName("/page/addStore");
        registry.addViewController("/page/addHome.html").setViewName("/page/addHome");

        registry.addViewController("/page/goods.html").setViewName("/page/goods");
        registry.addViewController("/page/addgoods.html").setViewName("/page/addgoods");

        registry.addViewController("/page/rkmanage.html").setViewName("/page/rkmanage");
        registry.addViewController("/page/ckmanage.html").setViewName("/page/ckmanage");


        registry.addViewController("/page/xstable.html").setViewName("/page/xstable");
        registry.addViewController("/page/xsreport.html").setViewName("/page/xsreport");
        registry.addViewController("/page/cgtable.html").setViewName("/page/cgtable");
        registry.addViewController("/page/cgreport.html").setViewName("/page/cgreport");
        registry.addViewController("/page/kcreport.html").setViewName("/page/kcreport");

    }

}
