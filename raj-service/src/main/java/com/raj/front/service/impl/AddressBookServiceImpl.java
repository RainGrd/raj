package com.raj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
        addressBookLambdaQueryWrapper.orderByDesc(AddressBook::getUpdateTime);
        return addressBookMapper.selectList(addressBookLambdaQueryWrapper);
    }

    @Override
    public AddressBook queryAddressBookById(Long id) {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getId, id);
        return addressBookMapper.selectOne(addressBookLambdaQueryWrapper);
    }

    @Override
    public int updateAddressBook(AddressBook addressBook) {
        return addressBookMapper.updateById(addressBook);
    }

    @Override
    public int deleteAddressBook(AddressBook addressBook) {
        return addressBookMapper.deleteById(addressBook);
    }

    @Override
    public AddressBook queryAddressBookByDefault() {
        Long userId = UserHolder.getUser().getId();
        log.info("当前用户id:{}", userId);
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件:当前用户id,is_default为1
        addressBookLambdaQueryWrapper.eq(AddressBook::getUserId, userId).eq(AddressBook::getIsDefault, Integer.valueOf(CommonEnum.ADDRESS_BOOK_IS_DEFAULT_YES.getValue()));
        return addressBookMapper.selectOne(addressBookLambdaQueryWrapper);
    }

    @Override
    @Transactional
    public int updateAddressBookDefault(AddressBook addressBook) {
        LambdaUpdateWrapper<AddressBook> addressBookLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        Long userId = UserHolder.getUser().getId();
        //1.将其他地址的默认值修改为0
        //2.根据用户id先修改地址
        addressBookLambdaUpdateWrapper.eq(AddressBook::getUserId, userId);
        addressBookLambdaUpdateWrapper.set(AddressBook::getIsDefault, Integer.valueOf(CommonEnum.ADDRESS_BOOK_IS_DEFAULT_NO.getValue()));
        this.update(addressBookLambdaUpdateWrapper);
        //将当前用户的要设置外卖地址,重新设置为默认地址
        addressBook.setIsDefault(Integer.valueOf(CommonEnum.ADDRESS_BOOK_IS_DEFAULT_YES.getValue()));
        // 设置此地址为当前用户的默认地址
        return addressBookMapper.updateById(addressBook);
    }
}




