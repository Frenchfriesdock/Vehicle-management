package com.hosiky.car.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hosiky.car.domain.dto.CarRegisterDTO;
import com.hosiky.car.domain.dto.CarUpdateDTO;
import com.hosiky.car.domain.vo.CarDetailVO;
import com.hosiky.car.domain.vo.CarListVO;
import com.hosiky.car.mapper.CarImageMapper;
import com.hosiky.car.mapper.CarMapper;
import com.hosiky.car.service.ICarService;
import com.hosiky.common.client.MyMinioClient;
import com.hosiky.common.entity.Enum.CarStatusEnum;
import com.hosiky.common.entity.po.CarImages;
import com.hosiky.common.entity.po.Cars;
import com.hosiky.common.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CarServiceImpl extends ServiceImpl<CarMapper, Cars> implements ICarService {

    private final CarMapper carMapper;

    private MyMinioClient minioClient;

    private final CarImageMapper carImageMapper;

    @Override
    public void carRegister(CarRegisterDTO carRegisterDTO) {
        Cars car = new Cars();
        BeanUtils.copyProperties(carRegisterDTO, car);

        car.setStatus(CarStatusEnum.ONSALE);
        car.setCreatedAt(LocalDateTime.now());
        car.setUpdatedAt(LocalDateTime.now());
        this.save(car);
    }

    @Override
    public void updateCar(BigInteger id, CarUpdateDTO carUpdateDTO) {
        boolean update = lambdaUpdate()
                .eq(Cars::getId, id)
                .set(carUpdateDTO.getTitle() != null, Cars::getTitle, carUpdateDTO.getTitle())
                .set(carUpdateDTO.getBrand() != null, Cars::getBrand, carUpdateDTO.getBrand())
                .set(carUpdateDTO.getModel() != null, Cars::getModel, carUpdateDTO.getModel())
                .set(carUpdateDTO.getVin() != null, Cars::getVin, carUpdateDTO.getVin())
                .set(carUpdateDTO.getColor() != null, Cars::getColor, carUpdateDTO.getColor())
                .set(carUpdateDTO.getPrice() != null, Cars::getPrice, carUpdateDTO.getPrice())
                .set(carUpdateDTO.getFuelType() != null, Cars::getFuelType, carUpdateDTO.getFuelType())
                .set(carUpdateDTO.getSeats() != null, Cars::getSeats, carUpdateDTO.getSeats())
                .update();

        if(!update){
            throw new RuntimeException("更新失败");
        }
    }


    /**
     * TODO 先获取所有汽车 ID，然后一次性查询所有相关图片，再通过 Map 进行匹配：
     * @param current
     * @param size
     * @return
     */
    @Override
    public IPage<CarListVO> getAllCars(int current, int size) {
        Page<Cars> page = new Page<>(current, size);

        Page<Cars> carsPage = lambdaQuery()
                .select(Cars::getId, Cars::getSellerId, Cars::getTitle, Cars::getBrand,
                        Cars::getModel, Cars::getPrice)
                .page(page);

        Page<CarListVO> resultPage = new Page<>(
                carsPage.getCurrent(),
                carsPage.getSize(),
                carsPage.getTotal()
        );

//        装换对象为vo
        carsPage.getRecords().stream()
                .map(car ->{
                    CarListVO carListVO = new CarListVO();
                    BeanUtils.copyProperties(car, carListVO);

                    List<CarImages> images = carImageMapper.selectList(
                            new QueryWrapper<CarImages>().eq("car_id", car.getId())
                    );

                    if(!images.isEmpty()){
                        carListVO.setCoverImage(images.get(0).getUrl());
                    }

                    return carListVO;
                })
                .collect(Collectors.toList());

        resultPage.setRecords(resultPage.getRecords());

        return resultPage;
    }

    @Override
    public CarDetailVO getCarDetailMessage(BigInteger id) {
        Cars car = getById(id.longValue());
        if(car == null){
            return null;
        }
//         查询汽车图片列表
        List<String> imageUrls = carImageMapper.selectList(new QueryWrapper<CarImages>().eq("car_id", car.getId())
                        .orderByAsc("sort"))
                .stream()
                .map(CarImages::getUrl)
                .collect(Collectors.toList());

        CarDetailVO carDetailVO = new CarDetailVO();
        BeanUtils.copyProperties(car, carDetailVO);
        carDetailVO.setImages(imageUrls);
        return carDetailVO;
    }

    @Override
    public List<String> addImages(Long carId, MultipartFile[] files) {
//        检验汽车是否存在
        boolean exists = carMapper.exists(Wrappers.<Cars>lambdaQuery()
                .eq(Cars::getId, carId));
        if(!exists){
            throw new RuntimeException("汽车不存在");
        }

        ArrayList<String> urls = new ArrayList<>();
        for(int i = 0; i < files.length; i++){
            try {
                String url = minioClient.upload(files[i]);
                urls.add(url);

                CarImages carImages = new CarImages();
                carImages.setCarId(BigInteger.valueOf(carId));
                carImages.setUrl(url);
                carImages.setSort(i);
                carImages.setCreateAt(LocalDateTime.now());
                carImageMapper.insert(carImages);

            } catch (FileUploadException e) {
                throw new RuntimeException(e);
            }
        }
        return urls;
    }


}
