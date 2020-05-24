package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.ItemDao;
import com.lzheng.familyfinance.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ItemService
 * @Author 6yi
 * @Date 2020/5/24 14:34
 * @Version 1.0
 * @Description:
 */

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItem(String type){
        return itemDao.selectByType(type);
    }

}
