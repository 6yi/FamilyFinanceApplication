package com.lzheng.familyfinance;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.lzheng.familyfinance.dao.ItemDao;
import com.lzheng.familyfinance.dao.MemberDao;
import com.lzheng.familyfinance.dao.OrderDao;
import com.lzheng.familyfinance.domain.Item;
import com.lzheng.familyfinance.domain.JWT;
import com.lzheng.familyfinance.domain.Member;
import com.lzheng.familyfinance.domain.Order;
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
    @Test
    void contextLoads() {
        Member member = new Member();
        member.setMName("刘正");
        member.setMUsername("haha");
        member.setMType("子女");
        member.setMPassword(md5.digestHex("lzheng.1"));
        member.setStatus(1);
        dao.insert(member);
    }
    @Test
    void contextLoads2() {
        Item item=new Item();
        item.setIName("娱乐");
        item.setIType("支出");
        item.setStatus(1);
        itemDao.insert(item);
    }
    @Test
    void contextLoads3() {
       Order order=new Order();
       order.setIId(1);
       order.setMId(2);
       order.setOMoney(new BigDecimal(13.5));
       order.setStatus(1);
       order.setODate(new Date());
       orderDao.insert(order);
    }
    //根据日期条件查询
    @Test
    void contextLoads4() {
        String dateStr = "2027-03-01";
        Date date = DateUtil.parse(dateStr);
        orderDao.selectByDate(date,new Date()).forEach(h->{
            System.out.println(h.getIName()+" : "+h.getMName());
        });
    }

    @Test
    void contextLoads5() {
        JWT jwt = JWTUtils.getNewJwt("家长");
        System.out.println(jwt.getHead());
        System.out.println(jwt.getPlayLoad());
        System.out.println(jwt.getSignal());
        System.out.println(jwt.getHead() +"."+ jwt.getPlayLoad()+"."+jwt.getSignal());
    }

    @Test
    void contextLoads6() {
        String s = JWTUtils.toJWT("ewogICJ0eXBlIjogIkpXVCIsCiAgImFsZyI6ICJNRDUiCn0=.ewogICJ0eXBlIjogIuWutumVvyIsCiAgIkV4cGlyZWQiOjE1OTAzMDY1MjYzNjkKfQ==.b4cbd791db9e412f989974bd007b4f0f");

    }

    @Test
    void contextLoads7() {
        itemDao.selectByType("支出").forEach(h->{
            System.out.println(h.getIName());
        });

    }

}
