package com.example.a14161.guochuang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a14161.guochuang.adapter.SearchAdapter;
import com.example.a14161.guochuang.model.Bean;
import com.example.a14161.guochuang.widge.SearchView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;


public class MainActivity extends AppCompatActivity implements SearchView.SearchViewListener{
    private PopupMenu popupMenu;
    private Menu menu;
    private ListView lvResults;
    private SearchView searchView;
    private LinearLayout placeHolder;
    private ArrayAdapter<String>hintAdapter;
    private ArrayAdapter<String>autoCompleteAdapter;
    private SearchAdapter resultAdapter;
    private List<Bean> dbData;
    private List<String>hintData;
    private List<String>autoCompleteData;
    private List<Bean>resultData;
    private static int DeFAULT_HINT_SIZE=4;
    private static int hintSize=DeFAULT_HINT_SIZE;

    public static void setHintSize(int hintSize){
        MainActivity.hintSize=hintSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initData(){
        getDbData();
        getHintData();
        getAutoCompletedData(null);
        getResultData(null);
    }

    private void initViews() {
        placeHolder=(LinearLayout)findViewById(R.id.placeHolder);
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        searchView.setSearchViewListener(this);
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast t = Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT);
                t.show();
            }
        });
        popupMenu=new PopupMenu(this,findViewById(R.id.pop_menu_bt));
        menu=popupMenu.getMenu();
        menuAdd();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String wp;
                switch(item.getItemId()) {
                    case Menu.FIRST+0:
                        wp=menu.getItem(Menu.FIRST+0).getTitle().toString();

                        break;
                    case Menu.FIRST+1:
                        wp=menu.getItem(Menu.FIRST+1).getTitle().toString();
                        break;
                    case Menu.FIRST+2:
                        wp=menu.getItem(Menu.FIRST+2).getTitle().toString();
                        break;
                    case Menu.FIRST+3:
                        wp=menu.getItem(Menu.FIRST+3).getTitle().toString();
                        break;
                }
                Intent intent=new Intent(MainActivity.this,WupinInforActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }

    public void menuAdd(){
        String wp="物品";
        menu.clear();
        for(int i=0;i<4;i++)
            menu.add(Menu.NONE,Menu.FIRST+0,0,wp+(i+1));
        //通过xML文件添加菜单项
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.dialog,menu);



    }
    public void popupmenu(View v){
        popupMenu.show();
    }
    public void setPlaceHolderVisable(){
        placeHolder.setVisibility(GONE);
    }
    /**
     * 获取db 数据
     */
    private void getDbData(){
        int size=100;
        dbData=new ArrayList<>(size);
        for(int i=0;i<size;i++){
            dbData.add(new Bean(R.drawable.icon,"android开发"+(i+1),"Android自定义view--i定义搜索",i*20+2+""));
        }
    }
    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData(){
        hintData=new ArrayList<>(hintSize);
        for(int i=0;i<hintSize;i++)
            hintData.add("热搜版"+i+":android自定义view");
        hintAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */

    private void getAutoCompletedData(String text){
        if(autoCompleteData==null)
            autoCompleteData=new ArrayList<>();
        else {
            autoCompleteData.clear();
            for(int i=0,count=0;i<dbData.size()&&count<hintData.size();i++){
                if(dbData.get(i).getTitle().contains(text.trim())){
                    autoCompleteData.add(dbData.get(i).getTitle());
                    count++;
                }
            }
        }
        if(autoCompleteAdapter==null){
            autoCompleteAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,autoCompleteData);
        }else
            autoCompleteAdapter.notifyDataSetChanged();
    }
    private void getResultData(String text){
        if(resultData==null)
            resultData=new ArrayList<>();
        else{
            resultData.clear();
            for(int i=0;i<resultData.size();i++){
                if(dbData.get(i).getTitle().contains(text.trim()))
                    resultData.add(dbData.get(i));
            }
        }
        if(resultAdapter==null){
            resultAdapter=new SearchAdapter(this,resultData,R.layout.item_bean_list);
        }else
            resultAdapter.notifyDataSetChanged();
    }
    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     * @param text
     */
    @Override
    public void onRefreshAutocomplete(String text){
        getAutoCompletedData(text);
    }

    @Override
    public void onSearch(String text){
        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        if(lvResults.getAdapter()==null){
            lvResults.setAdapter(resultAdapter);
        }else
            resultAdapter.notifyDataSetChanged();
        Intent intent=new Intent(MainActivity.this,WupinInforActivity.class);
        startActivity(intent);
    }





}

//    private List<String> list = new ArrayList<>();
//    private TextView myTextView;
//    private Spinner mySpinner;
//    private ArrayAdapter<String> adapter;  //数组适配器
    /* list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        myTextView=(TextView)findViewById(R.id.textView);
        mySpinner=(Spinner)findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?>arg0,View arg1,int arg2,long arg3){
                myTextView.setText("你选择的是："+adapter.getItem(arg2));
            }
            public void onNothing(SelectedAdapterView<?>arg0){
                myTextView.setText("nothing");
            }
        });
    }
    */
