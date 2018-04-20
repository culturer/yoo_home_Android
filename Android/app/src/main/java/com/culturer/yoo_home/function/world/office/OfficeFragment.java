package com.culturer.yoo_home.function.world.office;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.armour8.yooplus.yooplus.R;
import com.culturer.yoo_home.function.world.shows.show.pages.pageOne.FragmentBackHandler;
import com.vondear.rxtools.RxConstants;

public class OfficeFragment extends Fragment implements FragmentBackHandler {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    
    ProgressBar pbWebBase;
    WebView webBase;
    
    private String webPath = "";
    private long mBackPressed;
    
    private View contentView;

    public OfficeFragment() {
        // Required empty public constructor
    }

    public static OfficeFragment newInstance(String param1, String param2) {
        OfficeFragment fragment = new OfficeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_office, container, false);
        init();
        return contentView;
    }
    
    
    private void init(){
        initView();
        initData();
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private void initData(){
        pbWebBase.setMax(100);//设置加载进度最大值
//        webPath = getIntent().getStringExtra("URL");
        webPath = RxConstants.URL_BAIDU_SEARCH;//加载的URL
        if (webPath.equals("")) {
            //设置默认URL
            webPath = "http://demo.cssmoban.com/cssthemes3/ft5_48_glow/index.html";
        }

    
        WebSettings webSettings = webBase.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存否则网络
        }
        
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webBase.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webBase.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false
        
        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放
        
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        
        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        
        webSettings.setDomStorageEnabled(true);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webBase.setSaveEnabled(true);
        webBase.setKeepScreenOn(true);
        
        // 设置setWebChromeClient对象
        webBase.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                mRxTextAutoZoom.setText(title);
            }
            
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                pbWebBase.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法可在WebView中打开链接，反之用浏览器打开
        webBase.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webBase.getSettings().getLoadsImagesAutomatically()) {
                    webBase.getSettings().setLoadsImagesAutomatically(true);
                }
                pbWebBase.setVisibility(View.GONE);
            }
            
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                pbWebBase.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
            
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
                
                
                // Otherwise allow the OS to handle things like tel, mailto, etc.
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        webBase.setDownloadListener((paramAnonymousString1, paramAnonymousString2, paramAnonymousString3, paramAnonymousString4, paramAnonymousLong) -> {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(paramAnonymousString1));
            startActivity(intent);
        });
        
        webBase.loadUrl(webPath);
        Log.v("帮助类完整连接", webPath);
    }
    
    private void initView(){
        pbWebBase = contentView.findViewById(com.vondear.rxtools.R.id.pb_web_base);
        webBase = contentView.findViewById(com.vondear.rxtools.R.id.web_base);
    }
    
    public void onConfigurationChanged(Configuration newConfig) {
        
        try {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_LANDSCAPE");
            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_PORTRAIT");
            }
        } catch (Exception ex) {
        
        }
    }
    
    @Override
    public boolean onBackPressed() {
        if (webBase.canGoBack()) {
            webBase.goBack();
            return true;
        }
        return false;
    }
    
}
