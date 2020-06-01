package com.lzheng.familyfinance.controller;

import com.alibaba.fastjson.JSONObject;
import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.dto.DeleteDTO;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.exception.GlobalException;
import com.lzheng.familyfinance.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ItemController
 * @Author 6yi
 * @Date 2020/5/24 14:30
 * @Version 1.0
 * @Description:
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/getitem")
    public Result getItem(@RequestParam(value = "type", required = true) Integer type){
        List<Item> item=null;
        if(type==1){
            item= itemService.getItem("支出");
        }else if(type==2){
            item= itemService.getItem("收入");
        }
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(item);
        return result;
    }
    @PostMapping("/getallitem")
    public Result getItem(){
        List<Item> item=null;
        item= itemService.getAllItem();
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(item);
        return result;
    }

    @PutMapping("/update")
    public Result update(@RequestBody JSONObject jsonParam, HttpServletRequest request){
        Item item = jsonParam.toJavaObject(Item.class);
        if(request.getAttribute("type").equals("子女")){
            throw  new GlobalException(302,"你没有修改的权限");
        }
        if(item.getIType().equals("1")){
            item.setIType("支出");
        }else {
            item.setIType("收入");
        }
        itemService.updateItem(item);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }
    @PutMapping("/add")
    public Result add(@RequestBody JSONObject jsonParam, HttpServletRequest request){
        Item item = jsonParam.toJavaObject(Item.class);
        if(request.getAttribute("type").equals("子女")){
            throw  new GlobalException(302,"你没有添加的权限");
        }
        if(item.getIType().equals("1")){
            item.setIType("支出");
        }else {
            item.setIType("收入");
        }
        itemService.add(item);
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody JSONObject jsonParam, HttpServletRequest request){

        DeleteDTO deleteDTO = jsonParam.toJavaObject(DeleteDTO.class);
        System.out.println(request.getAttribute("type"));
        if(request.getAttribute("type").equals("子女")){
           throw  new GlobalException(302,"你没有删除的权限");
        }
        System.out.println("deto:"+deleteDTO.getOids());
        itemService.deleteItem(deleteDTO.getOids());
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        return result;
    }


}
