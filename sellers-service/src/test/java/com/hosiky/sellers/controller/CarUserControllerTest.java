//package com.hosiky.carUser.controller;
//
//import com.hosiky.carUser.service.ICarUserService;
//import org.aspectj.lang.annotation.After;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class CarUserControllerTest {
//
//    @Mock
//    CarUserController carUserController;
//
//    @Mock
//    private ICarUserService carUserService; // Mock 依赖项
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//
//    }
//
//
//    @Test
//    void add() {
//        //mock 打桩，就是不管真实的方法如何执行，我们可以自行假设该方法执行的结果
//        //后续的测试都是基于打桩结果来走
////         Mockito.when(carUserController.add(1,2)).thenReturn(4);
////         Assertions.assertEquals(3,carUserController.add(1,2));
//
//        //当测试方法出现异常，测试方法  如果有try{}catch{} 则可以测试异常是否正常
////        Mockito.when(carUserController.add(1,1)).thenThrow(new RuntimeException());
//
//        //调用真实的方法
//        Mockito.when(carUserController.add(1,1)).thenCallRealMethod();
//
////        验证测试的值和模拟的值是否正确
//        Assertions.assertEquals(2,carUserController.add(1,1));
//    }
//
//    @Test
//    void login() {
//        Mockito.when(carUserController.login()).thenCallRealMethod();
//        System.out.println(carUserController.login());
//    }
//
//    @Test
//    void test(){
//
//    }
//
//    @AfterEach
//    void after(){
//        System.out.println("after");
//    }
//}