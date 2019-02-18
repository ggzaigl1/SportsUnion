package com.fy.baselibrary.retrofit;

import com.fy.baselibrary.entity.BodyConToAppBean;
import com.fy.baselibrary.entity.CourseDetails;
import com.fy.baselibrary.entity.CourseList;
import com.fy.baselibrary.entity.EachHealthInfoBean;
import com.fy.baselibrary.entity.EmergencyTreatmentBean;
import com.fy.baselibrary.entity.EnergyDemandListBean;
import com.fy.baselibrary.entity.ExerciseClubBean;
import com.fy.baselibrary.entity.ExerciseClubDetailBean;
import com.fy.baselibrary.entity.ExerciseItemsBean;
import com.fy.baselibrary.entity.FoodFestivalBean;
import com.fy.baselibrary.entity.GroupMultiToAppBean;
import com.fy.baselibrary.entity.HealthyFoodBean;
import com.fy.baselibrary.entity.HomeBean;
import com.fy.baselibrary.entity.JiangKanBean;
import com.fy.baselibrary.entity.LoginBean;
import com.fy.baselibrary.entity.MealsBean;
import com.fy.baselibrary.entity.NewHeathyFoodBean;
import com.fy.baselibrary.entity.NewsBean;
import com.fy.baselibrary.entity.PhysicalDiagnosisBean;
import com.fy.baselibrary.entity.PunchClockBean;
import com.fy.baselibrary.entity.ReplyListBean;
import com.fy.baselibrary.entity.SecondRecommendBean;
import com.fy.baselibrary.entity.SelfChallenge;
import com.fy.baselibrary.entity.SelfChallengeRecord;
import com.fy.baselibrary.entity.SelfDetection;
import com.fy.baselibrary.entity.SelfDetectionAdd;
import com.fy.baselibrary.entity.SensoryListToApp;
import com.fy.baselibrary.entity.SensoryOne;
import com.fy.baselibrary.entity.SocialAllListBean;
import com.fy.baselibrary.entity.SportMethodBean;
import com.fy.baselibrary.entity.StaminaSignalBean;
import com.fy.baselibrary.entity.StaminaToAppBean;
import com.fy.baselibrary.entity.StandardsBean;
import com.fy.baselibrary.entity.StudentCompareBean;
import com.fy.baselibrary.entity.StudentInfo;
import com.fy.baselibrary.entity.TotalCountBean;
import com.fy.baselibrary.entity.TotalHealthInfoBean;
import com.fy.baselibrary.entity.WeeklyRecipeBean;
import com.fy.baselibrary.utils.ConstantUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 通用的的api接口 </p>
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

    int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间


    String BASE_URL = "http://47.104.176.229:80/";    //阿里云正式 地址

//    String BASE_URL = "http://192.168.100.158:80/";    //本地服务器 地址

//    String BASE_URL = "http://192.168.100.251:8080/";    //张敏服务器地址

