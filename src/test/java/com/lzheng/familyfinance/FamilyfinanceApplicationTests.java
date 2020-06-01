package com.lzheng.familyfinance;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.lzheng.familyfinance.dao.ItemDao;
import com.lzheng.familyfinance.dao.MemberDao;
import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.domain.Order;
import com.lzheng.familyfinance.service.ItemService;
import com.lzheng.familyfinance.service.StatisticsService;
import com.lzheng.familyfinance.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.util.Date;


@SpringBootTest
class FamilyfinanceApplicationTests {
    Digester md5 = new Digester(DigestAlgorithm.MD5);
    @Autowired
    MemberDao dao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    OrderDao orderDao;

    @Autowired
    ItemService itemService;


    @Autowired
    StatisticsService statisticsService;
//
//    @Test
//    void contextLoads() {
//        Member member = new Member();
//        member.setMName("刘正");
//        member.setMUsername("lzheng");
//        member.setMType("家长");
//        member.setMPassword(md5.digestHex("lzheng.1"));
//        member.setStatus(1);
//        dao.insert(member);
//    }


//    @Test
//    void contextLoads2() {
//        Item item=new Item();
//        item.setIName("娱乐");
//        item.setIType("支出");
//        item.setStatus(1);
//        itemDao.insert(item);
//    }
//    @Test
//    void contextLoads3() {
////        for(int i=1;i<50;i++){
//        DateTime dateTime = DateUtil.parse("2020-05-21");
//         String x="99.9";
//        Order order=new Order();
//            order.setIId(1);
//            order.setMId(2);
//            order.setOMoney(new BigDecimal(x));
//            order.setStatus(1);
//            order.setOTips("hh");
//            order.setODate(dateTime);
//            orderDao.insert(order);
////        }
//
//    }
    //根据日期条件查询
    @Test
    void contextLoads4() {
        String dateStr = "2019-11-27 ";
        Date date = DateUtil.parse(dateStr);
        Date date2 = DateUtil.parse("2020-06-07");
        System.out.println(statisticsService.getResult(date, date2, 2));
    }


    @Test
    void contextLoads42() {

        for (int i = 0; i < 150; i++) {
            int mid = 1013;
            int iid = RandomUtil.randomInt(1, 5);
            int dateId = RandomUtil.randomInt(10, 30);
            BigDecimal bigDecimal = RandomUtil.randomBigDecimal(new BigDecimal(0), new BigDecimal(300));
            String dateStr = "2020-06-01";
            Date date = DateUtil.parse(dateStr);
            Order order = new Order();
            order.setStatus(1);
            order.setODate(date);
            order.setOMoney(bigDecimal);
            order.setMId(mid);
            order.setIId(iid);

            orderDao.insert(order);
        }
    }


//    @Test
//    void test10(){
//        itemService.deleteItem(new Integer[]{1,2,3,4});
//    }



//
//      ;
//
//    }
//
//    @Test
//    void contextLoads5() {
//        JWT jwt = JWTUtils.getNewJwt("家长");
//        System.out.println(jwt.getHead());
//        System.out.println(jwt.getPlayLoad());
//        System.out.println(jwt.getSignal());
//        System.out.println(jwt.getHead() +"."+ jwt.getPlayLoad()+"."+jwt.getSignal());
//    }
//
//    @Test
//    void contextLoads6() {
//        String s = JWTUtils.toJWT("ewogICJ0eXBlIjogIkpXVCIsCiAgImFsZyI6ICJNRDUiCn0=.ewogICJ0eXBlIjogIuWutumVvyIsCiAgIkV4cGlyZWQiOjE1OTAzMDY1MjYzNjkKfQ==.b4cbd791db9e412f989974bd007b4f0f");
//
//    }
//
//    @Test
//    void contextLoads7() {
//        itemDao.selectByType("支出").forEach(h->{
//            System.out.println(h.getIName());
//        });
//
//    }
//    @Test
//    void contextLoads8() {
//        System.out.println(itemDao.selectName("yashi"));
//
//    }
}
