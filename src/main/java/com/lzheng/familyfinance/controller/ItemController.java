package com.lzheng.familyfinance.controller;

import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.dto.Result;
import com.lzheng.familyfinance.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getitem")
    public Result getItem(@RequestParam(value = "type", required = true) String type){
        List<Item> item = itemService.getItem(type);
        Result result = new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(item);
        return result;
    }


}