//    String BASE_URL = "http://192.168.100.114:8080/";    //周俊服务器地址

    String IMG_BASE_URL = BASE_URL + "image";

    String IMG_BASE_URL_THUM = BASE_URL + "image/thum";

    //  http://192.168.100.158:80/image/thum/web/f336cd3260314fa78d1289ffea54245a.png

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("sys/loginToApp")
    Observable<BeanModule<LoginBean>> loginToApp(@FieldMap Map<String, Object> options);

    /**
     * 检测token
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("sys/checktoken")
    Observable<BeanModule<Object>> checktoken(@FieldMap Map<String, Object> options);


    /**
     * 退出登录接口
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("sys/logout")
    Observable<BeanModule<Object>> loginOut(@FieldMap Map<String, Object> options);

    /**
     * 修改密码
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("sys/upadatePswdToApp")
    Observable<BeanModule<Object>> upadatePswdToApp(@FieldMap Map<String, Object> options);


    /**
     * 学生个人信息
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("student/infoToApp")
    Observable<BeanModule<StudentInfo>> infoToApp(@FieldMap Map<String, Object> options);

    /**
     * 学生个人信息修改
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("student/updateToApp")
    Observable<BeanModule<Object>> updateInfo(@FieldMap Map<String, Object> options);

    /**
     * 体质信息
     */
    @Headers({"url_name:user"})
    @GET("measurements/studentItemToApp")
    Observable<BeanModule<TotalHealthInfoBean>> getItemToApp(@QueryMap Map<String, Object> options);

    /**
     * 体型
     */
    @Headers({"url_name:user"})
    @GET("measurements/bodyConfigurationToApp")
    Observable<BeanModule<BodyConToAppBean>> getBodyConToApp(@QueryMap Map<String, Object> options);

    /**
     * 体能
     */
    @Headers({"url_name:user"})
    @GET("measurements/staminaToApp")
    Observable<BeanModule<StaminaToAppBean>> getStaminaToApp(@QueryMap Map<String, Object> options);


    /**
     * 各项体质信息
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("measurements/singleItemScToApp")
    Observable<BeanModule<EachHealthInfoBean>> getItemScToApp(@FieldMap Map<String, Object> options);


    /**
     * 体质与所有学生平均值比较
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("measurements/studentCompareAllToApp")
    Observable<BeanModule<StudentCompareBean>> getCompareAllToApp(@FieldMap Map<String, Object> options);


    /**
     * 推荐菜谱
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("dietary/listToApp")
    Observable<BeanModule<MealsBean>> getMenuList(@FieldMap Map<String, Object> options);


    /**
     * 多图片上传
     *
     * @param token
     * @return
     */
    @Multipart
    @Headers({"url_name:user"})
    @POST("file/uploadImages")
