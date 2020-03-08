package com.example.nuonuo.service.Impl;

import com.example.nuonuo.mapper.*;
import com.example.nuonuo.pojo.dto.CenterCarDTO;
import com.example.nuonuo.pojo.dto.CenterDTO;
import com.example.nuonuo.pojo.entity.*;
import com.example.nuonuo.pojo.vo.CarCenterVO;
import com.example.nuonuo.pojo.vo.CenterVO;
import com.example.nuonuo.pojo.vo.ResultVO;
import com.example.nuonuo.service.CenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CenterServiceImpl implements CenterService {
    private final CenterMapper centerMapper;
    private final CenterCarMapper centerCarMapper;
    private final CarMapper carMapper;
    private final DistanceMapper distanceMapper;
    private final ResultMapper resultMapper;
    private final PlaceMapper placeMapper;

    public CenterServiceImpl(CenterMapper centerMapper, CenterCarMapper centerCarMapper, CarMapper carMapper, DistanceMapper distanceMapper, ResultMapper resultMapper, PlaceMapper placeMapper) {
        this.centerMapper = centerMapper;
        this.centerCarMapper = centerCarMapper;
        this.carMapper = carMapper;
        this.distanceMapper = distanceMapper;
        this.resultMapper = resultMapper;
        this.placeMapper = placeMapper;
    }

    @Override
    public void add(CenterDTO centerdto) {
        Center center=new Center();
        BeanUtils.copyProperties(centerdto,center);
        center.setNeed("0");
        centerMapper.insertSelective(center);
        int id=center.getCenterId();
//        Optional<List<Integer>> carIds=Optional.ofNullable(centerdto.getCarId());
        Optional<List<Integer>> nums=Optional.ofNullable(centerdto.getNum());
        Optional<List<Double>> weights=Optional.ofNullable(centerdto.getWeight());
//        Optional<List<Car>> cars=Optional.ofNullable(centerdto.getClass(Car));
            nums.ifPresent(num->{
                weights.ifPresent(weight->{
                    for (int i=num.size()-1;i>=0;i--)
                    {
                        Integer nu=num.get(i);
                        Double we=weight.get(i);
                        CenterCar centerCar=new CenterCar();
                        centerCar.setCenterId(id);
                        centerCar.setNum(nu);
                        centerCar.setWeight(we);
                        centerCarMapper.insertSelective(centerCar);

                    }
                });

            });


    }

    @Override
    public List<CenterVO> getCenterInfoVo() throws IOException {

        List<CenterVO> centerVOList=new ArrayList<>();
        List<Center> centerList=centerMapper.selectAll();

        for (int i=0;i<centerList.size();i++){
            CenterVO centerVO=new CenterVO();
            BeanUtils.copyProperties(centerList.get(i),centerVO);
            List<Car> listCar = carMapper.selectByCenterId(centerVO.getCenterId());
            centerVO.setListCar(listCar);
            centerVOList.add(centerVO);
        }

        return centerVOList;
    }

    @Override
    public void own(Integer centerId, CenterCarDTO centerCarDTO) {
        CenterCar centerCar=new CenterCar();
        centerCar.setCenterId(centerId);
        centerCar.setNum(centerCarDTO.getNum());
        centerCar.setWeight(centerCarDTO.getWeight());
        centerCarMapper.insertSelective(centerCar);
    }

    @Override
    public List<CarCenterVO> getOwnCarVo(Integer centerId) {
        List<CenterCar> centerCarList=centerCarMapper.getById(centerId);
        List<CarCenterVO> list=new ArrayList<>();
        for(CenterCar centerCar:centerCarList){
            CarCenterVO carCenterVO=new CarCenterVO();
            BeanUtils.copyProperties(centerCar,carCenterVO);
            carCenterVO.setWeight(centerCar.getWeight());
            list.add(carCenterVO);
        }

        return list;
    }

    @Override
    public void distance(Integer centerId) {

        //删除数据库distances
        distanceMapper.deleteAll();

        List<Place> places = placeMapper.selectByCenterId(centerId);
        List<Distance> distances = new ArrayList<>();
        Center center=centerMapper.selectByPrimaryKey(centerId);

        Integer count = places.size();
        for (int k=0;k<count;k++)
        {
            Distance distance=new Distance();
            distance.setAid(0);
            distance.setBid(places.get(k).getPlaceId());
            double ALon= center.getLongitude()*(Math.PI/180);
            double ALan= center.getLatitude()*(Math.PI/180);
            double BLon= places.get(k).getLongitude()*(Math.PI/180);
            double BLan= places.get(k).getLatitude()*(Math.PI/180);
            double earthR = 6378;
            double distence = Math.acos(Math.sin(ALan) * Math.sin(BLan)
                    + Math.cos(ALan) * Math.cos(BLan) * Math.cos(BLon - ALon)) * earthR;
            distance.setDistance(distence);
            distances.add(distance);
        }



        for(int i=0;i<count;i++){
            for(int j=i+1;j<count;j++){
                Distance distance = new Distance();
                distance.setAid(places.get(i).getPlaceId());
                distance.setBid(places.get(j).getPlaceId());
                double ALon= places.get(i).getLongitude()*(Math.PI/180);
                double ALan= places.get(i).getLatitude()*(Math.PI/180);
                double BLon= places.get(j).getLongitude()*(Math.PI/180);
                double BLan= places.get(j).getLatitude()*(Math.PI/180);
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
           //为什么提交不上去我吐了
           distanceMapper.insert(distance);
       }

    }

    @Override
    public Object execute(Integer centerId,Integer lenth,Integer speed,Integer time) throws IOException, InterruptedException {
        outfile(centerId,lenth,speed,time);
//        String fileName = this.getClass().getClassLoader().getResource("A12.exe").getPath();
//        ClassPathResource resource = new ClassPathResource("A12.exe");
//        File sourceFile = resource.getFile();
//        InputStream fis = resource.getInputStream();
//        InputStream is = this.getClass().getResourceAsStream("/dict/fileName.txt");
//        InputStreamReader isr = new InputStreamReader(is);
//        BufferedReader br = new BufferedReader(isr);
//        String filePath  = createFile("A12.exe");
        String fileName = "A12.exe";
//        String filepath = getUploadResource(fileName);
//        System.out.println(filepath);
//        final Process proc = Runtime.getRuntime().exec("A12.exe");
        final Process proc = Runtime.getRuntime().exec("/ant.out");
        Thread.currentThread().sleep(1000);
         Pattern compile = Pattern.compile("\\d+");
         Pattern compile2 = Pattern.compile("\\d+.\\d+");
        int carNum;
        int skipLine = 2;
        int nowLine = 0;
        try{
            FileReader fr = new FileReader("out.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            while ((str = bf.readLine()) != null && nowLine <= skipLine){
                nowLine++;
            }
            carNum =matchNumberToInt(str);
            System.out.println("匹配到车辆数：" + carNum);
            int carType;
            double fullLoadRate;
            double mileage;
            String route;
            int count=0;
            while ((str = bf.readLine()) != null) {
                try{
                    String[] split = str.split(",");
                    carType = matchNumberToInt(split[0]);
                    fullLoadRate = matchNumberToDouble(split[1]);
                    mileage = matchNumberToDouble(split[2]);
                    route = split[3].split(":")[1].trim();
                    System.out.println("----------------------------");
                    System.out.println(str);
                    System.out.println("解析：类型：" + carType);
                    System.out.println("解析：满载率：" + fullLoadRate);
                    System.out.println("解析：里程数：" + mileage);
                    System.out.println("解析：行驶的路线：" + route);
                    Result result=new Result();
                    result.setCarId(carType);
                    result.setFullLoadRate(fullLoadRate);
                    result.setMileage(mileage);
                    result.setRoute(route);
                    resultMapper.insertSelective(result);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("解析出错");
                }
            }
            bf.close();
            fr.close();
            List<ResultVO> resultVOList=new ArrayList<>();
            List<Result> resultList=resultMapper.selectAll();
            for (int i=resultList.size()-1;i>=resultList.size()-carNum;i--)
            {
                ResultVO resultVO=new ResultVO();
                BeanUtils.copyProperties(resultList.get(i),resultVO);
                resultVOList.add(resultVO);
            }
            return resultVOList;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;



    }

    @Override
    public Object modify(Integer id, CenterDTO centerDTO) {
        Center center=centerMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(centerDTO,center);
        centerMapper.updateByPrimaryKeySelective(center);
        return null;
    }

    @Override
    public Object getInfo(Integer id) {
        CenterVO centerVO=new CenterVO();
        Center center=centerMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(center,centerVO);
        return centerVO;
    }

    @Override
    public Object modifycar(Integer carId,Integer centerId, CenterCarDTO centerCarDTO) {
        CenterCar centerCar=new CenterCar();
        centerCar.setCarId(carId);
        centerCar.setCenterId(centerId);
        BeanUtils.copyProperties(centerCarDTO,centerCar);
        centerCarMapper.updateByPrimaryKeySelective(centerCar);
        return null;
    }

    @Override
    public void delete(Integer carId,Integer centerId) {
        CenterCar centerCar=new CenterCar();
        centerCar.setCarId(carId);
        centerCar.setCenterId(centerId);
        centerCarMapper.delete(centerCar);

    }

    @Override
    public void deletecenter(Integer id) {
        centerMapper.deleteByPrimaryKey(id);
    }


    static int matchNumberToInt(String str){
        Pattern compile = Pattern.compile("\\d+");
        int result = 0;
        Matcher matcher = compile.matcher(str);
        if (matcher.find()){
            String numString = matcher.group();
            result = Integer.parseInt(numString);
        }
        return result;
    }
    static double matchNumberToDouble(String str){
        Pattern compile2 = Pattern.compile("\\d+.\\d+");
        double result = 0;
        Matcher matcher = compile2.matcher(str);
        if (matcher.find()){
            String numString = matcher.group();
            result = Double.parseDouble(numString);
        }
        return result;
    }

    public void outfile(Integer centerId,Integer lenth,Integer speed,Integer time) throws IOException {
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
        List<Place> list=placeMapper.selectByCenterId(centerId);
        int count=list.size();
        List<Distance> distanceList=distanceMapper.selectAll();
        int countd;
        countd = distanceList.size();
        List<CenterCar> centerCarList=centerCarMapper.getById(centerId);
        int countc=centerCarList.size();
        write.append(count);
        write.append(" ");
        write.append(countd);
        write.append(" ");
        write.append(countc);
        write.append(" ");
        write.append(lenth);
        write.append(" ");
        write.append(speed);
        write.append(" ");
        write.append(time);
        write.append("\r\n");
        write.append(0);
        write.append("\r\n");
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
    public String getUploadResource(String fileName) {
        //返回读取指定资源的输入流
        InputStream is = this.getClass().getResourceAsStream("/dict/" + fileName);

        //若文件已存在，则返回的filePath中含有"EXIST"，则不需再重写文件
        String filePath  = createFile(fileName);

        //文件不存在，则创建流输入默认数据到新文件
        if (!filePath.contains("EXIST")) {
            File file = new File(filePath);
            inputStreamToFile(is, file);
            return filePath;
        }
        return filePath.substring(5);
    }


    public String createFile(String filename) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        //create folder
        String dirPath = path + File.separator + "uploadFiles";
        File dir = new File(dirPath);
        dir.mkdirs();

        //create file
        String filePath = dirPath + File.separator + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filePath;
        }
        return "EXIST" + filePath;
    }

    public void inputStreamToFile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
