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

    /**
     * 根据id查询收货地址
     *
     * @param id
     * @return
     */
    @GetMapping("/queryAddressBookById.do/{id}")
    public Object queryAddressBookById(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.queryAddressBookById(id);
        if (addressBook == null) {
            return Result.error("你要查询的收货地址为空");
        }
        return Result.success(addressBook);
    }

    /**
     * 修改当前用户的默认地址
     */
    @PutMapping("/default/updateAddressBookDefault.do")
    public Object updateAddressBookDefault(@RequestBody AddressBook addressBook) {
        int i = addressBookService.updateAddressBookDefault(addressBook);
        if (i <= 0) {
            return Result.error("设置默认地址失败！");
        }
        return Result.success("设置成功!");
    }

    /**
     * 获取当前用户的默认地址
     *
     * @return
     */
    @GetMapping("/default/queryAddressBookByDefault.do")
    public Object queryAddressBookByDefault() {
        AddressBook addressBook = addressBookService.queryAddressBookByDefault();
        if (addressBook == null) {
            return Result.error("查询默认地址失败!");
        }
        return Result.success(addressBook);
    }

    /**
     * 修改用户的外卖地址
     *
     * @param addressBook
     * @return
     */
    @PutMapping("/updateAddressBook.do")
    public Object updateAddressBook(@RequestBody AddressBook addressBook) {
        //修改地址
        int i = addressBookService.updateAddressBook(addressBook);
        if (i <= 0) {
            return Result.error("修改失败！");
        }
        return Result.success();
    }

    @DeleteMapping("/deleteAddressBook.do")
    public Object deleteAddressBook(@RequestBody AddressBook addressBook) {
        //删除地址
        int i = addressBookService.deleteAddressBook(addressBook);
        if (i <= 0) {
            return Result.error("删除失败！");
        }
        return Result.success();
    }
}