package com.example.nuonuo.service.Impl;

import com.example.nuonuo.exception.CommonException;
import com.example.nuonuo.exception.UserLoginIncorrectException;
import com.example.nuonuo.mapper.*;
import com.example.nuonuo.pojo.dto.*;
import com.example.nuonuo.pojo.entity.*;
import com.example.nuonuo.pojo.vo.*;
import com.example.nuonuo.service.AudioService;
import com.example.nuonuo.service.FileService;
import com.example.nuonuo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
  private final UserMapper userMapper;
  private final Random random;
  private final ResetMailCodeMapper resetMailCodeMapper;
  private final FileService fileService;
  private final CollectInfoMapper collectInfoMapper;
  private final AudioMapper audioMapper;
  private final GoodInfoMapper goodInfoMapper;
  private final FollowMapper followMapper;
  private final AudioService audioService;
  private final SuspendMapper suspendMapper;
  private final AuditMapper auditMapper;

  public UserServiceImpl(UserMapper userMapper, Random random, ResetMailCodeMapper resetMailCodeMapper, FileService fileService, CollectInfoMapper collectInfoMapper, AudioMapper audioMapper, GoodInfoMapper goodInfoMapper, FollowMapper followMapper, AudioService audioService, SuspendMapper suspendMapper, AuditMapper auditMapper) {
    this.userMapper = userMapper;

    this.random = random;
    this.resetMailCodeMapper = resetMailCodeMapper;
    this.fileService = fileService;
    this.collectInfoMapper = collectInfoMapper;
    this.audioMapper = audioMapper;
    this.goodInfoMapper = goodInfoMapper;
    this.followMapper = followMapper;
    this.audioService = audioService;
    this.suspendMapper = suspendMapper;
    this.auditMapper = auditMapper;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void register(UserRegisterDTO dto) {
    if (userExistsByUname(dto.getUname())) {
      throw new CommonException("用户名已注册");

    }
    if (dto.getPassword().equals(dto.getRePassword())) {

      User user = new User();
      BeanUtils.copyProperties(dto, user);
      user.setName(dto.getUname());
      user.setHeadPicId(16);
      user.setFishNum(0);
      user.setSecondAvatarId(16);
      user.setPersonalAvatarId(20);
      user.setLevel(0);
      user.setAudioNum(0);
      Date date=new Date();
      Long dateTime=date.getTime();
      user.setRegisterTime(dateTime);


      //密码加密
      user.setPassword(encryptPassword(dto.getPassword()));

      user.setAccessToken(generateToken(random.nextInt(65535), System.currentTimeMillis()));

      userMapper.insertSelective(user);
    }
    else {
      throw new CommonException("前后两次密码不一致");
    }


  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserInfoVO login(UserLoginDTO dto) {

//    User exampleUser = new User();
//    exampleUser.setUname(dto.getUname());
//    exampleUser.setSuspend(true);
    User user = userMapper.selectByUname(dto.getUname());
//    System.out.println(user);

    if(user == null){
      throw new CommonException("用户未注册");
    }

    //比对手机登录密码
    if(!Objects.equals(user.getPassword(),encryptPassword(dto.getPassword()))){
      throw new CommonException("账号密码不正确");
    }
    System.out.println(user.isSuspend());
    if (user.isSuspend()){
      Suspend suspend=suspendMapper.getInfo(user.getUid());
      throw new CommonException("该用户因为"+suspend.getSuspendReason()+","+"被封禁至"+suspend.getSuspendEndTime());
    }
    Date date=new Date();
    Long dateTime=date.getTime();
    user.setLastLoginTime(dateTime);
    userMapper.updateByPrimaryKeySelective(user);

    //登陆成功则返回token
    updateToken(user);

    //返回用户信息
    return getUserInfoVO(user.getUid());
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
  public UserInfoVO getUserInfoVO(Integer id) {
    UserInfoVO userInfoVO = new UserInfoVO();
    User user=new User();
    user.setUid(id);
    User user1=userMapper.selectOne(user);
    BeanUtils.copyProperties(user1, userInfoVO);
    userInfoVO.setHeadPicUrl(fileService.getUrlById(userInfoVO.getHeadPicId()));
    userInfoVO.setSecondAvatarUrl(fileService.getUrlById(userInfoVO.getSecondAvatarId()));
    userInfoVO.setPersonalAvatarUrl(fileService.getUrlById(userInfoVO.getPersonalAvatarId()));
    return userInfoVO;
  }


  @Override
  public User queryUserByToken(String token) {
    User exampleUser = new User();
    exampleUser.setAccessToken(token);
    return userMapper.selectOne(exampleUser);
  }

    @Override
    public void collectAudio(Integer audioId, Integer uid) {
      CollectInfo collectInfo=new CollectInfo();
      collectInfo.setAudioId(audioId);
      collectInfo.setUid(uid);
      User user=userMapper.selectByPrimaryKey(uid);
      Audio audio=audioMapper.selectByPrimaryKey(audioId);
      if (collectInfoMapper.selectOne(collectInfo)!=null){
        collectInfoMapper.delete(collectInfo);
        audio.setCollectNumber(audio.getCollectNumber()-1);
        user.setFavor(user.getFavor()-1);
      }
      else {
        collectInfoMapper.insertSelective(collectInfo);
        audio.setCollectNumber(audio.getCollectNumber()+1);
        user.setFavor(user.getFavor()+1);
      }
      audioMapper.updateByPrimaryKeySelective(audio);
      userMapper.updateByPrimaryKeySelective(user);
    }

  @Override
  public void goodAudio(Integer audioId,Integer uid) {
    GoodInfo goodInfo=new GoodInfo();
    goodInfo.setAudioId(audioId);
    goodInfo.setUid(uid);

    Audio audio=audioMapper.selectByPrimaryKey(audioId);
    if (goodInfoMapper.selectOne(goodInfo)!=null){
      goodInfoMapper.delete(goodInfo);
      audio.setGoodNumber(audio.getGoodNumber()-1);
    }
    else {
      goodInfoMapper.insertSelective(goodInfo);
      audio.setGoodNumber(audio.getGoodNumber()+1);
    }
    audioMapper.updateByPrimaryKeySelective(audio);

  }

  @Override
  public void watchAudio(Integer audioId) {
    Audio audio=audioMapper.selectByPrimaryKey(audioId);
    audio.setWatchNumber(audio.getWatchNumber()+1);
    audioMapper.updateByPrimaryKeySelective(audio);
  }

  @Override
  public void follow(Integer id, Integer uid) {
    Follow follow=new Follow();
    follow.setUid(uid);
    follow.setFollowId(id);
    User user=userMapper.selectByPrimaryKey(uid);
    User user1=userMapper.selectByPrimaryKey(id);
    if (followMapper.selectOne(follow)!=null){
      followMapper.delete(follow);
      user.setFollows(user.getFollows()-1);
      user1.setFans(user1.getFans()-1);
    }
    else {
      followMapper.insertSelective(follow);
      user.setFollows(user.getFollows()+1);
      user1.setFans(user1.getFans()+1);
    }
    userMapper.updateByPrimaryKeySelective(user);
    userMapper.updateByPrimaryKeySelective(user1);
  }

    @Override
    public List<CardVO> getCollectList(Integer id) {
     List<CollectInfo> collectInfoList=collectInfoMapper.getCollectInfoByUid(id);
     List<CardVO> cardVOList=new ArrayList<>();
     for (int i=0;i<collectInfoList.size();i++){
       Audio audio=audioMapper.selectByPrimaryKey(collectInfoList.get(i).getAudioId());
       CardVO cardVO=new CardVO();
       BeanUtils.copyProperties(audio,cardVO);
       cardVO.setCoverUrl(fileService.getUrlById(cardVO.getCoverId()));
       cardVOList.add(cardVO);
     }
     return cardVOList;



    }

  @Override
  public List<FUserVO> getFollowList(Integer id) {
    List<Follow> followList=followMapper.getFollowByUid(id);
    List<FUserVO> fUserVOList=new ArrayList<>();
    for (int i=0;i<followList.size();i++)
    {
      FUserVO fUserVO=new FUserVO();
      User user=userMapper.selectByPrimaryKey(followList.get(i).getFollowId());
      BeanUtils.copyProperties(user,fUserVO);
      fUserVO.setHeadPicUrl(fileService.getUrlById(fUserVO.getHeadPicId()));
      fUserVOList.add(fUserVO);
    }
    return fUserVOList;
  }

  @Override
  public List<FUserVO> getFanList(Integer id) {
    List<Follow> followList=followMapper.getFanByUid(id);
    List<FUserVO> fUserVOList=new ArrayList<>();
    for (int i=0;i<followList.size();i++)
    {
      FUserVO fUserVO=new FUserVO();
      User user=userMapper.selectByPrimaryKey(followList.get(i).getUid());
      BeanUtils.copyProperties(user,fUserVO);
      fUserVO.setHeadPicUrl(fileService.getUrlById(fUserVO.getHeadPicId()));
      fUserVOList.add(fUserVO);
    }
    return fUserVOList;
  }

    @Override
    public List<UAudioVO> getUploadList(Integer id) {
        List<UAudioVO> uAudioVOList=new ArrayList<>();
        List<Audio> audioList=audioMapper.selectByUid(id);
        for (int i=0;i<audioList.size();i++)
        {
          UAudioVO uAudioVO=new UAudioVO();
          BeanUtils.copyProperties(audioList.get(i),uAudioVO);
          uAudioVO.setAudioType(audioService.getAudioType(uAudioVO.getAudioTypeId()));
          uAudioVO.setCoverUrl(fileService.getUrlById(uAudioVO.getCoverId()));
          uAudioVOList.add(uAudioVO);
        }
        return uAudioVOList;

    }

  @Override
  public AudioAdminVO getUpload(Integer audioId) {
    AudioAdminVO audioAdminVO =new AudioAdminVO();
    Audio audio=audioMapper.selectByPrimaryKey(audioId);
    BeanUtils.copyProperties(audio,audioAdminVO);
    Audit audit=auditMapper.selectByAId(audioAdminVO.getAudioId());
    audioAdminVO.setAudioType(audioService.getAudioType(audioAdminVO.getAudioTypeId()));
    audioAdminVO.setCoverUrl(fileService.getUrlById(audioAdminVO.getCoverId()));
    audioAdminVO.setContentUrl(fileService.getUrlById(audioAdminVO.getContentId()));
    if (audit!=null){
      audioAdminVO.setReason(audit.getReason());
      audioAdminVO.setAuditTime(audit.getAuditTime());}
    return audioAdminVO;
  }

  @Override
  public Object updateAudio(Integer audioId, AudioDTO audioDTO) {
    Audio audio=audioMapper.selectByPrimaryKey(audioId);
    BeanUtils.copyProperties(audioDTO,audio);
    Date date=new Date();
    Long dateTime=date.getTime();
    audio.setCreateTime(dateTime);
    audio.setStatus(0);
    audioMapper.updateByPrimaryKeySelective(audio);
    return getUpload(audioId);
  }


    @Override
    @Scheduled(cron = "0 59 23 * * *")
//    @Scheduled(fixedDelay = 5000)
    public Integer getLoginNumber() {
    List<User> userList=userMapper.selectAll();
    int loginNumber=0;
    for (int i=0;i<userList.size();i++)
    {
      Date date=new Date(userList.get(i).getLastLoginTime());
      Calendar now = Calendar.getInstance();
//      System.out.println(date.getYear()+1900);
//      System.out.println(now.get(Calendar.YEAR));
//      System.out.println(date.getMonth()+1);
//      System.out.println(now.get(Calendar.MONTH) + 1 );
//      System.out.println(date.getDate());
//      System.out.println(now.get(Calendar.DAY_OF_MONTH));
      if (((date.getYear()+1900)==now.get(Calendar.YEAR)) && ((date.getMonth()+1)==now.get(Calendar.MONTH)+1) && (date.getDate()==now.get(Calendar.DAY_OF_MONTH)))
      {
        loginNumber++;
      }

    }
    System.out.println(loginNumber);
        return loginNumber;

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
//  private boolean userExistsByEMail(String Email){
//    User exampleUser = new User();
//    exampleUser.setEmail(Email);
//    if (userMapper.selectOne(exampleUser) != null) {
//      return true;
//    }
//    else return false;
//  }
  /**
   * 判断用户名是否已经注册过
   */
  private boolean userExistsByUname(String Uname){
    User exampleUser = new User();
    exampleUser.setUname(Uname);
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
