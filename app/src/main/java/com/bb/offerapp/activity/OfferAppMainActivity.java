package com.bb.offerapp.activity;


import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.offerapp.R;
import com.bb.offerapp.adapter.MyViewPaperAdapter;
import com.bb.offerapp.dialog.CityNameDialog;
import com.nineoldandroids.view.ViewHelper;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherDaily;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import me.grantland.widget.AutofitTextView;


public class OfferAppMainActivity extends AppCompatActivity {
    //两次退出程序
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    //悬浮按钮
    private FloatingActionButton fab;

    //欢迎
    private AppBarLayout app_bar;
    //tab菜单
    private TabLayout tabLayout;
    private String[] tabTittle = {"功能", "项目"};
    //工具栏
    private Toolbar toolbar;
    //Viewpager
    private ViewPager viewPager;
    private MyViewPaperAdapter myViewPaperAdapter;
    //侧滑菜单
    private DrawerLayout drawer;
    private NavigationView navigationView;
    //首页壁纸
    private ImageView index_image;
    //天气
    private ImageView weather_icon;
    private AutofitTextView weather_city;
    private AutofitTextView weather_case;
    private TextView weather_degree;
    private TextView weather_wind;
    private String temperature;//实时温度
    //改变天气位置dailog
    private CityNameDialog cityNameDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView_ImageView();//初始化首页壁纸
        initView_NavigationView();//初始化DrawerLayout侧滑菜单导航
        initView_AppBarLayout();//初始化AppBarLayout
        initView_Toolbar();//初始化Toolbar
        initView_TabLayout();//初始化TabLayout
        initView_ViewPager();//初始化ViewPaper
        initView_FloatingActionButton();//初始化FloatingActionButton(悬浮按钮)
        initView_WeatherCard();//初始化天气状况
        initView_WeatherData("北京");//从"心知天气"获得3天天气api接口数据
        initView_NowWeatherData("北京");//获得当天实时天气
        initView_DrawerLayout();//DrawerLayout

    }


    private void initView_DrawerLayout() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }


    //两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }



    private void initView_ImageView() {
        index_image = (ImageView) findViewById(R.id.imageview);
    }

    private void initView_NavigationView() {

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_changepic) {
                    if (ContextCompat.checkSelfPermission(OfferAppMainActivity.this, Manifest.
                            permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(OfferAppMainActivity.this,new String[]{Manifest.
                                permission.READ_EXTERNAL_STORAGE},1);
                    }else{
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        openAlbumIntent.setType("image/*");
                        startActivityForResult(openAlbumIntent, 1);
                    }

                } else if (id == R.id.nav_changecity) {
                    cityNameDialog = new CityNameDialog(OfferAppMainActivity.this);
                    cityNameDialog.show();
                    cityNameDialog.getOk().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            weather_city.setText(cityNameDialog.getEditText().getText().toString());
                            initView_WeatherData(cityNameDialog.getEditText().getText().toString());
                            initView_NowWeatherData(cityNameDialog.getEditText().getText().toString());
                            cityNameDialog.cancel();
                        }
                    });


                } else if (id == R.id.nav_slideshow) {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(OfferAppMainActivity.this, 2);
                    gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setLayoutManager(gridLayoutManager);
                    myViewPaperAdapter.getaFragment().getmRecyclerAdapter().setSmallType(true);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setAdapter(myViewPaperAdapter.getaFragment().getmRecyclerAdapter());

                } else if (id == R.id.nav_wechat) {
                    Intent intent2 = new Intent(OfferAppMainActivity.this, WechatCard.class);
                    startActivity(intent2);
                } else if (id == R.id.nav_about) {

                    Intent intent = new Intent(OfferAppMainActivity.this, About.class);
                    startActivity(intent);

                } else if (id == R.id.nav_lineshow) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(OfferAppMainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setLayoutManager(linearLayoutManager);
                    myViewPaperAdapter.getaFragment().getmRecyclerAdapter().setSmallType(false);
                    myViewPaperAdapter.getaFragment().getmRecyclerView().setAdapter(myViewPaperAdapter.getaFragment().getmRecyclerAdapter());

                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    openAlbumIntent.setType("image/*");
                    startActivityForResult(openAlbumIntent, 1);
                }else{
                    Toast.makeText(OfferAppMainActivity.this,"未授权",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        else {
            try {
                Uri uri = data.getData();
                Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(),
                        uri);
                index_image.setImageBitmap(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void initView_AppBarLayout() {
        app_bar = (AppBarLayout) findViewById(R.id.app_bar);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("");//设置收缩之前标题
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    collapsingToolbarLayout.setTitle(weather_case.getText().toString() + " " + temperature + "°");//设置收缩之后标题
                } else {
                    collapsingToolbarLayout.setTitle("");//设置收缩中标题
                }
            }
        });
    }


    private void initView_Toolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void initView_TabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，MODE_FIXED是固定的,不能超出屏幕，MODE_SCROLLABLE可超出屏幕范围滚动的
        tabLayout.addTab(tabLayout.newTab().setText("杂货"));
        tabLayout.addTab(tabLayout.newTab().setText("音乐"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //关联viewPager
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView_ViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpaper);
        viewPager.setAdapter(myViewPaperAdapter = new MyViewPaperAdapter(getSupportFragmentManager(), tabTittle));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void initView_FloatingActionButton() {
        final Github github = new Github(this);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.argb(222, 208, 166, 80)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "将跳转至浏览器", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mHandler.removeCallbacks(github);
                                Toast.makeText(OfferAppMainActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                mHandler.postDelayed(github, 3000);
            }
        });
    }

    private void initView_WeatherCard() {

        weather_icon = (ImageView) findViewById(R.id.weather_icon);
        weather_city = (AutofitTextView) findViewById(R.id.weather_city);
        weather_case = (AutofitTextView) findViewById(R.id.weather_case);
        weather_degree = (TextView) findViewById(R.id.weather_degree);
        weather_wind = (TextView) findViewById(R.id.weather_wind);

        weather_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityNameDialog = new CityNameDialog(OfferAppMainActivity.this);
                cityNameDialog.show();
                cityNameDialog.getOk().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        weather_city.setText(cityNameDialog.getEditText().getText().toString());
                        initView_WeatherData(cityNameDialog.getEditText().getText().toString());
                        initView_NowWeatherData(cityNameDialog.getEditText().getText().toString());
                        cityNameDialog.cancel();
                    }
                });


            }
        });

    }

    private void initView_WeatherData(String cityName) {
        final TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        //使用心知天气官网获取的key和用户id初始化WeatherManager
        weatherManager.initWithKeyAndUserId("fyajwhj499drrlob", "U041DF6FDF");
        weatherManager.getWeatherDailyArray(new TPCity(cityName)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new Date(), 3
                , new TPListeners.TPWeatherDailyListener() {
                    @Override
                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
                        if (tpWeatherDailies != null) {
                            String textDay = tpWeatherDailies[0].textDay;
                            switch (textDay) {
                                case "晴":
                                    weather_icon.setImageResource(R.drawable.sun);
                                    break;
                                case "多云":
                                    weather_icon.setImageResource(R.drawable.cloudy);
                                    break;
                                case "晴间多云":
                                    weather_icon.setImageResource(R.drawable.suncloudy);
                                    break;
                                case "大部多云":
                                    weather_icon.setImageResource(R.drawable.mostcloudy);
                                    break;
                                case "阴":
                                    weather_icon.setImageResource(R.drawable.overcast);
                                    break;
                                case "阵雨":
                                    weather_icon.setImageResource(R.drawable.shower);
                                    break;
                                case "雷阵雨":
                                    weather_icon.setImageResource(R.drawable.storm);
                                    break;
                                case "雷阵雨伴有冰雹":
                                    weather_icon.setImageResource(R.drawable.showersnow);
                                    break;
                                case "小雨":
                                    weather_icon.setImageResource(R.drawable.shower);
                                    break;
                                case "中雨":
                                    weather_icon.setImageResource(R.drawable.midshower);
                                    break;
                                case "大雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "大暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "特大暴雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "冻雨":
                                    weather_icon.setImageResource(R.drawable.heavyshower);
                                    break;
                                case "雨夹雪":
                                    weather_icon.setImageResource(R.drawable.showersnow);
                                    break;
                                case "阵雪":
                                    weather_icon.setImageResource(R.drawable.snow);
                                    break;
                                case "小雪":
                                    weather_icon.setImageResource(R.drawable.snow);
                                    break;
                                case "中雪":
                                    weather_icon.setImageResource(R.drawable.midshower);
                                    break;
                                case "大雪":
                                    weather_icon.setImageResource(R.drawable.heavysnow);
                                    break;
                                case "暴雪":
                                    weather_icon.setImageResource(R.drawable.heavysnow);
                                    break;
                                case "浮尘":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "扬沙":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "沙尘暴":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "强沙尘暴":
                                    weather_icon.setImageResource(R.drawable.dust);
                                    break;
                                case "雾":
                                    weather_icon.setImageResource(R.drawable.foggy);
                                    break;
                                case "霾":
                                    weather_icon.setImageResource(R.drawable.haze);
                                    break;
                                case "风":
                                    weather_icon.setImageResource(R.drawable.windy);
                                    break;
                                case "大风":
                                    weather_icon.setImageResource(R.drawable.windy);
                                    break;
                                case "飓风":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "热带风暴":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "龙卷风":
                                    weather_icon.setImageResource(R.drawable.hurricane);
                                    break;
                                case "冷":
                                    weather_icon.setImageResource(R.drawable.cold);
                                    break;
                                case "热":
                                    weather_icon.setImageResource(R.drawable.hot);
                                    break;
                                case "未知":
                                    weather_icon.setImageResource(R.drawable.na);
                                    break;
                            }
                            weather_case.setText(textDay);
                            weather_degree.setText(tpWeatherDailies[0].lowTemperature + "°~" + tpWeatherDailies[0].highTemperature + "°");
                            weather_wind.setText(tpWeatherDailies[0].windDirection + "风 " + (int) tpWeatherDailies[0].windScale + "级");
                        } else {
                            System.out.println(s); //错误信息
                        }
                    }

                });
    }

    private void initView_NowWeatherData(String cityNamee) {
        TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        weatherManager.initWithKeyAndUserId("fyajwhj499drrlob", "U041DF6FDF");
        weatherManager.getWeatherNow(new TPCity(cityNamee)
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new TPListeners.TPWeatherNowListener() {
                    @Override
                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                        if (weatherNow != null) {
                            temperature = weatherNow.temperature + "";
                        } else {
                        }
                    }
                });

    }

}

class Github implements Runnable {
    Context context;

    Github(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Uri uri = Uri.parse("https://github.com/JayKuzzi");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }
}

