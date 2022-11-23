package com.raj.front.mapper;

import com.raj.entity.front.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author RainGrd
* @description 针对表【t_address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-11-23 18:03:09
* @Entity com.raj.entity.front.AddressBook
*/
@Repository
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




