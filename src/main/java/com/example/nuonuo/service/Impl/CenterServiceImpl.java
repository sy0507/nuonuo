package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.CarMapper;
import com.example.nuonuo.mapper.CenterCarMapper;
import com.example.nuonuo.mapper.CenterMapper;
import com.example.nuonuo.mapper.DistanceMapper;
import com.example.nuonuo.pojo.dto.CenterCarDTO;
import com.example.nuonuo.pojo.dto.CenterDTO;
import com.example.nuonuo.pojo.dto.DistanceDTO;
import com.example.nuonuo.pojo.entity.Car;
import com.example.nuonuo.pojo.entity.Center;
import com.example.nuonuo.pojo.entity.CenterCar;
import com.example.nuonuo.pojo.entity.Distance;
import com.example.nuonuo.pojo.vo.CarCenterVO;
import com.example.nuonuo.pojo.vo.CenterVO;
import com.example.nuonuo.service.CenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CenterServiceImpl implements CenterService {
    private final CenterMapper centerMapper;
    private final CenterCarMapper centerCarMapper;
    private final CarMapper carMapper;
    private final DistanceMapper distanceMapper;

    public CenterServiceImpl(CenterMapper centerMapper, CenterCarMapper centerCarMapper, CarMapper carMapper, DistanceMapper distanceMapper) {
        this.centerMapper = centerMapper;
        this.centerCarMapper = centerCarMapper;
        this.carMapper = carMapper;
        this.distanceMapper = distanceMapper;
    }

    @Override
    public void add(CenterDTO centerdto) {
        Center center=new Center();
        BeanUtils.copyProperties(centerdto,center);
        centerMapper.insertSelective(center);


    }

    @Override
    public List<CenterVO> getCenterInfoVo() throws IOException {
        List<CenterVO> centerVOList=new ArrayList<>();
        List<Center> centerList=centerMapper.selectAll();
        for (int i=0;i<centerList.size();i++){
            CenterVO centerVO=new CenterVO();
            BeanUtils.copyProperties(centerList.get(i),centerVO);
            centerVOList.add(centerVO);
        }
        outfile();
        return centerVOList;
    }

    @Override
    public void own(Integer id, CenterCarDTO centerCarDTO) {
        CenterCar centerCar=new CenterCar();
        centerCar.setCenterId(id);
        centerCar.setCarId(centerCarDTO.getCarId());
        Car car=carMapper.selectByPrimaryKey(centerCarDTO.getCarId());
        centerCar.setNum(centerCarDTO.getNum());
        centerCar.setWeight(car.getWeight());
        centerCarMapper.insertSelective(centerCar);

    }

    @Override
    public List<CarCenterVO> getOwnCarVo(Integer id) {
        List<CenterCar> centerCarList=centerCarMapper.getById(id);
        List<CarCenterVO> list=new ArrayList<>();
        for(CenterCar centerCar:centerCarList){
            CarCenterVO carCenterVO=new CarCenterVO();
            BeanUtils.copyProperties(centerCar,carCenterVO);
            Car car=carMapper.selectByPrimaryKey(centerCar.getCarId());
            carCenterVO.setWeight(car.getWeight());
            list.add(carCenterVO);
        }

        return list;
    }

    @Override
    public void distance() {

        //删除数据库distances
        distanceMapper.deleteAll();

        List<Center> centers = centerMapper.selectAll();
        List<Distance> distances = new ArrayList<>();



        Integer count = centers.size();
        for(int i=0;i<count;i++){
            for(int j=i+1;j<count;j++){
                Distance distance = new Distance();
                distance.setAid(centers.get(i).getCenterId());
                distance.setBid(centers.get(j).getCenterId());
                double ALon= centers.get(i).getLongitude()*(Math.PI/180);
                double ALan= centers.get(i).getLatitude()*(Math.PI/180);
                double BLon= centers.get(j).getLongitude()*(Math.PI/180);
                double BLan= centers.get(j).getLatitude()*(Math.PI/180);
                double earthR = 6378;
                double distence = Math.acos(Math.sin(ALan) * Math.sin(BLan)
                        + Math.cos(ALan) * Math.cos(BLan) * Math.cos(BLon - ALon)) * earthR;
                distance.setDistance(distence);
                distances.add(distance);
            }
        }

        //写入数据库
       for(Distance distance:distances){
           //System.out.println(distance.getAid()+"  "+distance.getBid());
           //很快乐第三方
           distanceMapper.insert(distance);
       }

    }

    public void outfile() throws IOException {
//        File file=null;
//        FileWriter fw=null;
//        file=new File("in.txt");
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        StringBuffer write;
        StringBuffer write1;
        StringBuffer write2;
        StringBuffer write3;
//        if(!file.exists()){
//                      file.createNewFile();
//                    }
//                fw = new FileWriter(file);
        outSTr=new FileOutputStream("in.txt");
        Buff = new BufferedOutputStream(outSTr);
        write=new StringBuffer();
        List<Center> list=centerMapper.selectAll();
        int count=list.size()-1;
        List<Distance> distanceList=distanceMapper.selectAll();
        int countd;
        countd = distanceList.size();
        List<CenterCar> centerCarList=centerCarMapper.selectAll();
        int countc=centerCarList.size();
        write.append(count);
        write.append(" ");
        write.append(countd);
        write.append(" ");
        write.append(countc);
        write.append("\r\n");
//        fw.write(count+" "+countd+" "+countc+"\r\n");
        Buff.write(write.toString().getBytes("UTF-8"));
        for (int i=0;i<list.size();i++){
            write1=new StringBuffer();
            write1.append(list.get(i).getNeed());
            write1.append("\r\n");
            Buff.write(write1.toString().getBytes("UTF-8"));
        }
        for (int a=0;a<distanceList.size();a++)
        {
            write2=new StringBuffer();
            write2.append(distanceList.get(a).getAid());
            write2.append(" ");
            write2.append(distanceList.get(a).getBid());
            write2.append(" ");
            write2.append(distanceList.get(a).getDistance());
            write2.append("\r\n");
            Buff.write(write2.toString().getBytes("UTF-8"));
        }
        for (int b=0;b<centerCarList.size();b++)
        {
            write3=new StringBuffer();
            write3.append(centerCarList.get(b).getNum());
            write3.append(" ");
            write3.append(centerCarList.get(b).getWeight());
            write3.append("\r\n");
            Buff.write(write3.toString().getBytes("UTF-8"));
        }

        Buff.flush();
        Buff.close();




    }
}
