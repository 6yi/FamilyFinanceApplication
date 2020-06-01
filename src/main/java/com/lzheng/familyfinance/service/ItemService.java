package com.lzheng.familyfinance.service;

import com.lzheng.familyfinance.dao.ItemDao;
import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

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
    DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    public List<Item> getItem(String type){
        return itemDao.selectByType(type);
    }

    public List<Item> getAllItem() {
        return itemDao.selectAll();
    }

    public void add(Item item){
        if(itemDao.selectName(item.getIName())!=null){
            throw new GlobalException(505,"已经存在该项目名");
        }
        itemDao.insert(item);

    }
    public void updateItem(Item item) {
         itemDao.updateByPrimaryKey(item);
    }


    public void deleteItem(Integer[] ids){
        TransactionStatus transactionStatus=null;
        try{
            transactionStatus=dataSourceTransactionManager.getTransaction(transactionDefinition);
            for (Integer id:ids) {
                itemDao.updateItemStatus(id);
                orderDao.updateOrderByIid(id);
            }
            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            if(transactionStatus!=null){
                dataSourceTransactionManager.rollback(transactionStatus);
            }
            throw new GlobalException(500,"事务失败");
        }
    }
}
