package com.raj.front.service;

import com.raj.entity.front.AddressBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author RainGrd
* @description 针对表【t_address_book(地址管理)】的数据库操作Service
* @createDate 2022-11-23 18:03:09
*/
public interface AddressBookService extends IService<AddressBook> {

    int saveAddressBook(AddressBook addressBook);

    List<AddressBook> queryAddressBookList();
}
