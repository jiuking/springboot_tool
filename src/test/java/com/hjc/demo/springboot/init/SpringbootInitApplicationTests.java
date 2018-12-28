package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.entity.FetchCustBaseInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootInitApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        long start = System.currentTimeMillis();
        for (int i = 1000; i <1003 ; i++) {
            FetchCustBaseInfoDto f = new FetchCustBaseInfoDto();
            f.setName("张三"+i);
            f.setId("12345678123333sadfdfasdss21212"+i);
            f.setAge("12");
            f.setCity("23");
            f.setClientId("12345678123333sadfdfasdss");
            f.setClientPhoneOne("12345678911");
            f.setClientPhoneTwo("23456789012");
            f.setCompAddr("四川省成都市天府新区阿萨撒的发生大发123号");
            f.setCompCity("时长asd");
            f.setCompDictCountry("四川");
            f.setCompPhone("as12345678");
            f.setCompPhone("asd12");
            f.setCreateTime(new Date());
            f.setJob("薪资");
            f.setMonIncomeMax("2323.12");
            f.setMonIncomeMin("2311.9");
            f.setSex("1");
            f.setIdCard("1234567ABC"+i);

            List<FetchCustBaseInfoDto.ContactInfoDto> list1 = new ArrayList();
            FetchCustBaseInfoDto.ContactInfoDto contactInfoDto = new FetchCustBaseInfoDto.ContactInfoDto();
            contactInfoDto.setContactTel("986");
            contactInfoDto.setContactMobile("3455555534334");
            list1.add(contactInfoDto);
            f.setContactInfoLists(list1);

            List<FetchCustBaseInfoDto.LoanInfoDto> list2 = new ArrayList<>();
            FetchCustBaseInfoDto.LoanInfoDto loanInfoDto = new FetchCustBaseInfoDto.LoanInfoDto();
            loanInfoDto.setActuAccountAmt("123");
            loanInfoDto.setCompCategory("232");
            loanInfoDto.setContractDate(new Date());
            loanInfoDto.setContractNo("323232322323asfasfd"+i);
            loanInfoDto.setLoanFinalAmt("12434.3");
            loanInfoDto.setMonthRepayAmt("asdfsdaf");
            loanInfoDto.setProduct("232"+i);
            loanInfoDto.setServiceLine("XD");
            loanInfoDto.setSuccessDate("2019-10-10");
            loanInfoDto.setIaSettleAmt("2332");
            list2.add(loanInfoDto);
            f.setLoanInfoLists(list2);


            redisTemplate.opsForSet().add("asdf1", f);

        }
        System.out.println(System.currentTimeMillis() - start);
        redisTemplate.opsForSet().members("asdf").forEach(System.out::println);
    }

    @Test
    public void testRedisSet() {
        FetchCustBaseInfoDto f1 = new FetchCustBaseInfoDto();
        f1.setName("张三");
        f1.setIdCard("12345678123333sadfdfasdss21212");
        System.out.println("f1 hashCode:" + f1.hashCode());
        FetchCustBaseInfoDto f2 = new FetchCustBaseInfoDto();
        f2.setName("李四");
        f2.setIdCard("12345678123333sadfdfasdss21212");

        System.out.println("f2 hashCode:" + f2.hashCode());

        FetchCustBaseInfoDto f3 = new FetchCustBaseInfoDto();
        f3.setName("王五");
        f3.setIdCard("12345678123333sadfdfasdss21212");
//        redisTemplate.opsForSet().add("asdf1", f1);
//        redisTemplate.opsForSet().add("asdf1", f2);
//        redisTemplate.opsForSet().add("asdf1", f3);
//
//        redisTemplate.opsForSet().add("asdf2", f1);
//        redisTemplate.opsForSet().add("asdf2", f2);

//        redisTemplate.opsForSet().pop("asdf1",4).forEach(System.out::println);
//        List<FetchCustBaseInfoDto> result = redisTemplate.opsForSet().pop("asdf1", 4);
        redisTemplate.executePipelined((RedisCallback)(redisConnection) ->{
                redisTemplate.opsForSet().add("asdf3", f1);
                redisTemplate.opsForSet().add("asdf3", f3);
                return null;
        });
//        List<FetchCustBaseInfoDto> list = redisTemplate.execute(new RedisCallback<List<FetchCustBaseInfoDto>>() {
//            @Override
//            public List<FetchCustBaseInfoDto> doInRedis(RedisConnection redisConnection) throws DataAccessException {
////                System.out.println(redisTemplate.opsForSet().size("asdf3"));
//                List<FetchCustBaseInfoDto> result = redisTemplate.opsForSet().pop("asdf3",2);
//                return result;
//            }
//        });
        List<FetchCustBaseInfoDto> obj = (List<FetchCustBaseInfoDto>) redisTemplate.execute((RedisCallback<List<FetchCustBaseInfoDto>>)(redisConnection) -> {
            redisConnection.openPipeline();
            System.out.println(redisTemplate.opsForSet().size("asdf3"));
            List<FetchCustBaseInfoDto> result = redisTemplate.opsForSet().pop("asdf3",2);
            redisConnection.closePipeline();
            return result;
        });
        obj.forEach(System.out::println);
    }

    @Test
    public void testReidsPiple() {
        long start = System.currentTimeMillis();
        redisTemplate.executePipelined((RedisCallback)(redisConnection) ->{
            for (int i = 0; i <10000 ; i++) {
                FetchCustBaseInfoDto f = new FetchCustBaseInfoDto();
                f.setName("张三"+i);
                f.setId("12345678123333sadfdfasdss21212"+i);
                f.setAge("12");
                f.setCity("23");
                f.setClientId("12345678123333sadfdfasdss");
                f.setClientPhoneOne("12345678911");
                f.setClientPhoneTwo("23456789012");
                f.setCompAddr("四川省成都市天府新区阿萨撒的发生大发123号");
                f.setCompCity("时长asd");
                f.setCompDictCountry("四川");
                f.setCompPhone("as12345678");
                f.setCompPhone("asd12");
                f.setCreateTime(new Date());
                f.setJob("薪资");
                f.setMonIncomeMax("2323.12");
                f.setMonIncomeMin("2311.9");
                f.setSex("1");
                f.setIdCard("1234567ABC");

                List<FetchCustBaseInfoDto.ContactInfoDto> list1 = new ArrayList();
                FetchCustBaseInfoDto.ContactInfoDto contactInfoDto = new FetchCustBaseInfoDto.ContactInfoDto();
                contactInfoDto.setContactTel("986");
                contactInfoDto.setContactMobile("3455555534334");
                list1.add(contactInfoDto);
                f.setContactInfoLists(list1);

                List<FetchCustBaseInfoDto.LoanInfoDto> list2 = new ArrayList<>();
                FetchCustBaseInfoDto.LoanInfoDto loanInfoDto = new FetchCustBaseInfoDto.LoanInfoDto();
                loanInfoDto.setActuAccountAmt("123");
                loanInfoDto.setCompCategory("232");
                loanInfoDto.setContractDate(new Date());
                loanInfoDto.setContractNo("323232322323asfasfd"+i);
                loanInfoDto.setLoanFinalAmt("12434.3");
                loanInfoDto.setMonthRepayAmt("asdfsdaf");
                loanInfoDto.setProduct("232"+i);
                loanInfoDto.setServiceLine("XD");
                loanInfoDto.setSuccessDate("2019-10-10");
                loanInfoDto.setIaSettleAmt("2332");
                list2.add(loanInfoDto);
                f.setLoanInfoLists(list2);
                redisTemplate.opsForValue().increment("pip", 1);
//                redisTemplate.opsForSet().add("asdf", f);

            }
            return null;
        });
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    redisTemplate.opsForValue().increment("pip", 1L);
                }
                return null;
            }
        });
        System.out.println(System.currentTimeMillis() - start1);

    }

    @Test
    public void testRedisInster() {
        Set<FetchCustBaseInfoDto> re = redisTemplate.opsForSet().intersect("asdf", "asdf1");
        System.out.println(re.size());
    }
}

