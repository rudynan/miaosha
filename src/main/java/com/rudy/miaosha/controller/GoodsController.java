package com.rudy.miaosha.controller;

import com.rudy.miaosha.domain.Goods;
import com.rudy.miaosha.domain.MXUser;
import com.rudy.miaosha.redis.GoodsKey;
import com.rudy.miaosha.service.GoodsService;
import com.rudy.miaosha.service.RedisService;
import com.rudy.miaosha.service.UserService;
import com.rudy.miaosha.service.impl.UserServiceImpl;
import com.rudy.miaosha.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String toList(MXUser mxUser, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (mxUser == null) {
            return "login";
        } else {
            // 取缓存
            String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
            if (StringUtils.isNotBlank(html)) {
                return html;
            }
            model.addAttribute("user", mxUser);
            List<GoodsVo> goodsVoList = goodsService.listGoodsVo();
            model.addAttribute("goodsList", goodsVoList);
            // 手动渲染生成html
            html = this.handleHtml(request, response, model, "goods_list");
            if (StringUtils.isNotBlank(html)){
                redisService.set(GoodsKey.getGoodsList, "", html);
            }
            return html;
        }
    }

    @RequestMapping(value = "/to_datail/{goodsId}",produces = "text/html")
    @ResponseBody
    public String toDetail(Model model, MXUser user, @PathVariable("goodsId") Long goodsId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, goodsId+"", String.class);
        if (StringUtils.isNotBlank(html)) {
            return html;
        }

        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        long endTime = goodsVo.getEndDate().getTime();
        long startTime = goodsVo.getStartDate().getTime();
        long currentTime = System.currentTimeMillis();
        model.addAttribute("user", user);
        model.addAttribute("goods", goodsVo);
        int miaoshaStatus = 0;
        int remainSeconds = 0;

        if (currentTime < startTime) {
            // 秒杀还未开始
            miaoshaStatus = 0;
            remainSeconds = (int) (startTime - currentTime) / 1000;
        } else if (currentTime > endTime) {
            // 秒杀已经结束
            miaoshaStatus = 1;
            remainSeconds = -1;
        } else {
            // 秒杀正在进行中
            miaoshaStatus = 2;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        html = this.handleHtml(request, response, model, "goods_detail");
        redisService.set(GoodsKey.getGoodsDetail, goodsId+"", html);
        return html;
    }


    private String handleHtml(HttpServletRequest request,HttpServletResponse response,Model model,String templateName){
        SpringWebContext springWebContext = new SpringWebContext(request, response, request.getServletContext(), request
                .getLocale(), model.asMap(), applicationContext);
        return thymeleafViewResolver.getTemplateEngine().process(templateName, springWebContext);
    }
}
