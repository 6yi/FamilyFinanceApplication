package com.lzheng.familyfinance.controller;

import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        result.setCode(200);
        result.setMsg("success");
        result.setData(item);
        return result;
    }


}
