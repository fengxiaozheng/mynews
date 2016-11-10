package com.example.fengxiaozheng.mynews.presenter;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.R;
import com.example.fengxiaozheng.mynews.presenter.base.BasePresenter;
import com.example.fengxiaozheng.mynews.utils.SPUtils;
import com.example.fengxiaozheng.mynews.view.LoginView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.tpl.OnLoginListener;
import cn.sharesdk.onekeyshare.tpl.SignupPage;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by fengxiaozheng on 2016/11/7.
 */

public class LoginPresenterImp extends BasePresenter<LoginView> implements LoginPresenter, PlatformActionListener, View.OnClickListener, Handler.Callback {
    // 填写从短信SDK应用后台注册得到的APPKEY
    private static String APPKEY = "18ceed0d5ddcb";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "ec234f9f4f1d3c5e96b71ddbf1c07fa2";

        private static final int MSG_SMSSDK_CALLBACK = 1;
        private static final int MSG_AUTH_CANCEL = 2;
        private static final int MSG_AUTH_ERROR= 3;
        private static final int MSG_AUTH_COMPLETE = 4;

        private String smssdkAppkey;
        private String smssdkAppSecret;
        private OnLoginListener signupListener;
        private Handler handler;
        //短信验证的对话框
        private Dialog msgLoginDlg;

    public LoginPresenterImp(LoginView view) {
        attachView(view);
        handler = new Handler(this);
    }

    /** 设置授权回调，用于判断是否进入注册 */
    public void setOnLoginListener(OnLoginListener l) {
        this.signupListener = l;
    }

    @Override
    public void init() {
        SPUtils spUtils = new SPUtils(view.getContext(), Constants.APP_NAME);
        if (spUtils.getBoolean(Constants.LOGIN) == true){
            view.success();
        }
    }

    @Override
    public void weiboLogin() {
//        ThirdPartyLogin tpl = new ThirdPartyLogin();
//        tpl.setSMSSDKAppkey(APPKEY, APPSECRET);
//        tpl.setOnLoginListener(new OnLoginListener() {
//            public boolean onSignin(String platform, HashMap<String, Object> res) {
//                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
//                // 此处全部给回需要注册
//                view.failure();
//                return true;
//            }
//
//            public boolean onSignUp(UserInfo info) {
//                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
//                view.success();
//                return true;
//            }
//        });
//        tpl.show(view.getContext());

        Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        authorize(sina);
    }

    @Override
    public void wechatLogin() {
        //微信登录
        //测试时，需要打包签名；sample测试时，用项目里面的demokey.keystore
        //打包签名apk,然后才能产生微信的登录
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        authorize(wechat);
    }

    @Override
    public void qqLogin() {
        Platform qzone = ShareSDK.getPlatform(QQ.NAME);
        authorize(qzone);
    }

    @Override
    public void share() {
        ShareSDK.initSDK(view.getContext());
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(view.getContext().getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(view.getContext());
    }


    //执行授权,获取用户信息
    //文档：http://wiki.mob.com/Android_%E8%8E%B7%E5%8F%96%E7%94%A8%E6%88%B7%E8%B5%84%E6%96%99
    private void authorize(Platform plat) {
        if (plat == null) {
            popupOthers();
            return;
        }

        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    //其他登录对话框
    private void popupOthers() {
        Dialog dlg = new Dialog(view.getContext(), R.style.WhiteDialog);
        View dlgView = View.inflate(view.getContext(), R.layout.tpl_other_plat_dialog, null);
        View tvFacebook = dlgView.findViewById(R.id.tvFacebook);
        tvFacebook.setTag(dlg);
        tvFacebook.setOnClickListener(this);
        View tvTwitter = dlgView.findViewById(R.id.tvTwitter);
        tvTwitter.setTag(dlg);
        tvTwitter.setOnClickListener(this);

        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(dlgView);
        dlg.show();
    }

    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            msg.obj = new Object[] {platform.getName(), res};
            handler.sendMessage(msg);
        }
    }

    public void onError(Platform platform, int action, Throwable t) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        t.printStackTrace();
    }

    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_AUTH_CANCEL:
                //取消授权
                Toast.makeText(view.getContext(), R.string.auth_cancel, Toast.LENGTH_SHORT).show();
             break;
            case MSG_AUTH_ERROR: {
                //授权失败
                Toast.makeText(view.getContext(), R.string.auth_error, Toast.LENGTH_SHORT).show();
            } break;
            case MSG_AUTH_COMPLETE: {
                //授权成功
                Toast.makeText(view.getContext(), R.string.auth_complete, Toast.LENGTH_SHORT).show();
                Object[] objs = (Object[]) msg.obj;
                String platform = (String) objs[0];
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                if (signupListener != null && signupListener.onSignin(platform, res)) {
                    SignupPage signupPage = new SignupPage();
                    signupPage.setOnLoginListener(signupListener);
                    signupPage.setPlatform(platform);
                    signupPage.show(view.getContext(), null);
                }
                view.success();
            } break;
            case MSG_SMSSDK_CALLBACK: {
                if (msg.arg2 == SMSSDK.RESULT_ERROR) {
                    Toast.makeText(view.getContext(), "操作失败", Toast.LENGTH_SHORT).show();
                } else {
                    switch (msg.arg1) {
                        case SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE: {
                            if(msgLoginDlg != null && msgLoginDlg.isShowing()){
                                msgLoginDlg.dismiss();
                            }
                            Toast.makeText(view.getContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                            Message m = new Message();
                            m.what = MSG_AUTH_COMPLETE;
                            m.obj = new Object[] {"SMSSDK", (HashMap<String, Object>) msg.obj};
                            handler.sendMessage(m);
                        } break;
                        case SMSSDK.EVENT_GET_VERIFICATION_CODE:{
                            Toast.makeText(view.getContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                        } break;
                    }
                }
            } break;
        }
        return false;
    }

    public void show(Context context) {
        initSDK(context);
    //    super.show(context, null);
    }

    private void initSDK(Context context) {
        //初始化sharesdk,具体集成步骤请看文档：
        //http://wiki.mob.com/Android_%E5%BF%AB%E9%80%9F%E9%9B%86%E6%88%90%E6%8C%87%E5%8D%97
        ShareSDK.initSDK(context);

        //短信验证初始化，具体集成步骤看集成文档：
        //http://wiki.mob.com/Android_%E7%9F%AD%E4%BF%A1SDK%E9%9B%86%E6%88%90%E6%96%87%E6%A1%A3
        SMSSDK.initSDK(context, smssdkAppkey, smssdkAppSecret);
        EventHandler eh = new EventHandler(){
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册短信验证的监听
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void onClick(View view) {

    }
}
