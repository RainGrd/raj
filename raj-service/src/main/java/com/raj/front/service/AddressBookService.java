package com.raj.front.service;

import com.raj.entity.front.AddressBook;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.math3.analysis.function.Add;

import java.util.List;

/**
 * @author RainGrd
 * @description 针对表【t_address_book(地址管理)】的数据库操作Service
 * @createDate 2022-11-23 18:03:09
 */
public interface AddressBookService extends IService<AddressBook> {

    int saveAddressBook(AddressBook addressBook);

    List<AddressBook> queryAddressBookList();

    AddressBook queryAddressBookById(Long id);

    int updateAddressBook(AddressBook addressBook);

    int deleteAddressBook(AddressBook addressBook);

    AddressBook queryAddressBookByDefault();

    int updateAddressBookDefault(AddressBook addressBook);
}
