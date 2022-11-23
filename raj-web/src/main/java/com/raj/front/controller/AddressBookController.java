package com.raj.front.controller;

import com.raj.Vo.Result;
import com.raj.entity.front.AddressBook;
import com.raj.front.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Classname AddressBookController
 * @Description 用户填充的外卖地址
 * @Version 1.0.0
 * @Date 2022/11/23 18:03
 * @Author RainGrd
 */
@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {

    @Resource
    private AddressBookService addressBookService;

    /**
     * 保存地址
     *
     * @param addressBook 地址对象
     * @return
     */
    @PostMapping("/saveAddressBook.do")
    public Object saveAddressBook(@RequestBody AddressBook addressBook) {
        log.info("新增的外卖地址对象:{}", addressBook);
        //保存地址
        int i = addressBookService.saveAddressBook(addressBook);
        return Result.success();
    }

    /**
     * 查询当前用户的全部地址
     *
     * @return
     */
    @GetMapping("/queryAddressBookList.do")
    public Object queryAddressBookList() {
        List<AddressBook> addressBooks = addressBookService.queryAddressBookList();
        log.info("当前用户的全部地址:{}", addressBooks);
        return Result.success(addressBooks);
    }



}
