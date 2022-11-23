package com.raj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.constants.CommonEnum;
import com.raj.entity.front.AddressBook;
import com.raj.entity.front.User;
import com.raj.front.service.AddressBookService;
import com.raj.front.mapper.AddressBookMapper;
import com.raj.holder.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author RainGrd
 * @description 针对表【t_address_book(地址管理)】的数据库操作Service实现
 * @createDate 2022-11-23 18:03:09
 */
@Service
@Slf4j
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
        implements AddressBookService {

    @Resource
    private AddressBookMapper addressBookMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int saveAddressBook(AddressBook addressBook) {
        //封装用户Id
        Long userId = UserHolder.getUser().getId();
        log.info("用户ID为:{}", userId);
        addressBook.setUserId(userId);
        return addressBookMapper.insert(addressBook);
    }

    @Override
    public List<AddressBook> queryAddressBookList() {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件:当前用户Id
        User user = UserHolder.getUser();
        addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, user.getId());
        //最先修改的地址为排序条件
        addressBookLambdaQueryWrapper.orderByAsc(AddressBook::getUpdateTime);
        return addressBookMapper.selectList(addressBookLambdaQueryWrapper);
    }
}




