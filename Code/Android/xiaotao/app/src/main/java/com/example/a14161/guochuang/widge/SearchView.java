package com.example.a14161.guochuang.widge;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a14161.guochuang.MainActivity;
import com.example.a14161.guochuang.R;

/**
 * Created by 14161 on 2017/9/5.
 */

public class SearchView extends LinearLayout implements View.OnClickListener{
    //输入框
    private EditText etInput;
    private ImageView voiceInput;
    private Button btnBack;
    private Context mContext;

    private ListView lvTips;
    //提示Adapter
    private ArrayAdapter<String> mHintAdapter;
    //自动补全adapter
    private ArrayAdapter<String> mAutoCompleteAdapter;
    private SearchViewListener mListener;

    public SearchView(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.search_view,this);
        initViews();
    }
    private void initViews(){
        etInput=(EditText)findViewById(R.id.search_et_input);
        voiceInput=(ImageView)findViewById(R.id.voice_input);
        btnBack=(Button)findViewById(R.id.search_btn_enter);
        lvTips=(ListView)findViewById(R.id.search_lv_tips);

        etInput.addTextChangedListener(new EditChangeListener());
        etInput.setOnClickListener(this);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView view, int i, KeyEvent keyEvent){
                if(i== EditorInfo.IME_ACTION_SEARCH){
                    lvTips.setVisibility(GONE);
                    notifyStartSearching(etInput.getText().toString());
                }
                return true;
            }
        });

        lvTips.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> apaterView,View view,int i,long l){
                String text=lvTips.getAdapter().getItem(i).toString();
                etInput.setText(text);
                etInput.setSelection(text.length());
                lvTips.setVisibility(View.GONE);
                notifyStartSearching(text);
            }
        });

        voiceInput.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }
    private void notifyStartSearching(String text){
        if(mListener!=null)
            mListener.onSearch(etInput.getText().toString());

        InputMethodManager inputMethodManager=(InputMethodManager)mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public void setTipsHintAdapter(ArrayAdapter<String> adapter){
        this.mHintAdapter=adapter;
        if(lvTips.getAdapter()==null){
            lvTips.setAdapter(mHintAdapter);
        }
    }
    public void setAutoCompleteAdapter(ArrayAdapter<String> arrayAdapter){
        this.mAutoCompleteAdapter=arrayAdapter;
    }
    private class EditChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence,int i,int i2,int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence,int i,int i2,int i3){
            if(!"".equals(charSequence.toString())){

                lvTips.setVisibility(VISIBLE);
                if(mAutoCompleteAdapter!=null&&lvTips.getAdapter()!=mAutoCompleteAdapter){
                    lvTips.setAdapter(mAutoCompleteAdapter);
                }
                if(mListener!=null){
                    mListener.onRefreshAutocomplete(charSequence.toString());
                }
            }
            else{

                lvTips.setVisibility(GONE);
                if(mHintAdapter!=null){
                    lvTips.setAdapter(mHintAdapter);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable editable){

        }
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.search_et_input:
                lvTips.setVisibility(VISIBLE);
                ((MainActivity)mContext).setPlaceHolderVisable();
                break;
            case R.id.voice_input:
                etInput.setText("");
                Toast.makeText(mContext,"语音输入~~~",Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_btn_enter:
//                ((Activity)mContext).finish();
                etInput.setText(etInput.getText());
                Toast.makeText(mContext,"进入搜索",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public void setSearchViewListener(SearchViewListener searchListener){
        mListener=searchListener;
    }

    public interface SearchViewListener{
        void onRefreshAutocomplete(String text);

        void onSearch(String text);
    }
}
