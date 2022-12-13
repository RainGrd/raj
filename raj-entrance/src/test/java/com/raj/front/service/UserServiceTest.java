package com.raj.front.service;

import cn.hutool.core.lang.UUID;
import com.raj.entity.front.User;
import com.raj.service.front.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UserServiceTest
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/11/21 20:05
 * @Author RainGrd
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void getEmailCodeTest() {
        long startTime = System.currentTimeMillis();
        User user = new User();
        user.setEmail("duanronggui1224@163.com");
        userService.getEmailCode(user);
        long overTime = System.currentTimeMillis();
        System.out.println(overTime - startTime);
    }

    @Test
    void main() {
        int[] arr = {3,0,6,4,1,3,7,8,5,9};
        sort(arr,0,arr.length-1);
        print(arr);
    }
    public static void sort(int[] arr,int left,int right) {
        if (left == right) {
            return;
        }
        //分成两半
        int mid = left +(right-left)/2;
        //左边排序
        sort(arr, left, mid);
        //右边排序
        sort(arr, mid +1, right);

        merge(arr, left, mid+1, right);
    }
    //leftPtr 指数组最左边
    //rightPtr 指数组中间
    //rightBound 数组最右边

    static void merge(int[] arr,int leftPtr,int rightPtr,int rightBound) {
        int mid = rightPtr - 1;
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        // 将右边剩余的归并
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //将左边剩余的归并
        while (j <= rightBound) {
            temp[k++] = arr[j++];

        }

        System.arraycopy(temp, 0, arr, leftPtr + 0, temp.length);
    }
    //排序
    static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //打印
    static void print(int[] arr) {
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

    @Test
    void saveUserTest() {
        User user = new User();
        user.setName("RainGrd");
        user.setPhone("15873461176");
        user.setSex(0);
        user.setIdNumber("430481200512240371");
        user.setAvatar("");
        user.setStatus(1);
        user.setEmail("duanronggui1224@163.com");
        userService.saveUser(user);
    }

    @Test
    void loginTest() {
        Map<String, String> map = new HashMap<>();
        map.put("email", "duanronggui1224@163.com");
        map.put("code", "f2e01d");
        System.out.println(userService.login(map));
    }


    @Test
    void uuidTest() {
        System.out.println(UUID.randomUUID());
    }
}
