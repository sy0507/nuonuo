package com.example.nuonuo.service.Impl;

import com.example.nuonuo.exception.CommonException;
import com.example.nuonuo.exception.UserLoginIncorrectException;
import com.example.nuonuo.mapper.ResetMailCodeMapper;
import com.example.nuonuo.mapper.UserMapper;
import com.example.nuonuo.pojo.dto.PutUserProfileDTO;
import com.example.nuonuo.pojo.dto.UserLoginDTO;
import com.example.nuonuo.pojo.dto.UserRegisterDTO;
import com.example.nuonuo.pojo.dto.UserResetPasswordDTO;
import com.example.nuonuo.pojo.entity.ResetMailCode;
import com.example.nuonuo.pojo.entity.User;
import com.example.nuonuo.pojo.vo.ActivityVO;
import com.example.nuonuo.pojo.vo.UserInfoVO;
import com.example.nuonuo.service.FileService;
import com.example.nuonuo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  private final Random random;
  private final ResetMailCodeMapper resetMailCodeMapper;
  private final FileService fileService;

  public UserServiceImpl(UserMapper userMapper, Random random, ResetMailCodeMapper resetMailCodeMapper, FileService fileService) {
    this.userMapper = userMapper;

    this.random = random;
    this.resetMailCodeMapper = resetMailCodeMapper;
    this.fileService = fileService;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void register(UserRegisterDTO dto) {
    if (userExistsByPhone(dto.getPhone())) {
      throw new CommonException("手机号已注册");

    }
    if (userExistsByEMail(dto.getEmail())) {
      throw  new CommonException("邮箱已注册");
      
    }

    User user=new User();
    BeanUtils.copyProperties(dto,user);

    //密码加密
    user.setPassword(encryptPassword(dto.getPassword()));

    user.setAccessToken(generateToken(random.nextInt(65535),System.currentTimeMillis()));

    userMapper.insertSelective(user);


  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserInfoVO login(UserLoginDTO dto) {

    // 根据手机号在数据库中查找用户
    User exampleUser = new User();
    exampleUser.setPhone(dto.getPhone());
    User user = userMapper.selectOne(exampleUser);

    //没有查到用户，则说明手机号为注册
    if(user == null){
      throw new CommonException("手机号未注册");
    }

    //比对手机登录密码
    if(!Objects.equals(user.getPassword(),encryptPassword(dto.getPassword()))){
      throw new CommonException("账号密码不正确");
    }

    //登陆成功则返回token
    updateToken(user);

    //返回用户信息
    return getUserInfoVO(user);
  }

  @Override
  public void modifyPassword(String oldPassword, String newPassword, User user) {

    if (!Objects.equals(user.getPassword(), encryptPassword(oldPassword))) {
      throw new UserLoginIncorrectException("原密码不正确", user.getPhone(), user.getPassword());
    }

    // 原密码正确，修改数据库
    User exampleUser = new User();
    exampleUser.setUid(user.getUid());
    exampleUser.setPassword(encryptPassword(newPassword));
    userMapper.updateByPrimaryKeySelective(exampleUser);

  }


  @Override
  @Transactional(rollbackFor = Exception.class)
  public void resetPassword(UserResetPasswordDTO dto) {

    ResetMailCode resetMailCode = resetMailCodeMapper.findByEmail(dto.getEmail());
    if (Objects.equals(dto.getCode(),resetMailCode.getCode())){
      User user = new User();
      user.setUid(resetMailCode.getUid());
      user.setPassword(encryptPassword(dto.getPassword()));
      userMapper.updateByPrimaryKeySelective(user);
    }
  }



  @Override
  public Object updateProfile(Integer uid, PutUserProfileDTO userProfileDTO) {
    User record = new User();
    record.setUid(uid);
    BeanUtils.copyProperties(userProfileDTO, record);
    // 赋值好do对象，对数据库更新
    userMapper.updateByPrimaryKeySelective(record);
    // TODO: 2018/10/9 后续补全用户的个人信息视图对象
    return null;
  }

  @Override
  public UserInfoVO getUserInfoVO(User user) {
    UserInfoVO userInfoVO = new UserInfoVO();
    BeanUtils.copyProperties(user, userInfoVO);
    // 获取头像地址
    // TODO: 2018/11/11 可能使用连表查询效率会更高一点
    userInfoVO.setHeadPicUrl(fileService.getUrlById(user.getHeadPicId()));
    return userInfoVO;
  }

  @Override
  public void collectActivity(Integer activityId, User user) {

  }

  @Override
  public List<ActivityVO> acGetList(User user) {
    return null;
  }

  @Override
  public User queryUserByToken(String token) {
    User exampleUser = new User();
    exampleUser.setAccessToken(token);
    return userMapper.selectOne(exampleUser);
  }


  /**
   * 判断手机号是否注册过
   */
  public boolean userExistsByPhone(String phone) {
    User exampleUser = new User();
    exampleUser.setPhone(phone);

    if (userMapper.selectOne(exampleUser) != null) {
      return true;
    }
    else return false;
  }

  /**
   * 判断邮箱是否注册过
   * @param Email
   * @return
   */
  private boolean userExistsByEMail(String Email){
    User exampleUser = new User();
    exampleUser.setEmail(Email);
    if (userMapper.selectOne(exampleUser) != null) {
      return true;
    }
    else return false;
  }

  /**
   * 加密用户密码
   *
   * @param source 原密码
   * @return 加密后的密码
   */
  private String encryptPassword(String source) {
    return DigestUtils.sha256Hex(source);
  }

  /**
   *
   * @param uid
   * @param time 时间戳，建议用当地时间不易发生冲突
   * @return 生成的token
   */

  private String generateToken(Integer uid,long time){
    return DigestUtils.md5Hex(uid+"-"+time);
  }

  public void updateToken(User user) {

    User exampleUser = new User();

    String accessToken;
    // 必须保证token的唯一性
    do {
      accessToken = generateToken(user.getUid(), System.currentTimeMillis());
      exampleUser.setAccessToken(accessToken);
    } while (userMapper.selectOne(exampleUser) != null);

    exampleUser.setUid(user.getUid());
    userMapper.updateByPrimaryKeySelective(exampleUser);
    user.setAccessToken(accessToken);
  }
}