//    @POST("http://192.168.100.123/hfs/")
    Observable<BeanModule<String>> uploadPostFile(@Part("token") RequestBody token,
                                                  @Part("type") RequestBody type,
                                                  @Part List<MultipartBody.Part> files);

    /**
     * 多上传视频
     *
     * @param token
     * @param files
     * @return
     */
    @Multipart
    @Headers({"url_name:user"})
    @POST("sports/selfChallenge/upload")
    Observable<BeanModule<List<String>>> uploadVideo(@Part("token") RequestBody token,
                                                     @Part("dir") RequestBody dir,
                                                     @Part List<MultipartBody.Part> files);


    /**
     * 所有帖子列表
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/listAllByApp")
    Observable<BeanModule<SocialAllListBean>> getlistAllByApp(@FieldMap Map<String, Object> options);

    /**
     * 我的发帖列表
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/listMyByApp")
    Observable<BeanModule<SocialAllListBean>> getlistMyByApp(@FieldMap Map<String, Object> options);

    /**
     * 我的发帖列表
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/listStudentByApp   ")
    Observable<BeanModule<SocialAllListBean>> getlistStudentByApp(@FieldMap Map<String, Object> options);

    /**
     * 新增帖子
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/addToApp")
    Observable<BeanModule<Object>> addToApp(@FieldMap Map<String, Object> options);


    /**
     * 某个帖子回复列表
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/listSonByApp")
    Observable<BeanModule<ReplyListBean>> getlistSonByApp(@FieldMap Map<String, Object> options);


    /**
     * 删除帖子
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/deleteToApp")
    Observable<BeanModule<Object>> deleteToApp(@FieldMap Map<String, Object> options);

    /**
     * 回复帖子
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/addSonToApp")
    Observable<BeanModule<Object>> addSonToApp(@FieldMap Map<String, Object> options);

    /**
     * 某个评论回复列表
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/listSonByApp")
    Observable<BeanModule<SecondRecommendBean>> getlistSecondSonByApp(@FieldMap Map<String, Object> options);

    /**
     * 点赞
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/addLikeToApp")
    Observable<BeanModule<Object>> addLikeToApp(@FieldMap Map<String, Object> options);

    /**
     * 取消赞
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("social/deleteLikeToApp")
    Observable<BeanModule<Object>> deleteLikeToApp(@FieldMap Map<String, Object> options);

    /**
     * 断点下载指定URL的文件
     *
     * @param range
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Header("Range") String range, @Url String fileUrl);

    /**
     * 获取下载的文件的大小
     *
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFileSize(@Url String fileUrl);


    /**
     * 首页信息
     */
    @Headers({"url_name:user"})
    @GET("student/getHome")
    Observable<BeanModule<HomeBean>> getHome(@QueryMap Map<String, Object> options);

    /**
     * 总分界面接口
     */
    @Headers({"url_name:user"})
    @GET("measurements/totalCountToApp")
    Observable<BeanModule<TotalCountBean>> getTotalCountToApp(@QueryMap Map<String, Object> options);

    /**
     * 达标
     */
    @Headers({"url_name:user"})
    @GET("measurements/standardsToApp")
    Observable<BeanModule<StandardsBean>> getStandardsToApp(@QueryMap Map<String, Object> options);

    /**
     * 练习方法/饮食处方
     */
    @Headers({"url_name:user"})
    @GET("app/groupMultipleToApp")
    Observable<BeanModule<GroupMultiToAppBean>> getGroupMultipleToApp(@QueryMap Map<String, Object> options);

    /**
     * 膳食-每日营养
     */
    @Headers({"url_name:user"})
    @GET("nutrition/listToApp")
    Observable<BeanModule<EnergyDemandListBean>> getListToApp(@QueryMap Map<String, Object> options);

    /**
     * 膳食-健康美食
     */
    @Headers({"url_name:user"})
    @GET("app/recipesMotion/getRecipesMotionToApp")
    Observable<BeanModule<FoodFestivalBean>> getFoodFestivalApp(@QueryMap Map<String, Object> options);

    /**
     * 膳食-健康食材
     */
    @Headers({"url_name:user"})
    @GET("healthFood/listToApp")
    Observable<BeanModule<NewHeathyFoodBean>> getlistToApp(@QueryMap Map<String, Object> options);

    /**
     * 膳食-每周食谱
     */
    @Headers({"url_name:user"})
    @GET("app/recipesMotion/getRecipesMotionToApp")
    Observable<BeanModule<WeeklyRecipeBean>> getWeeklyRecipeApp(@QueryMap Map<String, Object> options);

    /**
     * 诊断和诊断处方（运动，膳食）
     */
    @Headers({"url_name:user"})
    @GET("app/groupMultiple/groupMultipleToApp")
    Observable<BeanModule<PhysicalDiagnosisBean>> groupMultipleToApp(@QueryMap Map<String, Object> options);

    /**
     * 体能红绿灯
     */
    @Headers({"url_name:user"})
    @GET("sensory/senseMainToApp")
    Observable<BeanModule<StaminaSignalBean>> getsenseMainToApp(@QueryMap Map<String, Object> options);

    /**
     * 体能红绿灯 意义和方式
     */
    @Headers({"url_name:user"})
    @GET("sensory/senseListToApp")
    Observable<BeanModule<SensoryListToApp>> getsenseListToApp(@QueryMap Map<String, Object> options);

    /**
     * 感统
     */
    @Headers({"url_name:user"})
    @GET("sensory/sensoryListToApp")
    Observable<BeanModule<SensoryListToApp>> getSensoryListToApp(@QueryMap Map<String, Object> options);

    /**
     * 感统的意义更多
     */
    @Headers({"url_name:user"})
    @GET("sensory/sensoryListToApp")
    Observable<BeanModule<SensoryListToApp>> getSensoryListMoreToApp(@QueryMap Map<String, Object> options);

    /**
     * 运动
     */
    @Headers({"url_name:user"})
    @GET("app/sportsMotion/getSportsMotionToApp")
    Observable<BeanModule<SensoryOne>> getSportsMotionToApp(@QueryMap Map<String, Object> options);

    /**
     * 自我挑战保存接口
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("sports/selfChallenge/saveSelfChallengeList")
    Observable<BeanModule<Object>> saveSelfChallengeList(@Header("token") String token,
                                                         @Field("data") String options);

    /**
     * 自我检测 获取条目数据
     *
     * @param options
     * @return
     */
    @Headers({"url_name:user"})
    @GET("selfEvaluating/getItemToApp")
    Observable<BeanModule<List<SelfDetection>>> getSelfItemInfo(@QueryMap Map<String, Object> options);

    /**
     * 自我检测 提交数据
     *
     * @param options
     * @return
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("selfEvaluating/addToApp")
    Observable<BeanModule<SelfDetectionAdd>> selfAddToApp(@FieldMap Map<String, Object> options);


    /**
     * 自我评价 测试结果
     *
     * @param options
     * @return
     */
    @Headers({"url_name:user"})
    @GET("sports/selfChallenge/getMaxToApp")
    Observable<BeanModule<SelfChallenge>> getSelfChallenge(@QueryMap Map<String, Object> options);

    /**
     * 体测历史页面 测试结果
     *
     * @param options
     * @return
     */
    @Headers({"url_name:user"})
    @GET("sports/selfChallenge/historyListToApp")
    Observable<BeanModule<SelfChallengeRecord>> getHistoryListToApp(@QueryMap Map<String, Object> options);

    /**
     * //获取某月打开记录日期
     */
    @Headers({"url_name:user"})
    @GET("app/punchClock/getPunchClockByMonthToApp")
    Observable<BeanModule<List<String>>> getPunchClockByMonthToApp(@QueryMap Map<String, Object> options);

    /**
     * //获取某日打卡记录
     */
    @Headers({"url_name:user"})
    @GET("app/punchClock/getPunchClockToApp")
    Observable<BeanModule<List<PunchClockBean>>> getPunchClockToApp(@QueryMap Map<String, Object> options);

    /**
     * 练习项目
     */
    @Headers({"url_name:user"})
    @GET("app/sportsMethod/getSportsMethodToApp")
    Observable<BeanModule<SportMethodBean>> getSportsMethodToApp(@QueryMap Map<String, Object> options);

    /**
     * 保存打卡的记录
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("app/punchClock/saveToApp")
//    Observable<BeanModule<SportMethodBean>> savePunchClock(@FieldMap Map<String, Object> options);
    Observable<BeanModule<SportMethodBean>> savePunchClock(@Field("token") String token, @Field("data") String options);

    /**
     * 急救技巧
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("emergencyTreatment/listToApp")
    Observable<BeanModule<EmergencyTreatmentBean>> emergencyTreatment(@FieldMap Map<String, Object> options);

    /**
     * 资讯
     */
    @Headers({"url_name:user"})
    @GET("https://api.kmwlyy.com/News")
    Observable<NewsBean> News(@QueryMap Map<String, Object> options);


    /**
     * 技能拓展 --项目列表
     */
    @Headers({"url_name:user"})
    @GET("exerciseItems/listToApp")
    Observable<BeanModule<ExerciseItemsBean>> getExerciseItems(@QueryMap Map<String, Object> options);

    /**
     * 技能拓展 --俱乐部列表
     */
    @Headers({"url_name:user"})
    @GET("exerciseClub/listToApp")
    Observable<BeanModule<ExerciseClubBean>> getExerciseClub(@QueryMap Map<String, Object> options);


    /**
     * 技能拓展 --俱乐部详细信息
     */
    @Headers({"url_name:user"})
    @GET("exerciseClub/infoToApp")
    Observable<BeanModule<ExerciseClubDetailBean>> getExerciseClubDetails(@QueryMap Map<String, Object> options);

    /**
     * 个人健康档案
     */
    @FormUrlEncoded
    @Headers({"url_name:user"})
    @POST("intface/userHealth/getjiangkanurl")
    Observable<BeanModule<JiangKanBean>> getjiangkanurl(@FieldMap Map<String, Object> options);

    /**
     * 运动课程 ---运动列表
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/trainitem/trainitemlist")
    Observable<CourseList> getCourseList(@QueryMap Map<String, Object> options);

    /**
     * 运动课程 ---运动详情
     */
    @Headers({"url_name:user"})
    @GET("http://api.fithub.cc/api/v44/train/course")
    Observable<CourseDetails> getCourseDetails(@QueryMap Map<String, Object> options);
}
